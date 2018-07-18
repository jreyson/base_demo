package com.ms.base.dao;

import com.ms.base.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    int insert(@Param("pojo") UserDO pojo);
    int update(@Param("pojo") UserDO pojo);

    UserDO findByUsername(@Param("username") String username);
}
