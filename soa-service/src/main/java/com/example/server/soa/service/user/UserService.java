package com.example.server.soa.service.user;

import com.example.server.soa.dao.mysql.domain.User;

public interface UserService {

    boolean createUser(User user);

    User getUserById(Long userId);

    boolean updateUser(User userInfo);

    boolean deleteUserById(Long userId);

    User login(String loginName, String password, String loginType);

    boolean check(String loginName, String loginType);
}
