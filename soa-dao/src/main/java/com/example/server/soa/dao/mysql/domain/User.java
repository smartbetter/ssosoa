package com.example.server.soa.dao.mysql.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户基础信息表
 */
@Data
public class User {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户的性别, 0-未知, 1-男, 2-女
     */
    private Integer gender;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 手机号码是否验证, 0-已验证, 1-未验证
     */
    private Boolean mobileVerified;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱是否验证, 0-已验证, 1-未验证
     */
    private Boolean emailVerified;
    /**
     * 用户状态, 0-正常, 1-已冻结
     */
    private Integer status;
    /**
     * 主动创建时间
     */
    private Date gmtCreate;
    /**
     * 被动更新时间
     */
    private Date gmtModified;
}