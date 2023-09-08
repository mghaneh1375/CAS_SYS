package com.example.cas_sys.Service;

import com.example.cas_sys.Model.User;

import java.util.Optional;

public interface UserService {

    /**
     * @param login username or email
     * @return Optional User
     */
    Optional<User> getUserByUsername(String login);



}