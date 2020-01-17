package com.julat.myproject.validators;

import com.julat.myproject.Service;
import com.julat.myproject.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class LoginValidator extends Service implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {

        ValidationUtils.rejectIfEmpty(err, "login", "user.login.empty");
        ValidationUtils.rejectIfEmpty(err, "password", "user.password.empty");

        User user = (User) obj;
        if (((user.getPassword().length() > 0) && (user.getPassword().length() < 8)) || (user.getPassword().length() > 30)){
            err.rejectValue("password", "user.password.incorrect");
        }

        if (loginList(getUserList(getResultSet("select * from user"))).contains(user.getLogin())) {
            if ((user.getPassword().length() > 0) && (!passwordList(getUserList(getResultSet("select * from user"))).contains(user.getPassword()))) {
                err.rejectValue("login", "user.login.incorrect");
            }
        } else if ((user.getLogin().length() > 0) && (user.getPassword().length() >= 8) && (user.getPassword().length() <= 30))  {
            err.rejectValue("login", "user.login.incorrect");
        }


    }

}
