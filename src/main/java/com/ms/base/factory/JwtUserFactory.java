package com.ms.base.factory;

import com.alibaba.fastjson.JSONObject;
import com.ms.base.domain.JwtUser;
import com.ms.base.domain.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserDO user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(JSONObject.parseArray(user.getRoles(),String.class)),
                user.getLastPasswordReset()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

