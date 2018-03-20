package com.hank_01.edu.dao;

import com.hank_01.edu.Entity.UserEntity;
import com.hank_01.edu.enums.UserStatus;

import java.util.List;

public interface UserDao {
    UserEntity findUserById(Long id);

    UserEntity findUserByCondition (Long id ,String userName);

    Boolean registerUser(UserEntity entity);

    Boolean updateUser(UserEntity entity);

    Boolean updateStatusById(Long id , UserStatus status);

    public List<UserEntity> findUsersByCondition(String nickName, UserStatus status);
}
