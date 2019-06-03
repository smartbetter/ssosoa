package com.example.server.soa.dao.mysql.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户授权信息表, 第三方登录用
 */
@Data
public class UserAuth {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 登录类型, weixin-微信, weibo-微博
     */
    private String identityType;
    /**
     * 唯一标识, 微信的username, 微博的UID
     */
    private String identifier;
    /**
     * 密码凭证, 微信的token, 微博的access_token
     */
    private Boolean credential;
    /**
     * 主动创建时间
     */
    private Date gmtCreate;
    /**
     * 被动更新时间
     */
    private Date gmtModified;
}