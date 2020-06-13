package com.az.io.movieapi.service;

import com.az.io.movieapi.model.User;

public interface UserService {

    void addUser(User user);

    User getUser(String userId);

    void deleteUser(String userId);


}
