package com.example.microboot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microboot.dto.UserRequest;
import com.example.microboot.entity.User;
import com.example.microboot.exception.UserNotFoundException;
import com.example.microboot.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserRequest request) {
        User user = new User();
        applyRequest(user, request);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User update(Long id, UserRequest request) {
        User user = findById(id);
        applyRequest(user, request);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    private void applyRequest(User user, UserRequest request) {
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPhone(request.phone());
    }
}
