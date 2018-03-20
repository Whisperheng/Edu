package com.hank_01.edu;

import com.hank_01.edu.EduApplication;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EduApplication.class})
@ComponentScan(basePackages = {"com.hank_01.edu.dao","com.hank_01.edu.service"})
@MapperScan(basePackages = {"com.hank_01.edu.mapper"})
public class BaseTest {
//    @Autowired
//    private UserDao dao;

  /*  @Test
    public void test1(){
        UserEntity userEntity = dao.findUserById(1L);
        System.out.print(1);
    }*/
}
