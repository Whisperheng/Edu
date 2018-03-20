package com.hank_01.edu.mapper;

import com.hank_01.edu.Entity.PlayerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PlayerMapper {

    @Select("<script>"+
            " select * from player where id = #{id}"+
            "</script>"
    )
    PlayerEntity findById(@Param("id")Long id);
}
