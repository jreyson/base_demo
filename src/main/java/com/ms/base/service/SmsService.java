package com.ms.base.service;

import com.ms.base.domain.Reply;
import com.ms.base.domain.SmsCodeTO;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
public interface SmsService {
    Reply smsSend(SmsCodeTO smsCodeTO);
}

