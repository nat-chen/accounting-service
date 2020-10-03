package com.natchen.accounting.dao;

import com.natchen.accounting.dao.mapper.UserInfoMapper;
import com.natchen.accounting.model.persistence.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {
    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoDAOImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }

    @Override
    public void createNewUser(String username, String password) {

    }
}
