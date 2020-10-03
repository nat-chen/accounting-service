package com.natchen.accounting.dao;

import com.natchen.accounting.model.persistence.UserInfo;

public interface UserInfoDAO {
    UserInfo getUserInfoById(Long id);
    void createNewUser(String username, String password);
}
