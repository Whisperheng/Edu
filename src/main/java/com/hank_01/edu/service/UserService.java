package com.hank_01.edu.service;

import com.hank_01.edu.dto.UserDTO;
import com.hank_01.edu.enums.UserStatus;

import java.util.List;

public interface UserService {
    /**
     * 注册新的后台管理账号
     * @param dto
     * @return Boolean
     */
    Boolean registerUser(UserDTO dto);

    /**
     * 更改账号信息
     * @param dto
     * @return Boolean
     */
    Boolean updateUser(UserDTO dto);

    /**
     * 根据用户ID更改用户状态
     * @param id
     * @param status
     * @return Boolean
     */
    Boolean updateStatusById(Long id , UserStatus status);

    /**
     * 根据参数查找用户
     * @param id
     * @param userName
     * @return UserDTO
     */
    UserDTO findUserByCondition(Long id , String userName);

    /**
     * 根据条件查询用户列表
     * @param nickName
     * @param status
     * @return List<UserDTO>
     */
    List<UserDTO> findUsersByCondition(String nickName , UserStatus status);

    /**
     *
     * @param userName
     * @param password
     * @return Boolean
     */
    Boolean loginByUserAndPassword(String userName, String password);

}
