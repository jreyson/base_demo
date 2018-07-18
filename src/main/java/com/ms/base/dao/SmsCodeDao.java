package com.ms.base.dao;

import com.ms.base.domain.SmsCodeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SmsCodeDao {
    int insert(@Param("pojo") SmsCodeDO pojo);
    SmsCodeDO findSmsCodeByPhone(@Param("phone") String phone);
}
