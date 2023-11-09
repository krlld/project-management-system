package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.dto.AuthenticationRequest;
import by.kirilldikun.projectmanagementsystem.dto.AuthenticationResponse;
import jakarta.validation.Valid;

public interface AuthenticationService {

    AuthenticationResponse register(@Valid AuthenticationRequest authenticationRequest);

    AuthenticationResponse authenticate(@Valid AuthenticationRequest authenticationRequest);
}
