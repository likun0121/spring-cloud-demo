package com.lk.user.service;

import com.lk.user.dataobject.UserInfo;

/**
 * @author LK
 */
public interface UserService {

    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByopenid(String openid);
}
