/*
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 06/11/2018 18:22:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth`  (
  `id` bigint(18) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(18) UNSIGNED NOT NULL COMMENT '用户id',
  `identity_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登录类型, weixin-微信, weibo-微博',
  `identifier` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一标识, 微信的username, 微博的UID',
  `credential` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '密码凭证, 微信的token, 微博的access_token',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '主动创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '被动更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_identity_type_identifier`(`identity_type`, `identifier`) USING BTREE COMMENT '唯一索引',
  UNIQUE INDEX `uk_user_id_identity_type`(`user_id`, `identity_type`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户授权信息表, 第三方登录用' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES (1, 1, 'weixin', '123', 0, '2018-09-10 12:09:37', '2018-11-06 18:22:07');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` bigint(18) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `gender` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户的性别, 0-未知, 1-男, 2-女',
  `avatar_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `mobile` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `mobile_verified` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '手机号码是否验证, 0-已验证, 1-未验证',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `email_verified` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '邮箱是否验证, 0-已验证, 1-未验证',
  `status` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态, 0-正常, 1-已冻结',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '主动创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '被动更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE COMMENT '唯一索引',
  UNIQUE INDEX `uk_mobile`(`mobile`) USING BTREE COMMENT '唯一索引',
  UNIQUE INDEX `uk_email`(`email`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'user', 'e10adc3949ba59abbe56e057f20f883e', 'nickname', 1, 'http://www.example.com/avatar.jpg', NULL, NULL, 'user@example.com', NULL, 0, '2018-09-10 11:58:48', '2018-11-06 18:21:29');

SET FOREIGN_KEY_CHECKS = 1;
