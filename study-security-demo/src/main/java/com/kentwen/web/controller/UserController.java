package com.kentwen.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kentwen.dto.User;
import com.kentwen.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 毕文 on 2018/12/23.
 */
@RestController
@Slf4j
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @GetMapping("/user")
    @JsonView(User.UserSimpleView.class)
    public List<User> query() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            User user = new User(String.valueOf(i), "123");
            list.add(user);
        }
        return list;
    }

    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {
//        throw new RuntimeException("user not exist");
        if (id.equals("2")) {
            throw new UserNotExistException("2");
        }
        log.info("进入getInfo服务");
        return new User("Tom", "12");
    }

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user) {
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String msg = fieldError.getField() + " " + error.getDefaultMessage();
//                System.out.println(msg);
//            });
//        }
        System.out.println(user.getBirthday());
        user.setId(1);
        return user;
    }

    @PutMapping("/user/{id}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String msg = fieldError.getField() + " " + error.getDefaultMessage();
                System.out.println(msg);
            });
        }

        return user;
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
