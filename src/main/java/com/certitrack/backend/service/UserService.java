package com.certitrack.backend.service;

import com.certitrack.backend.dto.AuthResponse;
import com.certitrack.backend.dto.GoogleLoginRequest;
import com.certitrack.backend.dto.LoginRequest;
import com.certitrack.backend.dto.RegisterRequest;
import com.certitrack.backend.entity.User;
import com.certitrack.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email already registered", false, null, null);
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("user");
        user.setProvider("manual");

        userRepository.save(user);

        return new AuthResponse("User registered successfully", true, user.getName(), user.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return new AuthResponse("Invalid email or password", false, null, null);
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new AuthResponse("Invalid email or password", false, null, null);
        }

        return new AuthResponse("Login successful", true, user.getName(), user.getRole());
    }

    public AuthResponse googleLogin(GoogleLoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        User user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword("GOOGLE_LOGIN");
            user.setRole("user");
            user.setProvider("google");
            userRepository.save(user);
        }

        return new AuthResponse("Google login successful", true, user.getName(), user.getRole());
    }
}