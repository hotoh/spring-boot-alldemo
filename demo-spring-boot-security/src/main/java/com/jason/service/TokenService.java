package com.jason.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jason.constant.CacheConstants;
import com.jason.constant.Constants;
import com.jason.domain.LoginUser;
import com.jason.domain.SysUser;
import com.jason.util.IpUtils;
import com.jason.util.SecurityUtils;
import com.jason.util.ServletUtils;
import com.jason.utils.IdUtils;

import com.jason.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author jason
 */
@Component
public class TokenService
{
    @Autowired
    private RedisService redisService;

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    protected static final long MILLIS_SECOND = 1000;

//    /**
//     * 创建令牌
//     */
//    public Map<String, Object> createToken(LoginUser loginUser)
//    {
//        // 生成token
//        String token = IdUtils.fastUUID();
//        loginUser.setToken(token);
//        loginUser.setUserid(loginUser.getSysUser().getUserId());
//        loginUser.setUsername(loginUser.getSysUser().getUserName());
//        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
//        refreshToken(loginUser);
//
//        // 保存或更新用户token
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("access_token", token);
//        map.put("expires_in", EXPIRE_TIME);
//        redisService.setCacheObject(ACCESS_TOKEN + token, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
//        return map;
//    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser()
    {
        return getLoginUser(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            LoginUser loginUser = (LoginUser)redisService.getCacheObject(userKey);
            return loginUser;
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser)
    {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
        {
            refreshToken(loginUser);
        }
    }

    public void delLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            redisService.deleteObject(userKey);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME * MILLIS_SECOND);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    private String getTokenKey(String token)
    {
        return ACCESS_TOKEN + token;
    }
}
