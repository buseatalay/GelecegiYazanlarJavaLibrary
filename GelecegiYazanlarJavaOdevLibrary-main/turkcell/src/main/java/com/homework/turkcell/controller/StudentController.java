package com.homework.turkcell.controller;

import com.homework.turkcell.Entity.UserGlobalList;
import com.homework.turkcell.Entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {

    UserGlobalList<User> userList = new UserGlobalList<>();

    @GetMapping("/students")
    public List<User> GetAllStudents()
    {
        var allUserList = userList.getAll();

        var studentList = allUserList.stream()
                .filter(u -> !u.isAdmin())
                .toList();

        return studentList;
    }

    @GetMapping("/students/{id}")
    public User getStudentById(@PathVariable int id) {
        var allUserList = userList.getAll();

        return allUserList.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PatchMapping("/students/{id}")
    public User updateStudent(@PathVariable int id, @RequestBody User userPrm)
    {
        var allUserList = userList.getAll();
        var getUser = allUserList.stream().filter(u->u.getId() == id).findFirst().orElse(null);

        getUser.setFullname(userPrm.getFullname());
        getUser.setUsername(userPrm.getUsername());
        getUser.setPassword(userPrm.getPassword());

        return userPrm;
    }

    @DeleteMapping("/students/{id}")
    public void DeleteStudent(@PathVariable int id)
    {
        var allUserList = userList.getAll();
        var getUser = allUserList.stream().filter(u->u.getId() == id).findFirst().orElse(null);

        allUserList.remove(getUser);
        System.out.println("Kullanıcı başarıyla silindi !");
    }

}

/*
*
* 2) StudentController
• GET /api/students → Tüm öğrencileri listele
• GET /api/students/{id} → Tek öğrenci getir
• PATCH /api/students/{id} → Öğrenci bilgilerini güncelle
• DELETE /api/students/{id} → Öğrenci sil
* */