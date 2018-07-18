package com.ms.base.domain;

/**
 * @author JamelHuang
 * @date 2018/3/5
 */
public class LoginTO {


    private UserDO user;
    private CaptchaDO captcha;

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public CaptchaDO getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaDO captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "LoginTO{" +
                "user=" + user +
                ", captcha=" + captcha +
                '}';
    }
}
