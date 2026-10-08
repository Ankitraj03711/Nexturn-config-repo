package com.example.microboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
