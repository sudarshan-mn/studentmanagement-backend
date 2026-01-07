package com.sudarshan.studentmanagement.service;

import com.sudarshan.studentmanagement.dto.RegisterRequestDTO;
import com.sudarshan.studentmanagement.model.User;

public interface UserService {

    User register(RegisterRequestDTO dto);

    User login(String username, String password);
}
