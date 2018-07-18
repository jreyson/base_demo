package com.ms.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ms.base.dao.CaptchaDao;
import com.ms.base.dao.SmsCodeDao;
import com.ms.base.dao.UserDao;
import com.ms.base.domain.*;
import com.ms.base.service.AuthService;
import com.ms.base.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SmsCodeDao smsCodeDao;
    @Autowired
    private CaptchaDao captchaDao;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
//    @Autowired
//    public AuthServiceImpl(
//            AuthenticationManager authenticationManager,
//            UserDetailsService userDetailsService,
//            JwtTokenUtil jwtTokenUtil,
//            UserDao userDao) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.userDao = userDao;
//    }

    @Override
    public Reply register(RegisterTO registerTO) {
        if(registerTO == null || registerTO.getUser() == null || registerTO.getSmsCode() == null){
            return new Reply(new JSONObject(), 301, "参数无效！", false);
        }
        UserDO userDO = registerTO.getUser();
        final String username = registerTO.getUser().getUsername();
        if(userDao.findByUsername(username)!=null) {
            return new Reply(new JSONObject(), 203,"当前手机号已注册，请直接登陆！", false);
        }
        SmsCodeDO smsCodeDO = smsCodeDao.findSmsCodeByPhone(username);
        if (smsCodeDO == null || !registerTO.getSmsCode().getValue().equals(smsCodeDO.getValue())){
            return new Reply(new JSONObject(), 204, "短信验证码错误！", false);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userDO.getPassword();
        userDO.setPassword(encoder.encode(rawPassword));
        userDO.setLastPasswordReset(new Date());
        userDao.insert(userDO);
        return new Reply(userDO,200,"注册成功！", true);
    }

    @Override
    public Reply login(UserDO user, CaptchaDO captchaDO) {
        if(user == null || captchaDO == null || captchaDO.getId() == null || user.getUsername() == null || user.getPassword() == null || captchaDO.getValue() == null){
            return new Reply(new JSONObject(), 301,"请求参数错误！", false);
        }

        CaptchaDO captchaFind = captchaDao.findCaptchaById(captchaDO.getId());
        if (captchaFind == null || !captchaDO.getValue().equals(captchaFind.getValue())) {
            return new Reply(new JSONObject(), 205, "图片验证码错误", false);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDO userDO = userDao.findByUsername(user.getUsername());
        if(userDO == null || !encoder.matches(user.getPassword(),userDO.getPassword())){
            return new Reply(new JSONObject(), 203, "账户或密码错误！", false);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails,"web");
        userDO.setAuthToken(token);
        userDao.update(userDO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", userDO);
        return new Reply(jsonObject,200, "登录成功！", true);
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
