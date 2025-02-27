package com.scm.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.scm.entity.User;
import java.util.List;

@Service
public interface UserService {

    /**
     * Create User
     */
    
    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);



}
