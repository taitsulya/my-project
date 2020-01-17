package com.julat.myproject.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Message {

    private Integer id;

    private Integer sender;

    private Integer receiver;

    private String text;

    private LocalDateTime time;

    public Message(Integer id,
                Integer sender,
                Integer receiver,
                String text,
                LocalDateTime time) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.time = time;
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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
}
