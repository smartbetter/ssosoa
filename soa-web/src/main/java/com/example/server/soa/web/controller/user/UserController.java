package com.example.server.soa.web.controller.user;

import com.example.server.soa.common.constants.Constants;
import com.example.server.soa.common.constants.LoginConstants;
import com.example.server.soa.common.enums.ResultEnum;
import com.example.server.soa.common.protocol.ActionResult;
import com.example.server.soa.common.util.*;
import com.example.server.soa.dao.mysql.domain.User;
import com.example.server.soa.dao.redis.RedisClient;
import com.example.server.soa.service.user.UserService;
import com.example.server.soa.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController extends BaseController {

    @Value("${REDIS_SESSION_KEY}")
    private String sessionKey;
    @Value("${REDIS_SESSION_EXPIRE}")
    private Integer sessionExpire;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public Object register(@RequestBody User user) {
        try {
            if (StringUtils.isBlank(user.getUsername()) && StringUtils.isBlank(user.getMobile())
                    && StringUtils.isBlank(user.getEmail())) {
                return ResultTool.error(ResultEnum.PARAM_ERROR, "username/mobile/email must be filled");
            }
            if (StringUtils.isBlank(user.getPassword())) {
                return ResultTool.error(ResultEnum.PARAM_ERROR, "password must be filled");
            }

            // 校验是否已经存在
            if (user.getEmail() != null) {
                if (userService.check(user.getEmail(), LoginConstants.EMAIL)) {
                    return ResultTool.error(ResultEnum.PARAM_ERROR, "email already exists");
                }
            } else if (user.getMobile() != null) {
                if (userService.check(user.getMobile(), LoginConstants.MOBILE)) {
                    return ResultTool.error(ResultEnum.PARAM_ERROR, "mobile already exists");
                }
            } else if (user.getUsername() != null) {
                if (userService.check(user.getUsername(), LoginConstants.USERNAME)) {
                    return ResultTool.error(ResultEnum.PARAM_ERROR, "username already exists");
                }
            }

            if (userService.createUser(user)) {
                return ResultTool.success();
            }
            return ResultTool.error();
        } catch (Throwable e) {
            log.error("UserController.register error", e);
            return ResultTool.exception(e);
        }
    }

    @PostMapping("/user/edit")
    public Object editUser(User user) {
        try {
            if (user.getUserId() == null) {
                return ResultTool.error(ResultEnum.PARAM_ERROR, "id must be filled.");
            }
            if (userService.updateUser(user)) {
                return ResultTool.success();
            }
            return ResultTool.error();
        } catch (Throwable e) {
            log.error("UserController.editUser error", e);
            return ResultTool.exception(e);
        }
    }

    @PostMapping("/user/login")
    public Object login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password) {
        try {
            if (StringUtils.isBlank(loginName)) {
                return ResultTool.error(ResultEnum.PARAM_ERROR, "username/mobile/email must be filled");
            }
            if (StringUtils.isBlank(password)) {
                return ResultTool.error(ResultEnum.PARAM_ERROR, "password must be filled");
            }
            User user = null;
            // 通过校验格式判断是哪种登录方式
            if (RegexUtil.isEmail(loginName)) {
                user = userService.login(loginName, password, LoginConstants.EMAIL);
            } else if (RegexUtil.isMobilePhone(loginName)) {
                user = userService.login(loginName, password, LoginConstants.MOBILE);
            } else if (RegexUtil.match(loginName, Constants.PATTERN_USERNAME)) {
                user = userService.login(loginName, password, LoginConstants.USERNAME);
            }

            if (user == null || user.getUserId() == null) {
                return ResultTool.error(ResultEnum.ERROR.getCode(), "login failed");
            }

            ActionResult result = new ActionResult();
            String token = AESUtil.encryptHex(String.valueOf(user.getUserId()), AESUtil.DEFAULT_ENCRYPT_KEY);

            // 使用redis做分布式session
            redisClient.set(sessionKey + Constants.COLON + token, JsonUtil.write2JsonStr(user));
            redisClient.expire(sessionKey + Constants.COLON + token, sessionExpire);
            if (redisClient.get(sessionKey + Constants.COLON + token) == null) {
                return ResultTool.error();
            }

            // TODO 如果是web站点,这里还需要将token放入到cookie中
            result.addResult("token", token);
            return ResultTool.success(result);
        } catch (Throwable e) {
            log.error("UserController.login error", e);
            return ResultTool.exception(e);
        }
    }

    @GetMapping("/user/loginInfo")
    public Object queryLoginInfo(String token) {
        try {
            String json = redisClient.get(sessionKey + Constants.COLON + token);
            if (StringUtils.isBlank(json)) {
                return ResultTool.error(ResultEnum.ERROR.getCode(), "not login");
            }
            ActionResult result = new ActionResult();
            User user = JsonUtil.json2Object(json, User.class);
            // 更新过期时间
            redisClient.expire(sessionKey + Constants.COLON + token, sessionExpire);
            if (user != null) {
                result.addResult("user", user);
                return ResultTool.success(result);
            }
            return ResultTool.error();
        } catch (Throwable e) {
            log.error("UserController.queryLoginInfo error", e);
            return ResultTool.exception(e);
        }
    }

    @PostMapping("/user/logout")
    public Object logout(String token) {
        try {
            // 注销分布式session
            redisClient.del(sessionKey + Constants.COLON + token);
            // TODO 如果是web站点,这里还需要将cookie清空
            if (redisClient.get(sessionKey + Constants.COLON + token) == null) {
                return ResultTool.success();
            }
            return ResultTool.error();
        } catch (Throwable e) {
            log.error("UserController.logout error", e);
            return ResultTool.exception(e);
        }
    }
}
