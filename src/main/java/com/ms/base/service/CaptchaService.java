package com.ms.base.service;

import com.ms.base.domain.CaptchaDO;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
public interface CaptchaService {
    CaptchaDO generateCaptcha(String captcha);
}

