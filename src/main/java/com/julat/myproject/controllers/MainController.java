package com.julat.myproject.controllers;

import com.julat.myproject.Service;
import com.julat.myproject.domain.FullMessage;
import com.julat.myproject.domain.Message;
import com.julat.myproject.validators.EditValidator;
import com.julat.myproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import java.time.temporal.ChronoUnit;


@Controller
public class MainController extends Service{



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(@CookieValue(value = "sessionid") Cookie userCookie) {
        ModelAndView modelAndView = new ModelAndView("index");
        User currentUser = User.getCurrent(userCookie);
        if (currentUser != null) {
            modelAndView.setViewName("redirect:/profile");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@CookieValue(value = "sessionid") Cookie userCookie) {
        sql = "";
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("profile");
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        LocalDate now = LocalDate.now();
        Integer ages = Period.between(currentUser.getBirthdate(), now).getYears();
        modelAndView.addObject("ages", ages);
        modelAndView.addObject("user", currentUser);
        modelAndView.addObject("firstLanguage", getLanguageById(currentUser.getFirstLanguage()));
        modelAndView.addObject("learningLanguage", getLanguageById(currentUser.getLearningLanguage()));
        modelAndView.addObject("languageLevel", getLanguageLevelById(currentUser.getLanguageLevel()));
        return modelAndView;
    }

    private String sql = "";

    @RequestMapping(value = "/community{page}", method = RequestMethod.GET)
    public ModelAndView community(@PathVariable Integer page, @CookieValue(value = "sessionid") Cookie userCookie) {
        final int pageSize = 6;
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("community");
        modelAndView.addObject("page", page);
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        if (sql.equals("")) {
            sql = "select * from user" +
                    " where id <> " + currentUser.getId();
        }
        ResultSet resultSet = getResultSet(sql +
                " limit " + (pageSize + 1) +
                " offset " + pageSize * (page - 1));
        List<User> userList = getUserList(resultSet);
        boolean nextPageIsExist = false;
        if (userList != null && userList.size() > pageSize) {
            nextPageIsExist = true;
            userList.remove(pageSize);
        }
        System.out.println("GET:" + sql +
                " limit " + (pageSize + 1) +
                " offset " + pageSize * (page - 1));
        modelAndView.addObject("user", new User());
        modelAndView.addObject("nextPageIsExist", nextPageIsExist);
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @RequestMapping(value = "/community{page}", method = RequestMethod.POST)
    public ModelAndView community(@PathVariable Integer page, @ModelAttribute("user") User user, @RequestParam Integer from, @RequestParam Integer to, @CookieValue(value = "sessionid") Cookie userCookie) {
        final int pageSize = 6;
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("community");
        modelAndView.addObject("page", page);
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        String date = "";
        LocalDate now = LocalDate.now();
        if (from != null && to != null) {
            date = " and birthdate between TO_DATE('" + now.minusYears(to) + "', 'yyyy-MM-dd') and TO_DATE('" + now.minusYears(from) + "', 'yyyy-MM-dd')";
        } else if (from == null && to != null) {
            date = " and birthdate >= TO_DATE('" + now.minusYears(to) + "', 'yyyy-MM-dd')";
        } else if (from != null) {
            date = " and birthdate <= TO_DATE('" + now.minusYears(from) + "', 'yyyy-MM-dd')";
        } else {
            date = "";
        }
        sql = "select * from user" +
                " where id <> " + currentUser.getId() +
                ((user.getName().equals("") || user.getName() == null) ? "" : " and name like '" + user.getName() + "%'") +
                ((user.getGender() == null) ? "" : " and gender = " + user.getGender()) +
                date +
                ((user.getFirstLanguage() == null) ? "" : " and first_language = " + user.getFirstLanguage()) +
                ((user.getLearningLanguage() == null) ? "" : " and learning_language = " + user.getLearningLanguage()) +
                ((user.getLanguageLevel() == null) ? "" : " and language_level = " + user.getLanguageLevel());
        ResultSet resultSet = getResultSet(sql +
                " limit " + (pageSize + 1) +
                " offset " + pageSize * (page - 1));
        System.out.println("POST:" + sql +
                " limit " + (pageSize + 1) +
                " offset " + pageSize * (page - 1));
        List<User> userList = getUserList(resultSet);
        boolean nextPageIsExist = false;
        if (userList != null && userList.size() > pageSize) {
            nextPageIsExist = true;
            userList.remove(pageSize);
        }
        modelAndView.setViewName("redirect:/community1");
        modelAndView.addObject("nextPageIsExist", nextPageIsExist);
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }


    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView messages(@CookieValue(value = "sessionid") Cookie userCookie) {
        sql = "";
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("messages");
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        ResultSet resultSet = getResultSet("select sender, receiver, text, created_at, name, image from message" +
                " left join user on user.id = message.sender where sender <> " + currentUser.getId() +
                " and receiver = " + currentUser.getId() +
                " union select sender, receiver, text, created_at, name, image from message" +
                " left join user on user.id = message.receiver where receiver <> " + currentUser.getId() +
                " and sender = " + currentUser.getId() +
                " order by created_at desc");

        List<FullMessage> messageList = getFullMessageList(resultSet);
        List<FullMessage> notContain = new LinkedList<>();
        try {
            for (int i = 0; i < messageList.size(); i++) {
                for (int j = i; j < messageList.size(); j++) {
                    if ((i != j) && (messageList.get(i).getName().equals((messageList.get(j).getName()))) && (!notContain.contains(messageList.get(i)))) {
                        notContain.add(messageList.get(j));
                    }
                }
            }
        } catch (Exception ignored) { }
        for (int i = 0; i < notContain.size(); i++) {
            messageList.remove(notContain.get(i));
        }
        modelAndView.addObject("messageList", messageList);
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }



    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("sessionid", "");
        sql = "";
        response.addCookie(cookie);
        return "redirect:/";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView upload(@CookieValue(value = "sessionid") Cookie userCookie) {
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("upload");
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file, @CookieValue(value = "sessionid") Cookie userCookie) {
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("upload");
        /*if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }*/
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        String fullDirPath = "C://Users/HP/Downloads/spring-boot-war-master/myproject/src/main/resources/static/images/" + currentUser.getLogin();
        String imagePath = "/" + currentUser.getLogin() + "/" + file.getOriginalFilename();
        new File(fullDirPath).mkdirs();
        try {
            byte[]bytes = file.getBytes();
            Path path = Paths.get(fullDirPath + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(path.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }



        String sqlStr = "update user set" +
                " image = '" + (file.getOriginalFilename().equals("") ? "/images/image.png" : "/images" + imagePath) + "'" +
                " where SESSION_UUID = '" + currentUser.getSessionUUID() + "'";

        System.out.println(sqlStr);


        execute(sqlStr);

        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }

    @RequestMapping(value = "/user{id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable Integer id, @CookieValue(value = "sessionid") Cookie userCookie) {
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("userpage");
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        ResultSet resultSet = getResultSet("select * from user" +
                " where id = " + id);

        User user = getUserList(resultSet).get(0);
        LocalDate now = LocalDate.now();
        Integer ages = Period.between(user.getBirthdate(), now).getYears();

        if (currentUser.getId().equals(id)) {
            modelAndView.setViewName("redirect:/profile");
        } else {
            modelAndView.addObject("ages", ages);
            modelAndView.addObject("user", user);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("firstLanguage", getLanguageById(user.getFirstLanguage()));
            modelAndView.addObject("learningLanguage", getLanguageById(user.getLearningLanguage()));
            modelAndView.addObject("languageLevel", getLanguageLevelById(user.getLanguageLevel()));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/chat{id}", method = RequestMethod.GET)
    public ModelAndView chat(@PathVariable Integer id, @CookieValue(value = "sessionid") Cookie userCookie) {
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("chat");
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        ResultSet resultSet = getResultSet("select * from message" +
                " where receiver = " + id + " and sender = " + currentUser.getId() + " or" +
                " receiver = " + currentUser.getId() + " and sender = " + id +
                " order by created_at");
        List<Message> messageList = getMessageList(resultSet);
        modelAndView.addObject("messageList", messageList);
        resultSet = getResultSet("select * from user where id = " + id);
        User user = getUserList(resultSet).get(0);
        if (currentUser.getId().equals(id)) {
            modelAndView.setViewName("redirect:/profile");
        } else {
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/chat{id}", method = RequestMethod.POST)
    public ModelAndView chat(@PathVariable Integer id, @RequestParam(name = "message") String message, @CookieValue(value = "sessionid") Cookie userCookie) {
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("redirect:/chat" + id);
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        if (!message.trim().equals("")) {
            execute("insert into message (sender, receiver, text, created_at) values (" +
                    currentUser.getId() + " , " +
                    id + " , '" +
                    message + "' , " +
                    "CURRENT_TIMESTAMP)");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
    public ModelAndView delete(@CookieValue(value = "sessionid") Cookie userCookie, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        User currentUser = User.getCurrent(userCookie);
        if (currentUser == null || currentUser.getRole() != 1) {
            modelAndView.setViewName("redirect:/");
        }
        else {
            execute("delete from user where id = " + id);
        }
        return modelAndView;
    }


}
