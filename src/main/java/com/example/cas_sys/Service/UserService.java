package com.example.cas_sys.Service;

import my.common.commonkoochita.Model.User;

import java.util.Optional;

public interface UserService {

    /**
     * @param login username or email
     * @return Optional User
     */
    Optional<User> getUserByUsername(String login);



}