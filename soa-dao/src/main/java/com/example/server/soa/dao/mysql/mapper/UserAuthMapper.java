package com.example.server.soa.dao.mysql.mapper;

import com.example.server.soa.dao.mysql.domain.UserAuth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthMapper {

    int insert(UserAuth userAuth);

    UserAuth selectById(Long id);

    int updateById(UserAuth userAuth);

    int deleteById(Long id);
}
