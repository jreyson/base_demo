package com.ms.base.service;

import com.ms.base.domain.CaptchaDO;
import com.ms.base.domain.RegisterTO;
import com.ms.base.domain.Reply;
import com.ms.base.domain.UserDO;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
public interface AuthService {
    Reply register(RegisterTO registerTO);
    Reply login(UserDO user, CaptchaDO captchaDO);
    String refresh(String oldToken);
}

