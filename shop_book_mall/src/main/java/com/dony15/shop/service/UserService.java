package com.dony15.shop.service;

import com.dony15.shop.mapper.UserDao;
import com.dony15.shop.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return 用户登录信息
     */
    User insertUser(User user);

    /**
     * 校验注册用户是否存在
     * @param userInfo 用户名/邮箱/手机号的值
     * @param type 用户名/邮箱/手机号
     * @return true 可使用/false 已占用
     */
    Boolean chekInfo(String userInfo, String type);

    /**
     * 查询用户
     * @param userInfo
     * @param password
     * @return 用户信息
     */
    User selectUser(String userInfo,String password);

    /**
     * 更新用户 密码/性别/手机号
     * @param user
     * @return
     */
    int updateUser(User user);
}
