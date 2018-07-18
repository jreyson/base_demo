package com.ms.base.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JamelHuang
 * @date 2018/3/1
 */
public class UserDO extends BaseDO implements Serializable {
    private String username;
    private String nickname;
    private String phone;
    @JSONField(name = "password")
    private String password;
    @JSONField(name = "email")
    private String email;
    @JSONField(name = "auth_token")
    private String authToken;
    /**
     * 激活状态 0 已激活 1 未激活
     */
    private Integer status;
    /**
     * 0 手机 1 微信 2 微博
     */
    @JSONField(name = "registered_source")
    private Integer registeredSource;
    @JSONField(name = "wx_open_id")
    private String wxOpenId;
    private String avator;
    @JSONField(name = "weibo_open_id")
    private String weiboOpenId;
    @JSONField(name = "reg_id")
    private String regId;
    private Date lastPasswordReset;
    private String roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegisteredSource() {
        return registeredSource;
    }

    public void setRegisteredSource(Integer registeredSource) {
        this.registeredSource = registeredSource;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getWeiboOpenId() {
        return weiboOpenId;
    }

    public void setWeiboOpenId(String weiboOpenId) {
        this.weiboOpenId = weiboOpenId;
    }


    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", authToken='" + authToken + '\'' +
                ", status=" + status +
                ", registeredSource=" + registeredSource +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", avator='" + avator + '\'' +
                ", weiboOpenId='" + weiboOpenId + '\'' +
                ", regId='" + regId + '\'' +
                ", lastPasswordReset=" + lastPasswordReset +
                ", roles='" + roles + '\'' +
                '}';
    }
}
