package com.ms.base.service.impl;

import com.ms.base.dao.CaptchaDao;
import com.ms.base.domain.CaptchaDO;
import com.ms.base.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaDao captchaDao;

    @Override
    public CaptchaDO generateCaptcha(String captcha) {
        if (captcha != null) {
            CaptchaDO captchaDO = new CaptchaDO();
            captchaDO.setValue(captcha);
            captchaDao.insert(captchaDO);
            return captchaDO;
        } else {
            return null;
        }

    }
}
