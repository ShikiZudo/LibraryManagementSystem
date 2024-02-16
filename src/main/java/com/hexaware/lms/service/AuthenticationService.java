package com.hexaware.lms.service;


import com.hexaware.lms.dto.AuthenticationRequest;
import com.hexaware.lms.dto.AuthenticationResponse;
import com.hexaware.lms.dto.RegisterRequest;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
