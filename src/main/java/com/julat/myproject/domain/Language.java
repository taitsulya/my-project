package com.julat.myproject.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Language {

    private Integer id;

    private String name;


    public Language(Integer id,
                String name) {
        this.id = id;
        this.name = name;
    }

    public Language() {

    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
