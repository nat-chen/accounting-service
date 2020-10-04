package com.natchen.accounting.utils;

import com.natchen.accounting.dao.mapper.UserInfoMapper;
import com.natchen.accounting.model.persistence.UserInfo;

import java.time.LocalDate;

public class UserInfoMapperTestImpl implements UserInfoMapper {
    @Override
    public UserInfo getUserInfoByUserId(Long id) {
        return id > 0 ?
                UserInfo.builder()
                    .id(id)
                    .username("hardcore")
                    .password("hardcore")
                    .createTime(LocalDate.now())
                    .build()
                : null;
    }
}
