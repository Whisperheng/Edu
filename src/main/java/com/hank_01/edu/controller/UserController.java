package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.dto.UserDTO;
import com.hank_01.edu.request.UserRequest;
import com.hank_01.edu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
@Api(description = "后台用户相关 api")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查找用户",notes = "查找用户")
    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public EduResponse findUserByCondition(@ApiParam(value = "用户id")@PathVariable(value = "id" ,required = false)Long id,
                                           @ApiParam(value = "用户名")@RequestParam(value = "userName" ,required = false)String userName){
        return EduResponse.succResponse(userService.findUserByCondition(id,userName));
    }

    @ApiOperation(value = "后台用户注册",notes = "查找后台用户注册玩家")
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    public EduResponse registerUser(@RequestBody UserRequest request){
        UserDTO dto = new UserDTO();
        dto.setUserName(request.getUserName());
        dto.setNickName(request.getNickName());
        dto.setPassword(request.getPassword());
        dto.setPhone(request.getPhone());
        dto.setSex(request.getSex());

        return EduResponse.succResponse(userService.registerUser(dto));
    }

    @ApiOperation(value = "后台用户登录",notes = "后台用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public EduResponse loginByUserName(@ApiParam(value = "用户名")@RequestParam(value = "userName") String userName,
                                       @ApiParam(value = "用户密码")@RequestParam(value = "password") String password){
        return EduResponse.succResponse(userService.loginByUserAndPassword(userName,password));
    }
}
