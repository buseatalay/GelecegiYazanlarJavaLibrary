package com.homework.turkcell.controller;

import com.homework.turkcell.Entity.Book;
import com.homework.turkcell.Entity.BookGlobalList;
import com.homework.turkcell.Entity.User;
import com.homework.turkcell.Entity.UserGlobalList;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BookController {

    BookGlobalList<Book> bookList = new BookGlobalList<>();
    UserGlobalList<User> userList = new UserGlobalList<>();

    @PostMapping("/books/{addUserId}")
    public Object AddBook(@RequestBody Book book , @PathVariable int addUserId){
        var user = userList.get(addUserId);
        if(user != null && user.isAdmin())
        {
            book.setAvailable(true);
            bookList.add(book);
            System.out.println("Kitap " + user.getFullname() +" tarafından başarıyla eklendi !");
            return book;
        }
        else{
            return HttpStatus.UNAUTHORIZED.toString();
        }
    }

    @GetMapping("/books/{bookId}")
    public Book GetBookById(@PathVariable int bookId)
    {
        var book = bookList.get(bookId);
        return book;
    }

    @GetMapping("/books/getAll")
    public List<Book> GetBookByParams(@RequestParam String title, @RequestParam String author)
    {
        var books = bookList.getAll();
        if(title.toString().isEmpty() && author.toString().isEmpty())
        {
            return books;
        }
        else if(!title.toString().isEmpty() && author.toString().isEmpty()){
            System.out.println("1.koşul");
            System.out.println(title.toLowerCase());
            System.out.println(books.stream().count());
            var aa = books.stream().filter(b->b.getTitle().toLowerCase().equals(title.toLowerCase())).toList();
            return aa;
        }
        else if(title.toString().isEmpty() && !author.toString().isEmpty()){
            System.out.println("2.koşul");
            return books.stream().filter(b->b.getAuthor().toLowerCase().equals(author.toLowerCase())).toList();
        }
        else if(!title.toString().isEmpty() && !author.toString().isEmpty()){
            System.out.println("3.koşul");
            return books.stream().filter(b->b.getAuthor().toLowerCase().equals(author.toLowerCase()) && b.getTitle().toLowerCase().equals(title.toLowerCase())).toList();
        }
        else{
            System.out.println("else koşulu");
            return null;
        }
    }

    @PatchMapping("/books/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book bookPrm)
    {
        var allBookList = bookList.getAll();
        var getBook = allBookList.stream().filter(b->b.getId() == id).findFirst().orElse(null);

        getBook.setAuthor(bookPrm.getAuthor());
        getBook.setTitle(bookPrm.getTitle());

        return bookPrm;
    }

    @DeleteMapping("/books/{id}")
    public void DeleteBook(@PathVariable int id)
    {
        var allBookList = bookList.getAll();
        var getBook = allBookList.stream().filter(b->b.getId() == id).findFirst().orElse(null);

        allBookList.remove(getBook);
        System.out.println("Kitap başarıyla silindi !");
    }

}
