package com.example.server.soa.dao.mysql.mapper;

import com.example.server.soa.dao.mysql.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insert(User user);

    User selectById(Long id);

    User selectByUsernameWithPassword(String user);

    User selectByMobileWithPassword(String user);

    User selectByEmailWithPassword(String user);

    int updateById(User user);

    int deleteById(Long id);
}
