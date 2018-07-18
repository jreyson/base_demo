package com.ms.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ms.base.config.SmsConfig;
import com.ms.base.dao.CaptchaDao;
import com.ms.base.dao.SmsCodeDao;
import com.ms.base.domain.CaptchaDO;
import com.ms.base.domain.Reply;
import com.ms.base.domain.SendBackMsg;
import com.ms.base.domain.SmsCodeTO;
import com.ms.base.service.SmsService;
import com.ms.base.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsCodeDao smsCodeDao;
    @Autowired
    private CaptchaDao captchaDao;
    @Autowired
    private SmsConfig smsConfig;

    private final String smsModel = "[MagicStar] 您的验证码是：";

    @Override
    public Reply smsSend(SmsCodeTO smsCodeTO) {
        if (smsCodeTO != null && smsCodeTO.getCaptcha() != null && smsCodeTO.getCaptcha().getId() != null && smsCodeTO.getCaptcha().getValue() != null && smsCodeTO.getUser().getPhone() != null) {
            CaptchaDO captchaDO = captchaDao.findCaptchaById(smsCodeTO.getCaptcha().getId());
            if (captchaDO != null && smsCodeTO.getCaptcha().getValue().equals(captchaDO.getValue())) {
                String smsCode = EncryptUtil.generateIdentifierBySize(6);
                SendBackMsg sendBackMsg = send(smsCodeTO.getUser().getPhone(), smsModel + smsCode + "， 10分钟有效。");
                if ("0".equals(sendBackMsg.getErrorCode())) {
                    smsCodeTO.getUser().setValue(smsCode);
                    smsCodeDao.insert(smsCodeTO.getUser());
                    return new Reply(new JSONObject(), 200,"短信验证码发送成功！", true);
                } else {
                    return new Reply(new JSONObject(), 500, "短信验证码发送失败！", false);
                }
            } else {
                return new Reply(new JSONObject(), 301, "图片验证码错误！", false);
            }

        } else {
            return new Reply(new JSONObject(), 301,"参数无效！", false);
        }

    }

    public SendBackMsg send(String phone, String content) {
        StringBuffer httpArg = new StringBuffer();
        httpArg.append("u=").append(smsConfig.getUsername()).append("&");
        httpArg.append("p=").append(md5(smsConfig.getPassword())).append("&");
        httpArg.append("m=").append(phone).append("&");
        httpArg.append("c=").append(encodeUrlString(content, "UTF-8"));

        String httpUrl = smsConfig.getHost() + "?" + httpArg.toString();

        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        SendBackMsg sendBackMsg = new SendBackMsg();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = reader.readLine();
            if (strRead != null) {
                sbf.append(strRead);
                while ((strRead = reader.readLine()) != null) {
                    sbf.append("\n");
                    sbf.append(strRead);
                }
            }
            reader.close();
            sendBackMsg.setErrorCode(sbf.toString());
            sendBackMsg.setErrorMsg(errorCodeToMsg(sbf.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendBackMsg;
    }

    public String md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = ((int) b[offset]) & 0xff;
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return buf.toString();
    }

    private String encodeUrlString(String str, String charset) {
        String result = null;
        if (str == null) {
            return str;
        }

        try {
            result = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    private String errorCodeToMsg(String errorCode) {

        Map<String, String> errorCodes = new HashMap<String, String>() {
            {
                put("30", "密码错误");
                put("40", "账号不存在");
                put("41", "余额不足");
                put("42", "账号过期");
                put("43", "IP地址限制");
                put("50", "内容含有敏感词");
                put("51", "手机号码不正确");
                put("0", "短信发送成功");
            }
        };
        return errorCodes.get(errorCode);
    }
}
