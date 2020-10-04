package com.natchen.accounting.manager;

import com.natchen.accounting.converter.p2c.UserInfoP2CConverter;
import com.natchen.accounting.dao.UserInfoDaoImpl;
import com.natchen.accounting.exception.ResourceNotFoundException;
import com.natchen.accounting.utils.UserInfoMapperTestImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInfoManagerWithoutMockitoTest {
    private UserInfoManager userInfoManager;

    @BeforeEach
    void setUp() {
        val userMapper = new UserInfoMapperTestImpl();
        val userInfoP2CConverter = new UserInfoP2CConverter();
        val userInfoDao = new UserInfoDaoImpl(userMapper);
        userInfoManager = new UserInfoManagerImpl(userInfoDao, userInfoP2CConverter);
    }

    @Test
    void testGetUserInfoByUserId() {
        val userId = 1L;
        val result = userInfoManager.getUserInfoByUserId(userId);

        assertEquals(userId, result.getId());
        assertEquals("hardcore", result.getUsername());
        assertEquals("hardcore", result.getPassword());
    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() {
        val userId = -1L;
        assertThrows(
                ResourceNotFoundException.class,
                () -> userInfoManager.getUserInfoByUserId(userId)
        );
    }
}
