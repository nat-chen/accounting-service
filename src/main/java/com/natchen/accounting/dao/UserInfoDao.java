package com.natchen.accounting.dao;

import com.natchen.accounting.model.persistence.UserInfo;

public interface UserInfoDao {
    UserInfo getUserInfoById(Long id);
    void createNewUser(String username, String password);
}
