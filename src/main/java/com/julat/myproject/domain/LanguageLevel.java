package com.julat.myproject.domain;

public class LanguageLevel {

    private Integer id;

    private String level;


    public LanguageLevel(Integer id,
                         String level) {
        this.id = id;
        this.level = level;
    }

    public LanguageLevel() {

    }


    public Integer getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLevel(String level) {
        this.level = level;
    }


}
