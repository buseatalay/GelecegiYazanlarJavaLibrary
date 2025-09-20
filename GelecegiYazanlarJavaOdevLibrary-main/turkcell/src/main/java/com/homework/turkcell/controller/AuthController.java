package com.homework.turkcell.controller;

import com.homework.turkcell.Entity.UserGlobalList;
import com.homework.turkcell.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    UserGlobalList<User> userList = new UserGlobalList<User>();

    @PostMapping("/register")
    public User Register(@RequestBody User user)
    {
        userList.add(user);
        System.out.println( user.getFullname() + "isimli öğrenci kaydı başarıyla gerçekleşmiştir.");
        return user;
    }


    @PostMapping("/login")
    public String Login(@RequestBody User user)
    {

        if (user.getFullname() != null && user.getPassword() != null) {
            for (User getUser : userList.getAll()) {
                if (user.getFullname().equals(getUser.getFullname()) &&
                        user.getPassword().equals(getUser.getPassword())) {
                    return "Giriş başarılı. Token : " + getUser.getId() + "?_Turkcell..";
                }
            }
        }

        return HttpStatus.UNAUTHORIZED.toString();
    }
}
