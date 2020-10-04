package com.natchen.accounting.converter.p2c;

import com.google.common.base.Converter;
import com.natchen.accounting.model.persistence.UserInfo;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfoP2CConverter extends Converter<UserInfo, com.natchen.accounting.model.common.UserInfo> {

    @Override
    protected com.natchen.accounting.model.common.UserInfo doForward(UserInfo userInfo) {
        return com.natchen.accounting.model.common.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.natchen.accounting.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }
}
