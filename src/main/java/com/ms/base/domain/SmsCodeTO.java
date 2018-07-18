package com.ms.base.domain;

/**
 * @author JamelHuang
 * @date 2018/3/6
 */
public class SmsCodeTO {
    private CaptchaDO captcha;
    private SmsCodeDO user;

    public CaptchaDO getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaDO captcha) {
        this.captcha = captcha;
    }

    public SmsCodeDO getUser() {
        return user;
    }

    public void setUser(SmsCodeDO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SmsCodeTO{" +
                "captcha=" + captcha +
                ", user=" + user +
                '}';
    }
}
