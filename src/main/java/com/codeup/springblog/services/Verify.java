package com.codeup.springblog.services;

import com.codeup.springblog.repositories.UserRepository;

public class Verify {

    public static boolean userNameNotExist(UserRepository repo, String name) {
        return repo.findByUsername(name) == null;
    }
//    public static boolean userEmailNotExist(String email) {
//        User userEmailTest = DaoFactory.getUsersDao().findByUserEmail(email);
//        return userEmailTest == null;
//    }
}
