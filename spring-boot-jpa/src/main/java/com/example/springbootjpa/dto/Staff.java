package com.example.springbootjpa.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(nullable = false, unique = true)
    private String userName;
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;
    @NonNull
    @Column(nullable = true)
    private String nickName;
    @NonNull
    @Column(nullable = false)
    private String passWord;
    @NonNull
    @Column(nullable = false)
    private String regTime;

}
