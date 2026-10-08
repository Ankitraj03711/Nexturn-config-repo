package com.example.microboot.service;

import java.util.List;

import com.example.microboot.dto.UserRequest;
import com.example.microboot.entity.User;

public interface UserService {

    User create(UserRequest request);

    List<User> findAll();

    User findById(Long id);

    User update(Long id, UserRequest request);

    void delete(Long id);
}
