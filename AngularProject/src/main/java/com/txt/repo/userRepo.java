package com.txt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.txt.model.User;

public interface userRepo extends JpaRepository<User, Integer>{

}
