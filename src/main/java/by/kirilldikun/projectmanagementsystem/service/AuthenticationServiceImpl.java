package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.dto.AuthenticationRequest;
import by.kirilldikun.projectmanagementsystem.dto.AuthenticationResponse;
import by.kirilldikun.projectmanagementsystem.entity.Role;
import by.kirilldikun.projectmanagementsystem.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(@Valid AuthenticationRequest authenticationRequest) {
        User user = User.builder()
                .username(authenticationRequest.username())
                .password(passwordEncoder.encode(authenticationRequest.password()))
                .role(Role.USER)
                .build();
        userService.save(user);
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticationResponse authenticate(@Valid AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        User user = userService.findByUsername(authenticationRequest.username());
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }
}
