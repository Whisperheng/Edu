package com.hank_01.edu.playerTest;

import com.hank_01.edu.BaseTest;
import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.dao.PlayerDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerDaoTest extends BaseTest{
    @Autowired
    private PlayerDao dao;

    @Test
    public void findByIdTest(){
        PlayerEntity entity = dao.findPlayerById(1L);
        System.out.print(entity.getAgentType().getName());

    }
}
