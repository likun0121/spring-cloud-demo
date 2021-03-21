package com.lk.user.controller;

import com.lk.user.dataobject.UserInfo;
import com.lk.user.enums.ResultEnum;
import com.lk.user.enums.RoleEnum;
import com.lk.user.service.UserService;
import com.lk.user.util.ResultVOUtil;
import com.lk.user.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author LK
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        // openid和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByopenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAILED);
        }
        // 判断角色
        if (!RoleEnum.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        // cookie设置openid=abc
        Cookie cookie = new Cookie("openid", openid);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return ResultVOUtil.success(null);
    }

    /**
     * 卖家登录
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    cookie = c;
                }
            }
        }
        if (cookie != null
                && StringUtils.isNotEmpty(stringRedisTemplate.opsForValue().get("token_" + cookie.getValue()))) {
            return ResultVOUtil.success(null);
        }

        // openid和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByopenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAILED);
        }
        // 判断角色
        if (!RoleEnum.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        // 设置redis
        String uuid = UUID.randomUUID().toString();
        int expiry = 3600;
        stringRedisTemplate.opsForValue().set("token_" + uuid, openid, expiry, TimeUnit.SECONDS);

        // cookie设置openid=abc
        if (cookie == null) {
            cookie = new Cookie("token", uuid);
        }
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
        return ResultVOUtil.success(null);
    }
}
