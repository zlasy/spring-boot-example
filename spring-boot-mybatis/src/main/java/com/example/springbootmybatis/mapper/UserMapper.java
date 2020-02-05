package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.enums.UserSexEnum;
import com.example.springbootmybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userName", column = "user_name")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userName", column = "user_name")
    })
    User getOne(Long id);

    @Insert("INSERT INTO users(user_name,password,user_sex) VALUES(#{userName}, #{password}, #{userSex})")
    void insert(User user);

    @Update("UPDATE users SET user_name=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}