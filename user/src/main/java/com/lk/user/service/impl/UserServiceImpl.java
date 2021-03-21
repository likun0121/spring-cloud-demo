package com.lk.user.service.impl;

import com.lk.user.dataobject.UserInfo;
import com.lk.user.repository.UserInfoRepository;
import com.lk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LK
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByopenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
