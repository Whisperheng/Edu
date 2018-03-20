package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.common.util.StringUtil;
import com.hank_01.edu.dto.UserDTO;
import com.hank_01.edu.request.UserRequest;
import com.hank_01.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin

public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public EduResponse findUserByCondition(@PathVariable(value = "id" ,required = false)Long id,
                                           @RequestParam(value = "userName" ,required = false)String userName){
        return EduResponse.succResponse(userService.findUserByCondition(id,userName));
    }

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public EduResponse loginByUserName(@RequestParam(value = "userName") String userName,
                                       @RequestParam(value = "password") String password){
        return EduResponse.succResponse(userService.loginByUserAndPassword(userName,password));
    }
}
