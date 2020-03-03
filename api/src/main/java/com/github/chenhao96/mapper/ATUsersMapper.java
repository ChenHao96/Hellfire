package com.github.chenhao96.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.chenhao96.entity.po.ATUsers;
import org.apache.ibatis.annotations.Select;

public interface ATUsersMapper extends BaseMapper<ATUsers> {

    @Select("select id from a_t_users where username = #{username,jdbcType=VARCHAR}")
    Integer queryHasUsername(String username);
}
