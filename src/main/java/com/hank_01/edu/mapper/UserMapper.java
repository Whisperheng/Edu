package com.hank_01.edu.mapper;

import com.hank_01.edu.Entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("<script>"+
            "SELECT * from h_user "+
            "WHERE id = #{id} "+
            "</script>"
    )
    UserEntity findUserById(@Param("id")Long id);

    @Select("<script>"+
            " select * from h_user "+
            " where "+
            "<if test = 'id != null'> id = #{id} </if> "+
            "<if test = 'userName != null'> user_name = #{userName} </if>"+
            "</script>"
    )
    UserEntity findUserByCondition(@Param("id") Long id,
                                   @Param("userName")String userName) ;

    @Insert("<script>"+
            " insert into h_user ( id , user_name , nick_name , password ,phone " +
            " <if test = 'entity.sex != null'> , sex </if>" +
            " , status) " +
            " VALUES (#{entity.id} , #{entity.userName} ,#{entity.nickName} ,#{entity.password} ,#{entity.phone} ," +
            " <if test = 'entity.sex != null'> #{entity.sex} , </if>" +
            " #{entity.status} )" +
            "</script>"
    )
    Boolean insertUser(@Param("entity") UserEntity entity);
}
