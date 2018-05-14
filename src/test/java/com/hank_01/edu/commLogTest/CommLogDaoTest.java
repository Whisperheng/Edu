package com.hank_01.edu.commLogTest;

import com.hank_01.edu.BaseTest;
import com.hank_01.edu.Entity.CommLogEntity;
import com.hank_01.edu.dao.CommLogDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommLogDaoTest extends BaseTest {

    @Autowired
    private CommLogDao dao;

    @Test
    public void insertTest(){
        CommLogEntity entity = new CommLogEntity();
        entity.setAgentPlayerId(1L);
        entity.setOrderGoldAmount(1111L);
        entity.setOrderId(1L);
        entity.setPayPlayerId(2L);
        entity.setOrderPrice(new BigDecimal(343L));
        Boolean result = dao.insertCommLog(entity);
        System.out.print(result);
    }
    @Test
    public void findByCondition(){

        Date date1 = null;
        Date date2 = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh-mm-ss");
        try {
           date1 = simpleDateFormat.parse("2018-03-30 00:59:04");
           date2 = simpleDateFormat.parse("2018-03-30 23:59:04");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<CommLogEntity> entities = dao.findByCondition(null,null , date1  ,date2);
        System.out.print(entities.size());
    }
}
