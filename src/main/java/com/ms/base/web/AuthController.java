package com.ms.base.web;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ms.base.domain.*;
import com.ms.base.service.AuthService;
import com.ms.base.service.CaptchaService;
import com.ms.base.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
@RestController
@RequestMapping( "${base.url}" )
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private SmsService smsService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    private CaptchaService captchaService;

    //登陆系统
    @RequestMapping(value = "auth/login", method = RequestMethod.POST)
    public Reply login(
            @RequestBody LoginTO loginTO) throws AuthenticationException {
        final Reply login = authService.login(loginTO.getUser(), loginTO.getCaptcha());
        return login;
    }

    @RequestMapping(value = "loginout", method = RequestMethod.POST)
    public Reply loginOut() throws AuthenticationException {
        return new Reply(new JSONObject(), 200, "登出成功！", true);
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    //注册用户
    @RequestMapping(value = "auth/register", method = RequestMethod.POST)
    public Reply register(@RequestBody RegisterTO registerTO) throws AuthenticationException {
        return authService.register(registerTO);
    }

    //发送短信验证码
    @RequestMapping(value = "auth/smsSend", method = RequestMethod.POST)
    public Reply smsSend(@RequestBody SmsCodeTO smsCodeTO) throws AuthenticationException {
        return smsService.smsSend(smsCodeTO);
    }

    //图片验证码
    @RequestMapping(value = "auth/captcha", method = RequestMethod.POST)
    public Reply captcha(HttpServletResponse response) throws Exception {
        String capText = defaultKaptcha.createText();
        CaptchaDO captchaDO = captchaService.generateCaptcha(capText);
        if (captchaDO != null) {
            // create the image with the text
            BufferedImage bi = defaultKaptcha.createImage(capText);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", output);
            Base64.Encoder base64 = Base64.getEncoder();
            String imgsrc = base64.encodeToString(output.toByteArray());
            JSONObject jsonObject = new JSONObject();
            CaptchaTO captchaTO = new CaptchaTO();
            captchaTO.setId(captchaDO.getId());
            captchaTO.setImage_base64("data:image/png;base64," + imgsrc);
            jsonObject.put("captcha", captchaTO);
            try {
                output.flush();
            } finally {
                output.close();
            }
            return new Reply(jsonObject, 200, "生成图片验证码成功！", true);
        }
        return new Reply(new JSONObject(), 500, "生成验证码失败!", false);
    }
}
