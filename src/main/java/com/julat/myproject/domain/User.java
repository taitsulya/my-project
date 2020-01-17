package com.julat.myproject.domain;

import com.julat.myproject.DBUtils;
import com.julat.myproject.Service;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class User {

    public static User getCurrent(Cookie cookie) {
        if (cookie == null) {
            return null;
        }

        String userUUID = cookie.getValue();

        if (userUUID.length() == 0) {
            return null;
        }

        ResultSet userSet = DBUtils.getResultSet("SELECT * FROM user WHERE SESSION_UUID='" + userUUID + "'");

        try {
            if (userSet != null && userSet.next()) {
                return Service.getUser(userSet);
            }
        } catch (SQLException e) {
            return null;
        }

        return null;
    }

    private Integer id;

    private Integer role;

    private String login;

    private String password;

    private String name;

    private Integer gender;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;

    private Integer firstLanguage;

    private Integer learningLanguage;

    private Integer languageLevel;

    private String image;

    private String sessionUUID;

    private String description;


    public User(Integer id,
                Integer role,
                String login,
                String password,
                String name,
                Integer gender,
                LocalDate birthdate,
                Integer firstLanguage,
                Integer learningLanguage,
                Integer languageLevel,
                String image,
                String sessionUUID,
                String description) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.firstLanguage = firstLanguage;
        this.learningLanguage = learningLanguage;
        this.languageLevel = languageLevel;
        this.image = image;
        this.sessionUUID = sessionUUID;
        this.description = description;
    }

    public User() {

    }


    public Integer getId() {
        return id;
    }

    public Integer getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Integer getFirstLanguage() {
        return firstLanguage;
    }

    public Integer getLearningLanguage() {
        return learningLanguage;
    }

    public Integer getLanguageLevel() {
        return languageLevel;
    }

    public String getImage() {
        return image;
    }

    public String getSessionUUID() {
        return sessionUUID;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setFirstLanguage(Integer firstLanguage) {
        this.firstLanguage = firstLanguage;
    }

    public void setLearningLanguage(Integer learningLanguage) {
        this.learningLanguage = learningLanguage;
    }

    public void setLanguageLevel(Integer languageLevel) {
        this.languageLevel = languageLevel;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
