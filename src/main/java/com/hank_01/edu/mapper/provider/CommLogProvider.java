package com.hank_01.edu.mapper.provider;

import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CommLogProvider {
    private static final Logger LOG = LoggerFactory.getLogger(CommLogProvider.class);
    private static final String TABLE_NAME = "h_comm_log as c";

    public String findByCondition(@Param("agentPlayerId")Long agentPlayerId,
                                  @Param("orderId")Long orderId,
                                  @Param("startTime")Date startTime,
                                  @Param("endTime")Date endTime){
        String sql = new SQL(){
            {
                SELECT("*");

                if (agentPlayerId != null){
                    WHERE("agent_player_id = " +agentPlayerId);
                }
                if (orderId  != null){
                    WHERE("order_id = #{orderId}");
                }
                if (startTime != null){
                    WHERE("update_time >= #{startTime}");
                }
                if (endTime != null){
                    WHERE("update_time <= #{endTime}");
                }
                WHERE("is_deleted = 0");
                FROM(TABLE_NAME);
            }
        }.toString();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Executing sql in method 'findByCondition': \n {}", sql);
        }
        return sql;
    }
}
