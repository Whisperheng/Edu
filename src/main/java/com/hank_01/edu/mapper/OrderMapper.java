package com.hank_01.edu.mapper;

import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.enums.OrderStatus;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    @Select("<script>" +
            " select * from h_order where id = #{id} " +
            "</script>"
    )
    OrderEntity findOrderById(@Param("id")Long id);

    @Insert("<script>" +
            "insert into h_order (" +
            " id , player_id , player_name , gold_count , order_price , status " +
            " ) values (" +
            " #{entity.id} , #{entity.playerId} , #{entity.playerName} , #{entity.goldCount} , #{entity.orderPrice} , #{entity.status}" +
            " )" +
            "</script>")
    Boolean createOrder(@Param("entity") OrderEntity entity);

    @Update("<script>" +
            "UPDATE h_order set status = #{status} where id = #{id}" +
            "</script>")
    Boolean updateOrderStatusById(@Param("id")Long id ,
                                  @Param("status")OrderStatus status);
}
