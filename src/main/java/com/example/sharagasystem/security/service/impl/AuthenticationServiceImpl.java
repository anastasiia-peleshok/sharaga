package com.example.sharagasystem.security.service.impl;

import com.example.sharagasystem.exception.AuthenticationException;
import com.example.sharagasystem.security.dto.request.AuthenticationRequest;
import com.example.sharagasystem.security.dto.request.RefreshTokenRequest;
import com.example.sharagasystem.security.dto.request.RegistrationRequest;
import com.example.sharagasystem.security.dto.response.AuthenticationResponse;
import com.example.sharagasystem.security.dto.response.UserAuthResponse;
import com.example.sharagasystem.security.exception.TokenIsNotValidException;
import com.example.sharagasystem.security.jwt.JwtService;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.model.mapper.UserMapper;
import com.example.sharagasystem.security.repository.RoleRepository;
import com.example.sharagasystem.security.repository.UserRepository;
import com.example.sharagasystem.security.service.AuthenticationService;
import com.example.sharagasystem.security.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;


    //TODO добавити створення residentDetails i staffDetails для їхнього подальшого входу
    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        User user = new User();
        final Optional<User> userFromDb = userRepository.findByEmail(request.getEmail());
        //&& userFromDb.get().isActive()
        if(userFromDb.isPresent()) {
            throw new AuthenticationException("This email has already been used before");
        }
        user.setRole(roleRepository.findByName(Role.RoleName.ADMIN)
                .orElseThrow(() -> new AuthenticationException("Role Not Found")));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userMapper.mapToUserAuthResponse(savedUser))
                .build();
    }

    @Override
    public AuthenticationResponse refresh(RefreshTokenRequest request) {
        if (jwtService.isTokenExpired(request.getRefreshToken())) {
            throw new TokenIsNotValidException("Refresh token is expired");
        }

        String email = jwtService.extractUsername(request.getRefreshToken());
        User user = userService.findByEmail(email);

        String newAccessToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(newAccessToken)
                .refreshToken(request.getRefreshToken()) // можна згенерувати новий, якщо хочеш
                .user(userMapper.mapToUserAuthResponse(user))
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userService.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        UserAuthResponse userAuthResponse = UserAuthResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().getRoleName())
                .build();

        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .user(userAuthResponse)
                .build();
    }
}
