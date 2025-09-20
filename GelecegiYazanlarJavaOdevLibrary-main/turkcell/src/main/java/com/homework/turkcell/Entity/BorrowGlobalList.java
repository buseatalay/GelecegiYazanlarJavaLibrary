package com.homework.turkcell.Entity;


import java.util.ArrayList;
import java.util.List;

public class BorrowGlobalList<T> {
    private static List<Borrow> globalList = new ArrayList<>();

    public void add(Borrow item) {
        globalList.add(item);
    }

    public List<Borrow> getAll() {
        return (List<Borrow>) globalList;
    }

    public Borrow get(int id) {
        return globalList.stream().filter(b->b.getId() == id).findFirst().orElse(null);
    }
}
