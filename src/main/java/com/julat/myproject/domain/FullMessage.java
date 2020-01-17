package com.julat.myproject.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;


public class FullMessage {

    private Integer sender;
    private Integer receiver;
    private String text;
    private LocalDateTime time;
    private String name;
    private String image;

    public FullMessage(Integer sender,
                       Integer receiver,
                       String text,
                       LocalDateTime time,
                       String name,
                       String image) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.time = time;
        this.name = name;
        this.image = image;
    }

    public Integer getSender() {
        return sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }


    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
