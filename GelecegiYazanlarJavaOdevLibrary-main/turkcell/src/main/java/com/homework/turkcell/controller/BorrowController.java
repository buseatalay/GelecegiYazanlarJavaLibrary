package com.homework.turkcell.controller;

import com.homework.turkcell.Entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class BorrowController {

    BookGlobalList<Book> bookList = new BookGlobalList<>();
    UserGlobalList<User> userList = new UserGlobalList<>();
    BorrowGlobalList<Borrow> borrowList = new BorrowGlobalList<>();


    @PostMapping("/borrow/{bookId}/{studentId}")
    public void AddBarrow(@PathVariable int bookId, @PathVariable int studentId, @RequestBody Borrow borrow)
    {
        var book = bookList.get(bookId);
        var student = userList.get(studentId);
        if(book == null || student == null)
        {
            System.out.println("Böyle bir kitap veya öğrenci yok !");
            return;
        }

        if(!book.isAvailable())
        {
            System.out.println("Bu kitap zaten alınmış !");
            return;
        }

        book.setAvailable(false);

        borrow.setBookId(bookId);
        borrow.setStudentId(studentId);
        borrowList.add(borrow);

        System.out.println(book.getTitle() + " başlıklı kitap, " + student.getFullname() + " isimli kişiye ödünç verildi !");

    }

    @GetMapping("/borrow/{userId}")
    public List<Book> GetUserBorrowBooks(@PathVariable int userId){
        var borrows = borrowList.getAll();

        List<Book> userBorrowBooks = new ArrayList<>();

        var userBarrows = borrows.stream().filter(b->b.getStudentId() == userId).toList();

        for(var item : userBarrows){
            userBorrowBooks.add(bookList.get(item.getBookId()));
        }

        return userBorrowBooks;
    }

    @PostMapping("/borrow/{bookId}")
    public void ReturnBook(@PathVariable int bookId)
    {
        var book = bookList.get(bookId);
        var allBorrows = borrowList.getAll();
        if(book == null)
        {
            System.out.println("Kitap bulunamadı !");
            return;
        }
        else{
            book.setAvailable(true);

            var filteredBarrows = allBorrows.stream().filter(b->b.getBookId() == bookId);
            allBorrows.remove(filteredBarrows);

            System.out.println("Kitap başarıyla geri alındı !");

        }
    }
}
