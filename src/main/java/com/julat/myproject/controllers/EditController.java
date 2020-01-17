package com.julat.myproject.controllers;

import com.julat.myproject.Service;
import com.julat.myproject.domain.FullMessage;
import com.julat.myproject.domain.Message;
import com.julat.myproject.domain.User;
import com.julat.myproject.validators.EditValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Controller
public class EditController extends Service {


    @Autowired
    private EditValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@CookieValue(value = "sessionid") Cookie userCookie) {
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user", currentUser);
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.addObject("user", currentUser);
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@CookieValue(value = "sessionid") Cookie userCookie, @ModelAttribute("user") @Validated User user, BindingResult result) {
        User currentUser = User.getCurrent(userCookie);
        ModelAndView modelAndView = new ModelAndView("edit");
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        if (!result.hasErrors()) {
            String sqlStr = "update user set" +
                    " login = '" + user.getLogin() + "' ," +
                    " password = '" + user.getPassword() + "' ," +
                    " name = '" + user.getName() + "' ," +
                    " gender = " + user.getGender() + " ," +
                    " birthdate = TO_DATE('" + user.getBirthdate() + "', 'yyyy-MM-dd')," +
                    " first_language = " + user.getFirstLanguage() + " ," +
                    " learning_language = " + user.getLearningLanguage() + " ," +
                    " language_level = " + user.getLanguageLevel() + " ," +
                    " description = '" + user.getDescription() + "'" +
                    " where SESSION_UUID = '" + currentUser.getSessionUUID() + "'";


            execute(sqlStr);

            System.out.println(sqlStr);
            modelAndView.setViewName("redirect:/profile");
        }
        return modelAndView;
    }

}
