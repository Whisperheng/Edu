package com.hank_01.edu.playerTest;

import com.hank_01.edu.BaseTest;
import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.dao.PlayerDao;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.service.PlayerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class PlayerDaoTest extends BaseTest{
    @Autowired
    private PlayerDao dao;
    @Autowired
    private PlayerService service ;

    @Test
    public void findByIdTest(){
        PlayerEntity entity = dao.findPlayerById(1L);
        System.out.print(entity.getNickName());

    }
    @Test
    public void insert(){
        PlayerDTO dto = new PlayerDTO();
        dto.setWeChatName("微信 hank");
        dto.setMoneyCount(new BigDecimal(0L));
        dto.setWeChatId("111");
        dto.setNickName("hanktest2");
        service.createPlayer(dto);
    }


    @Test
    public void insertTest(){
        PlayerEntity entity = new PlayerEntity();
        entity.setMoneyCount(new BigDecimal(0L));
        entity.setNickName("hankcesi1");
        entity.setWeChatId("123456");
        entity.setWeChatName("普通玩家微信");
        entity.setGoldCount(entity.getMoneyCount());
        Boolean result = dao.createPlayer(entity);
        System.out.print(result);
    }
}
