package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//Board table 에 관한 설정
@Entity
@Data
public class Board {

    @Id //PK의미
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //mysql, mariadb 에서 사용
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;
}
