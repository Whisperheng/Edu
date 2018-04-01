package com.hank_01.edu.mapper;

import com.hank_01.edu.Entity.CommLogEntity;
import com.hank_01.edu.mapper.provider.CommLogProvider;
import org.apache.ibatis.annotations.*;
import java.util.Date;
import java.util.List;

@Mapper
public interface CommLogMapper {

    @Select("<script>" +
            " select * from h_comm_log where id = #{id} and is_deleted = 0" +
            "</script>")
    CommLogEntity findById(@Param("id")Long id);

    @Insert("<script>" +
            " insert into h_comm_log (" +
            " id , agent_player_id ,pay_player_id ," +
            " order_id ,order_price ,order_gold_amount ,reward_amount " +
            " ) values (" +
            " #{entity.id}, #{entity.agentPlayerId},#{entity.payPlayerId}," +
            " #{entity.orderId}, #{entity.orderPrice}, #{entity.orderGoldAmount} , #{entity.rewardAmount}" +
            ")" +
            "</script>")
    Boolean insert(@Param("entity")CommLogEntity entity);

    @SelectProvider(type = CommLogProvider.class,method = "findByCondition")
    List<CommLogEntity> findByCondition(@Param("agentPlayerId")Long agentPlayerId,
                                        @Param("orderId")Long orderId,
                                        @Param("startTime")Date startTime,
                                        @Param("endTime")Date endTime);
}
