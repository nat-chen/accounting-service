package com.natchen.accounting.converter;

import com.natchen.accounting.converter.p2c.UserInfoP2CConverter;
import com.natchen.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoP2CConverterTest {
    private UserInfoP2CConverter userInfoP2CConverter = new UserInfoP2CConverter();

    @Test
    void testDoForward() {
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";
        val createTime = LocalDate.now();

        val userInfoInPersistence = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(createTime)
                .build();

        val result = userInfoP2CConverter.convert(userInfoInPersistence);

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
    }

    @Test
    void testDoBackward() {
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";

        val userInfoInCommon = com.natchen.accounting.model.common.UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        val result = userInfoP2CConverter.reverse().convert(userInfoInCommon);

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("createTime", null)
                .hasFieldOrPropertyWithValue("password", password);
    }
}
