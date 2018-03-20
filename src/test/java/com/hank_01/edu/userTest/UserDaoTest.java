package com.hank_01.edu.userTest;

import com.hank_01.edu.Entity.UserEntity;
import com.hank_01.edu.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseTest {
    @Autowired
    private UserDao dao;

    @Test
    public void test(){
        UserEntity entity = dao.findUserById(1L);
        System.out.print(111);
    }
}
