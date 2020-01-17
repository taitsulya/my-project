package com.julat.myproject.validators;

import com.julat.myproject.Service;
import com.julat.myproject.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class EditValidator extends Service implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {

        ValidationUtils.rejectIfEmpty(err, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(err, "login", "user.login.empty");
        ValidationUtils.rejectIfEmpty(err, "password", "user.password.empty");
        ValidationUtils.rejectIfEmpty(err, "gender", "user.gender.empty");
        ValidationUtils.rejectIfEmpty(err, "birthdate", "user.birthdate.empty");
        ValidationUtils.rejectIfEmpty(err, "firstLanguage", "user.firstLanguage.empty");
        ValidationUtils.rejectIfEmpty(err, "learningLanguage", "user.learningLanguage.empty");
        ValidationUtils.rejectIfEmpty(err, "languageLevel", "user.languageLevel.empty");
        User user = (User) obj;
        if ((user.getPassword().length() > 0) && (user.getPassword().length() < 8)){
            err.rejectValue("password", "user.password.incorrect");
        }

    }

}
