package com.example.microboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.microboot.dto.UserRequest;
import com.example.microboot.entity.User;
import com.example.microboot.exception.UserNotFoundException;
import com.example.microboot.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createMapsRequestAndSavesUser() {
        UserRequest request = new UserRequest("Ada Lovelace", "ada@example.com", "+1 555 0100");
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User created = userService.create(request);

        assertEquals("Ada Lovelace", created.getName());
        assertEquals("ada@example.com", created.getEmail());
        assertEquals("+1 555 0100", created.getPhone());
        verify(userRepository).save(created);
    }

    @Test
    void findByIdThrowsWhenUserDoesNotExist() {
        when(userRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(42L));
    }
}
