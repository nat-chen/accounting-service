package com.natchen.accounting.controlller;

import com.natchen.accounting.controller.UserController;
import com.natchen.accounting.converter.c2s.UserInfoC2SConverter;
import com.natchen.accounting.exception.GlobalExceptionHandler;
import com.natchen.accounting.manager.UserInfoManager;
import com.natchen.accounting.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserInfoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserInfoManager userInfoManager;
    @Mock
    private UserInfoC2SConverter userInfoC2SConverter;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void teardown() {
        reset(userInfoManager);
        reset(userInfoC2SConverter);
    }

    @Test
    public void testGetUserInfoByUserId() throws Exception {
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";

        val userInfoInCommon = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        val userInfo = com.natchen.accounting.model.service.UserInfo
                .builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        doReturn(userInfoInCommon).when(userInfoManager).getUserInfoByUserId(anyLong());
        doReturn(userInfo).when(userInfoC2SConverter).convert(userInfoInCommon);

        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"id\":100,\"username\":\"hardcore\",\"password\":\"hardcore\"}"));

        verify(userInfoManager).getUserInfoByUserId(anyLong());
        verify(userInfoC2SConverter).convert(userInfoInCommon);
    }

    @Test
    public void testGetUserInfoByUserIdWithInvalidUserId() throws Exception {
        val userId = -100L;

        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"code\":\"INVALID_PARAMETER\",\"errorType\":\"Client\"," +
                        "\"message\":\"The user id -100 is invalid\",\"statusCode\":400}"));

        verify(userInfoManager, never()).getUserInfoByUserId(anyLong());
    }
}
