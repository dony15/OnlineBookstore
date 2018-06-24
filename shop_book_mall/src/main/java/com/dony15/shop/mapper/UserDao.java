package com.dony15.shop.mapper;

import com.dony15.shop.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 用户注册
     * @param user
     * @return 是否注册成功
     */
    int insertUser(User user);

    /**
     * 校验注册用户是否存在
     * @param userInfo 用户名/邮箱/手机号的值
     * @param type 用户名/邮箱/手机号
     * @return 0未占用/>0已占用
     */
    int chekInfo(@Param("userInfo")String userInfo,@Param("type")String type);

    /**
     * 查询用户
     * @param userInfo
     * @param password
     * @return 用户信息
     */
    User selectUser(@Param("loginInfo") String userInfo,@Param("password") String password);

//    /**
//     * 通过uid查询用户
//     * @param uid
//     * @return
//     */
//    User selectUserById(@Param("uid")Long uid);

    /**
     * 更新用户 密码/性别/手机号
     * @param user
     * @return
     */
    int updateUser(User user);

}
