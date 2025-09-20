package com.homework.turkcell.Entity;

import java.util.ArrayList;
import java.util.List;

public class BookGlobalList<T> {
    private static List<Book> globalList = new ArrayList<>();

    public void add(Book item) {
        globalList.add(item);
    }

    public List<Book> getAll() {
        return (List<Book>) globalList;
    }

    public Book get(int id) {
        return globalList.stream().filter(b->b.getId() == id).findFirst().orElse(null);
    }
}
