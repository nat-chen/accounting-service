package com.natchen.accounting.converter.c2s;

import com.google.common.base.Converter;
import com.natchen.accounting.model.common.UserInfo;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfoC2SConverter extends Converter<UserInfo, com.natchen.accounting.model.service.UserInfo> {

    @Override
    protected com.natchen.accounting.model.service.UserInfo doForward(UserInfo userInfo) {
        return com.natchen.accounting.model.service.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.natchen.accounting.model.service.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .password(userInfo.getPassword())
                .username(userInfo.getUsername())
                .build();
    }
}
