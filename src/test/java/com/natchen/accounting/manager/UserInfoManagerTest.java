package com.natchen.accounting.manager;

import com.natchen.accounting.converter.p2c.UserInfoP2CConverter;
import com.natchen.accounting.dao.UserInfoDao;
import com.natchen.accounting.exception.ResourceNotFoundException;
import com.natchen.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class UserInfoManagerTest {
    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoDao userInfoDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, new UserInfoP2CConverter());
    }

    @Test
    void testGetUserInfoByUserId() {
        val userId = 1L;
        val username = "hardcore";
        val password = "hardcore";
        val createTime = LocalDate.now();

        val userInfo = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(createTime)
                .build();

        doReturn(userInfo).when(userInfoDAO).getUserInfoById(userId);

        val result = userInfoManager.getUserInfoByUserId(userId);

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);

        verify(userInfoDAO).getUserInfoById(userId);
    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() {
        val userId = -1L;
        doReturn(null).when(userInfoDAO).getUserInfoById(userId);

        assertThrows(
                ResourceNotFoundException.class,
                () -> userInfoManager.getUserInfoByUserId(userId)
        );

        verify(userInfoDAO).getUserInfoById(userId);
    }
}
