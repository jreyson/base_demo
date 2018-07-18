package com.ms.base.dao;

import com.ms.base.domain.CaptchaDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CaptchaDao {
    int insert(@Param("pojo") CaptchaDO pojo);
    CaptchaDO findCaptchaById(@Param("id") Long id);
}
