package com.ms.base.domain;

/**
 * @author JamelHuang
 * @date 2018/3/6
 */
public class RegisterTO {
    private UserDO user;
    private SmsCodeDO smsCode;

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public SmsCodeDO getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeDO smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "RegisterTO{" +
                "user=" + user +
                ", smsCode=" + smsCode +
                '}';
    }
}
