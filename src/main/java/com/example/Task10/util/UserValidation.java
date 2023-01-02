package com.example.Task10.util;

import com.example.Task10.models.User;
import com.example.Task10.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidation implements Validator {

    private final UserService userService;

    public UserValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(!userService.add(user)) {
            errors.rejectValue("email","","Пользователь с таким логином занят");
        }
    }
}
