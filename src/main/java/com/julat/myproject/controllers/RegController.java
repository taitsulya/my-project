package com.julat.myproject.controllers;

import com.julat.myproject.validators.RegValidator;
import com.julat.myproject.domain.User;
import com.julat.myproject.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class RegController extends Service {

    @Autowired
    private RegValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(@CookieValue(value = "sessionid") Cookie userCookie) {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        User currentUser = User.getCurrent(userCookie);
        if (currentUser != null) {
            modelAndView.setViewName("redirect:/profile");
            return modelAndView;
        }
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("user") @Validated User user, BindingResult result, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("registration");

        if (!result.hasErrors()) {
            UUID userUUID = UUID.randomUUID();
            String uuidStr = userUUID.toString();

            String sqlStr = "insert into user (ROLE, LOGIN, PASSWORD, NAME, GENDER, BIRTHDATE, FIRST_LANGUAGE, LEARNING_LANGUAGE, LANGUAGE_LEVEL, SESSION_UUID) values (" +
                    2 + " , '" +
                    user.getLogin() + "' , '" +
                    user.getPassword() + "' , '" +
                    user.getName() + "' , " +
                    user.getGender() + " , TO_DATE('" +
                    user.getBirthdate() + "', 'yyyy-MM-dd'), " +
                    user.getFirstLanguage() + " , " +
                    user.getLearningLanguage() + " , " +
                    user.getLanguageLevel() + " , '" +
                    uuidStr + "')";


            execute(sqlStr);

            Cookie cookie = new Cookie("sessionid", uuidStr);

            response.addCookie(cookie);

            modelAndView.setViewName("redirect:/profile");
        }
        return modelAndView;
    }

}
