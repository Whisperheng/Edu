package com.hank_01.edu.userTest;

import com.hank_01.edu.dto.UserDTO;
import com.hank_01.edu.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;
    @Test
    public void findByConditionTest(){
        UserDTO dto = userService.findUserByCondition(1L,null);
        System.out.print(dto.getNickName());
    }
    @Test
    public void registerTest(){
        UserDTO dto = new UserDTO();
        dto.setUserName("哼");
        dto.setPhone("15518632483");
        dto.setNickName("小杨生煎");
        dto.setPassword("ssf123456");
        Boolean result = userService.registerUser(dto);
        System.out.print(result);
    }
}
