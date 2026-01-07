package com.sudarshan.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sudarshan.studentmanagement.dto.RegisterRequestDTO;
import com.sudarshan.studentmanagement.exception.StudentNotFoundException;
import com.sudarshan.studentmanagement.model.User;
import com.sudarshan.studentmanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequestDTO dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_" + dto.getRole());

        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new StudentNotFoundException("Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new StudentNotFoundException("Invalid username or password");
        }

        return user;
    }
}
