package com.dony15.shop.pojo;

import org.springframework.stereotype.Component;

/**
 * @author DonY15
 * @description
 * @create 2018\6\19 0019
 */
@Component("user")
public class User {
    /**
     * @uid 用户id
     * @username 用户名
     * @password 用户密码
     * @email 邮箱
     * @telephone 手机号
     * @gender 性别
     * @introduce 个人介绍
     * @activeCode 激活码
     * @role 角色
     * @state 激活状态 0未激活/1已激活/2已注销
     * @registTime 注册时间
     */
    private Long uid;
    private String username;
    private String password;
    private String email;
    private String telephone;
    private String gender;
    private String introduce;
    private String activeCode;
    private String role;
    private Integer state;
    private String registTime;

    public User() {
    }

    public User(String username, String password, String email, String telephone, String gender, String introduce) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.gender = gender;
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gender='" + gender + '\'' +
                ", introduce='" + introduce + '\'' +
                ", activeCode='" + activeCode + '\'' +
                ", role='" + role + '\'' +
                ", state=" + state +
                ", registTime='" + registTime + '\'' +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }
}
