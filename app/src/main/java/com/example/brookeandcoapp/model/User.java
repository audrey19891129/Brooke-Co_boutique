package com.example.brookeandcoapp.model;

public class User extends Client{

    public static Client user;

    public User() {
    }

    public static Client getUser() {
        return user;
    }

    public static void setUser(Client user) {
        User.user = user;
    }
}
