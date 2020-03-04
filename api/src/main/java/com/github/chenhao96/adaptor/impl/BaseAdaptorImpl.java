package com.github.chenhao96.adaptor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.chenhao96.adaptor.BaseAdaptor;
import com.github.chenhao96.entity.bo.PageQuery;
import com.github.chenhao96.entity.vo.PageResult;

public abstract class BaseAdaptorImpl<D> implements BaseAdaptor<D> {

    protected abstract BaseMapper<D> getBaseMapper();

    public PageResult<D> queryPage(PageQuery query) {

        //TODO:
        QueryWrapper<D> queryWrapper = new QueryWrapper<>();

        Page<D> page = new Page<>(query.getPage(), query.getLimit());

        getBaseMapper().selectPage(page, queryWrapper);

        PageResult<D> result = new PageResult<>();
        result.setNext(page.hasNext());
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords());
        result.setPrevious(page.hasPrevious());
        return result;
    }

    public boolean saveNewRecord(D record) {
        if (record == null) return false;
        return getBaseMapper().insert(record) == 1;
    }

    public boolean updateRecordInfo(D record) {
        if (record == null) return false;
        return getBaseMapper().updateById(record) == 1;
    }

    public D queryRecordById(Integer id) {
        if (id == null || id < 1) return null;
        return getBaseMapper().selectById(id);
    }

    public boolean deleteRecord(Integer id) {
        if (id == null || id < 1) return false;
        return getBaseMapper().deleteById(id) == 1;
    }
}
