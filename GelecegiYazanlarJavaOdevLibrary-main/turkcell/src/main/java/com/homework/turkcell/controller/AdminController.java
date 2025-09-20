package com.homework.turkcell.controller;

import com.homework.turkcell.Entity.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    UserGlobalList<User> userList = new UserGlobalList<>();
    BookGlobalList<Book> bookList = new BookGlobalList<>();
    BorrowGlobalList<Borrow> borrowList = new BorrowGlobalList<>();


    @GetMapping("/borrows")
    public List<Borrow> GetAllBorrow(){
        return borrowList.getAll();
    }

    @GetMapping("/stats")
    public String Stats()
    {
        var totalBookCount = bookList.getAll().stream().count();
        var activeStudents = userList.getAll().stream().filter(u->!u.isAdmin()).toList();
        var borrowCount = borrowList.getAll().stream().count();

        StringBuilder sb = new StringBuilder();

        sb.append("Toplam kitap sayısı: ").append(totalBookCount).append("\n");
        sb.append("Aktif öğrenciler: ").append("\n");
        for(var student : activeStudents){
            sb.append(student.getFullname());
            sb.append("\n");
        }
        sb.append("Ödünç alınan kitap sayısı: ").append(borrowCount);

        String result = sb.toString();
        return result;
    }


}
