package com.julat.myproject.controllers;

import com.julat.myproject.Service;
import com.julat.myproject.validators.LoginValidator;
import com.julat.myproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


@Controller
public class LoginController extends Service {

    @Autowired
    private LoginValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@CookieValue(value = "sessionid") Cookie userCookie) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        User currentUser = User.getCurrent(userCookie);
        if (currentUser != null) {
            modelAndView.setViewName("redirect:/profile");
            return modelAndView;
        }
        return modelAndView;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("user") @Validated User user, BindingResult result, HttpServletResponse response) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("login");
        if (!result.hasErrors()) {
            ResultSet userSet = getResultSet("SELECT id FROM user WHERE login='" + user.getLogin() +
                    "' AND password='" + user.getPassword() + "'");

            if (userSet != null && userSet.next()) {
                Integer userId = userSet.getInt("id");

                UUID userUUID = UUID.randomUUID();
                String uuidStr = userUUID.toString();

                execute("UPDATE user SET SESSION_UUID='" + uuidStr + "' WHERE id=" + userId);

                ResultSet userSet2 = getResultSet("SELECT id FROM user WHERE SESSION_UUID='" + uuidStr + "'");

                userSet2.next();

                Cookie cookie = new Cookie("sessionid", uuidStr);

                response.addCookie(cookie);

                modelAndView.setViewName("redirect:/profile");
            }
        }
        return modelAndView;
    }




}
