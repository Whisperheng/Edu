package com.hank_01.edu.dao.impl;

import com.hank_01.edu.Entity.UserEntity;
import com.hank_01.edu.common.util.StringUtil;
import com.hank_01.edu.dao.UserDao;
import com.hank_01.edu.enums.UserStatus;
import com.hank_01.edu.enums.errorEnum.UserErrorEnum;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserEntity findUserById(Long id) {
        return mapper.findUserById(id);
    }

    @Override
    public UserEntity findUserByCondition(Long id, String userName) {
        if (id == null && StringUtil.isEmpty(userName)){
           return null;
        }
        if (id != null){
           userName = null;
        }
        return mapper.findUserByCondition(id,userName);
    }

    @Override
    public Boolean registerUser(UserEntity entity) {
        this.validate(entity);
        UserEntity localEntity = this.findUserByCondition(null,entity.getUserName());
        if (localEntity != null){
            throw new EduException(UserErrorEnum.USER_COUNT_ALREADY_EXISTED);
        }
        entity.setId(new Date().getTime());
        entity.setStatus(UserStatus.USE);
        return mapper.insertUser(entity);
    }

    @Override
    public Boolean updateUser(UserEntity entity) {
        return null;
    }

    @Override
    public Boolean updateStatusById(Long id, UserStatus status) {
        return null;
    }

    @Override
    public List<UserEntity> findUsersByCondition(String nickName, UserStatus status) {
        return null;
    }

    private void validate(UserEntity entity){
        if (StringUtil.isEmpty(entity.getUserName())){
            throw new EduException(UserErrorEnum.PARAMETER_ERROR);
        }
        if (StringUtil.isEmpty(entity.getPassword())){
            throw new EduException(UserErrorEnum.PARAMETER_ERROR);
        }
        if (!StringUtil.isCellphone(entity.getPhone())){
            throw new EduException(UserErrorEnum.CELLPHONE_IS_INVALID);
        }
    }
}
