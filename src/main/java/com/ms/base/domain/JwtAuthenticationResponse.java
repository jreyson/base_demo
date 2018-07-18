package com.ms.base.domain;

import java.io.Serializable;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
public class JwtAuthenticationResponse implements Serializable {

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
