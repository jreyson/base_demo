package com.ms.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JamelHuang
 * @date 2018/3/6
 */
@Configuration
@ConfigurationProperties(prefix="msg.sms")
public class SmsConfig {
    private String username;
    private String password;
    private String host;

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
