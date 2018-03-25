package com.hank_01.edu.service.impl;

import com.hank_01.edu.Entity.UserEntity;
import com.hank_01.edu.common.util.StringUtil;
import com.hank_01.edu.dao.UserDao;
import com.hank_01.edu.dto.UserDTO;
import com.hank_01.edu.enums.UserStatus;
import com.hank_01.edu.enums.errorEnum.UserErrorEnum;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Boolean registerUser(UserDTO dto) {
        this.validate(dto);
        UserDTO localDTO = this.findUserByCondition(null,dto.getUserName());
        if (localDTO != null){
            throw new EduException(UserErrorEnum.USER_COUNT_ALREADY_EXISTED);
        }
        return userDao.registerUser(dto.convert2Entity());
    }

    @Override
    public Boolean updateUser(UserDTO dto) {
        return null;
    }

    @Override
    public Boolean updateStatusById(Long id, UserStatus status) {
        return null;
    }

    @Override
    public UserDTO findUserByCondition(Long id, String userName) {
        if (id == null && StringUtil.isEmpty(userName)){
            LOG.info("参数为空。。。。。");
            return null;
        }
        UserEntity entity =  userDao.findUserByCondition(id,userName);
        if (entity == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.convertfrom(entity);
        LOG.info("已找到用户。。。。。。。。。");
        return userDTO;
    }

    @Override
    public List<UserDTO> findUsersByCondition(String nickName, UserStatus status) {
        return null;
    }

    @Override
    public Boolean loginByUserAndPassword(String userName, String password) {
        UserDTO dto = this.findUserByCondition(null,userName);
        if (dto == null){
            throw new EduException(UserErrorEnum.USER_COUNT_NOT_EXISTED);
        }
        return dto.getPassword().equals(password);
    }

    private void validate(UserDTO dto){
        if (StringUtil.isEmpty(dto.getUserName()) || StringUtil.isEmpty(dto.getPassword())){
            throw new EduException(UserErrorEnum.PARAMETER_ERROR);
        }
    }

}
