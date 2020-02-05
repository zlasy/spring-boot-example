package com.example.springbootmybatis.model;

import com.example.springbootmybatis.enums.UserSexEnum;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String password;
    private UserSexEnum userSex;
    private String nickName;

    public User(String userName, String password, UserSexEnum userSex) {
        super();
        this.password = password;
        this.userName = userName;
        this.userSex = userSex;
    }
}
