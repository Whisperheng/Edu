package com.hank_01.edu.mapper;

import com.hank_01.edu.Entity.PlayerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
