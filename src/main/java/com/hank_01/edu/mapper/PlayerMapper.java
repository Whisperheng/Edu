package com.hank_01.edu.mapper;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.enums.AgentLever;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlayerMapper {

    @Select("<script>"+
            " select * from player where id = #{id}"+
            "</script>"
    )
    PlayerEntity findById(@Param("id")Long id);

    @Select("<script>"+
            " select * from player where is_deleted = 0 "+
            "<if test = 'agentType != null '> and agent_lever = #{agentType} </if>"+
            "<if test = 'playStatus != null '> and status = #{playStatus} </if>"+
            "<if test = 'onLineStatus != null' > and online_status = #{onLineStatus} </if>"+
            "</script>"
    )
    List<PlayerEntity> findPlayersByCondition(@Param("agentType")String agentType,
                                              @Param("onLineStatus")String onLineStatus,
                                              @Param("playStatus")String playStatus);

    @Update("<script>"+
            " UPDATE player set update_time = NOW() "+
            " <if test = 'entity.nickName != null '>, nick_name = #{entity.nickName} </if>"+
            " <if test = 'entity.sex != null '>, sex = #{entity.sex} </if>"+
            " <if test = 'entity.moneyCount != null '>, money_count = #{entity.moneyCount} </if>"+
            " <if test = 'entity.goldCount != null '>, gold_count = #{entity.goldCount} </if>"+
            " <if test = 'entity.superLeverCount != null '>, super_lever_count = #{entity.superLeverCount} </if>"+
            " <if test = 'entity.superLeverName != null '>, super_lever_name = #{entity.superLeverName} </if>"+
            " where id = #{entity.id} "+
            " </script> "
    )
    Boolean updatePlayer(@Param("entity") PlayerEntity entity);

    @Update("<script>"+
            " UPDATE player set update_time = NOW() "+
            " <if test = 'agentLever != null '> , agent_lever = #{agentLever} </if>"+
            " where id = #{id}"+
            "</script>"
    )
    Boolean updatePlayerAgentTypeById(@Param("id") Long id,
                                      @Param("agentLever")AgentLever agentLever);

    @Insert("<script>"+
            " INSERT INTO player ("+
            " id , we_chat_id , we_chat_name , nick_name  " +
            " , status , online_status , agent_lever "+
            " , is_deleted , create_time , update_time " +
            " <if test='entity.sex != null'> , sex </if>" +
            " <if test='entity.moneyCount != null'> , money_count </if>" +
            " <if test='entity.goldCount != null'> , gold_count </if>" +
            " <if test='entity.superLeverCount != null'> , super_lever_count </if>" +
            " <if test='entity.superLeverName != null'> , super_lever_name </if>" +
            " ) VALUES(" +
            " #{entity.id} , #{entity.weChatId} ,#{entity.weChatName} , #{entity.nickName} " +
            " , 'USE' , 'ONLINE' , 'LEVER_NULL' "+
            " , 0 , now() , now() " +
            " <if test='entity.sex != null'> , #{entity.sex} </if>" +
            " <if test='entity.moneyCount != null'> , #{entity.moneyCount} </if>" +
            " <if test='entity.goldCount != null'> , #{entity.goldCount} </if>" +
            " <if test='entity.superLeverCount != null'> , #{entity.superLeverCount} </if>" +
            " <if test='entity.superLeverName != null'> , #{entity.superLeverName} </if>" +
            ")"+
            " </script>"
    )
    Boolean insertPlayer(@Param("entity")PlayerEntity entity);
}
