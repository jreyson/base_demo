package com.ms.base.domain;

import java.io.Serializable;

/**
 * @author JamelHuang
 * @date 2018/3/6
 */
public class CaptchaDO extends BaseDO implements Serializable {
    private String phone;
    private String value;
    private String type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CaptchaDO{" +
                "phone='" + phone + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
