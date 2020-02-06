package com.example.springbootes.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 城市实体类
 */
@Data
@Document(indexName = "province", type = "city")
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 城市评分
     */
    private Integer score;
}