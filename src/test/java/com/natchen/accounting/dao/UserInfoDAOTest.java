package com.natchen.accounting.dao;

import com.natchen.accounting.dao.mapper.UserInfoMapper;
import com.natchen.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserInfoDAOTest {
    @Mock
    private UserInfoMapper userInfoMapper;

    @InjectMocks
    private UserInfoDAOImpl userInfoDAO;

    @Test
    public void testGetUserInfoById() {
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";
        val createTime = LocalDate.now();

        val userInfo = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(createTime)
                .build();
        doReturn(userInfo).when(userInfoMapper).getUserInfoByUserId(userId);

        val result = userInfoDAO.getUserInfoById(userId);
        assertEquals(userInfo, result);
        verify(userInfoMapper).getUserInfoByUserId(userId);
    }
}
