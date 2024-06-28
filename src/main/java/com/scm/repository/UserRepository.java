package com.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entity.User;

public interface UserRepository extends JpaRepository<User , String> {
    
}
