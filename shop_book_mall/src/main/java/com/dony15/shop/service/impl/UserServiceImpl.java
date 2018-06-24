package com.dony15.shop.service.impl;

import com.dony15.shop.mapper.UserDao;
import com.dony15.shop.pojo.User;
import com.dony15.shop.service.UserService;
import com.dony15.shop.utils.MessageDigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DonY15
 * @description
 * @create 2018\6\19 0019
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;


    @Override
    public User insertUser(User user) {
        user.setPassword(MessageDigestUtils.sha1(user.getPassword()));
        int i = userdao.insertUser(user);
        if (i>0){
        return userdao.selectUser(user.getUsername(), user.getPassword());
        }
        return null;
    }

    @Override
    public Boolean chekInfo(String userInfo, String type) {
        int i = userdao.chekInfo(userInfo, type);

        return i==0?true:false;
    }

    @Override
    public User selectUser(String userInfo, String password) {
        return userdao.selectUser(userInfo, password);
    }

    @Override
    public int updateUser(User user) {
        return userdao.updateUser(user);
    }
}
