package com.natchen.accounting.controller;

import com.natchen.accounting.converter.c2s.UserInfoC2SConverter;
import com.natchen.accounting.exception.InvalidParameterException;
import com.natchen.accounting.manager.UserInfoManager;
import com.natchen.accounting.model.service.UserInfo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@Slf4j
public class UserController {

    private final UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(final UserInfoManager userInfoManager,
                          final UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfoByUserId(@PathVariable("id") Long userId) {
        log.info("Get user info by user id {}", userId);
        if (userId == null || userId <= 0L) {
            throw new InvalidParameterException(String.format("The user id %s is invalid", userId));
        }
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
    }
}
