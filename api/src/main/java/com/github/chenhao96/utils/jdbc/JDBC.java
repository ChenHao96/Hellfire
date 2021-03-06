package com.github.chenhao96.utils.jdbc;

import com.github.chenhao96.utils.CommonsUtil;

import java.sql.*;
import java.util.*;

public class JDBC {

    private String driverClass;
    private String connectionUrl;
    private String userName;
    private String password;

    private Connection connection;
    private boolean successInit;

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void init() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            connection.setAutoCommit(false);
            successInit = true;
        } catch (Exception e) {
            throw new SqlJdbcException(e);
        }
    }

    public Map<String, Object> querySingleton(String sql, Object... params) {

        if (!successInit) throw new SqlJdbcException("JDBC is not init success.");

        List<Map<String, Object>> result = query(sql, params);
        if (result == null || result.size() == 0) return null;
        if (result.size() > 1) {
            throw new SqlJdbcException(String.format("Many Result size:%d", result.size()));
        }

        return result.get(0);
    }

    public List<Map<String, Object>> query(String sql, Object... params) {

        if (!successInit) throw new SqlJdbcException("JDBC is not init success.");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = addPsParams(connection, sql, params);
            resultSet = preparedStatement.executeQuery();
            return getResultMaps(resultSet);
        } catch (Exception e) {
            throw new SqlJdbcException(e);
        } finally {
            CommonsUtil.safeClose(resultSet, preparedStatement);
        }
    }

    private List<Map<String, Object>> getResultMaps(ResultSet resultSet) throws SQLException {

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        Set<String> columnNames = new HashSet<>(columnCount);
        for (int i = 0; i < columnCount; i++) {
            columnNames.add(resultSetMetaData.getColumnName(i + 1));
        }
        int listSize = resultSet.getFetchSize();
        List<Map<String, Object>> result = new ArrayList<>(listSize);

        while (resultSet.next()) {
            Map<String, Object> vo = new HashMap<>(columnCount);
            for (String column : columnNames) {
                vo.put(column, resultSet.getObject(column));
            }
            result.add(vo);
        }

        return result;
    }

    public int update(String sql, Object... params) {

        if (!successInit) throw new SqlJdbcException("JDBC is not init success.");

        int result = 0;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = addPsParams(connection, sql, params);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new SqlJdbcException(e);
        } finally {
            CommonsUtil.safeClose(preparedStatement);
        }

        return result;
    }

    public int prepareCallUpdate(String sql, CallableParam... params) {

        if (!successInit) throw new SqlJdbcException("JDBC is not init success.");

        int result = 0;
        CallableStatement callableStatement = null;

        try {
            callableStatement = addPsParams4Call(connection, sql, params);
            result = callableStatement.executeUpdate();
            returnPsParams4Call(callableStatement, params);
        } catch (Exception e) {
            throw new SqlJdbcException(e);
        } finally {
            CommonsUtil.safeClose(callableStatement);
        }

        return result;
    }

    public List<List<Map<String, Object>>> prepareCallQuery(String sql, CallableParam... params) {

        if (!successInit) throw new SqlJdbcException("JDBC is not init success.");

        List<List<Map<String, Object>>> result = null;
        CallableStatement callableStatement = null;

        try {
            callableStatement = addPsParams4Call(connection, sql, params);
            if (callableStatement.execute()) {
                result = new ArrayList<>(6);
                do {
                    ResultSet resultSet = callableStatement.getResultSet();
                    result.add(getResultMaps(resultSet));
                } while (callableStatement.getMoreResults());

                returnPsParams4Call(callableStatement, params);
            }
        } catch (Exception e) {
            throw new SqlJdbcException(e);
        } finally {
            CommonsUtil.safeClose(callableStatement);
        }

        return result;
    }

    private void returnPsParams4Call(CallableStatement callableStatement, CallableParam... params) {

        if (callableStatement == null) return;
        if (params == null || params.length == 0) return;

        for (int i = 0; i < params.length; i++) {
            try {
                params[i].setReturnValue(callableStatement.getObject(i + 1));
            } catch (SQLException e) {
                throw new SqlJdbcException(e);
            }
        }
    }

    private static CallableStatement addPsParams4Call(Connection connection, String sql, CallableParam... params) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall(sql);

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                switch (params[i].getIsInOrOutAndInOut()) {

                    case CallableParam.IN_PARAM:
                        callableStatement.setObject(i + 1, params[i].getValue());
                        break;

                    case CallableParam.IN_OUT_PARAM:
                        callableStatement.setObject(i + 1, params[i].getValue());

                    case CallableParam.OUT_PARAM:
                        callableStatement.registerOutParameter(i + 1, params[i].getType());
                        break;
                }
            }
        }

        return callableStatement;
    }

    private static PreparedStatement addPsParams(Connection connection, String sql, Object... params) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }

        return preparedStatement;
    }

    public void commit() {
        if (!successInit) return;
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new SqlJdbcException(e);
        }
    }

    public void rollback() {
        if (!successInit) return;
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new SqlJdbcException(e);
        }
    }

    public void close() {
        CommonsUtil.safeClose(connection);
        connection = null;
        successInit = false;
    }
}
