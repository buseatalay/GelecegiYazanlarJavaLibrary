package com.homework.turkcell.Entity;

import java.util.ArrayList;
import java.util.List;

public class UserGlobalList<T> {
    private static List<User> globalList = new ArrayList<>();

    public void add(User item) {
        globalList.add(item);
    }

    public List<User> getAll() {
        return (List<User>) globalList;
    }

    public User get(int id) {
        return (User) globalList.stream().filter(u->u.getId() == id).findFirst().orElse(null);
    }

}
