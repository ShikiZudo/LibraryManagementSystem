package com.hexaware.lms.service.impl;

import com.hexaware.lms.config.auth.JwtService;
import com.hexaware.lms.dto.AuthenticationRequest;
import com.hexaware.lms.dto.AuthenticationResponse;
import com.hexaware.lms.dto.RegisterRequest;
import com.hexaware.lms.entity.Authentication;
import com.hexaware.lms.entity.User;
import com.hexaware.lms.repository.AuthenticationRepository;
import com.hexaware.lms.repository.UserRepository;
import com.hexaware.lms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .contactNo(request.getContactNo())
                .gender(request.getGender())
                .email(request.getEmail())
                .noOfBooksLoan(request.getNoOfBooksLoan())
                .build();
        User savedUser = userRepository.save(user);


        var auth = Authentication.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .user(savedUser)
                .build();
        repository.save(auth);

        var jwtToken = jwtService.generateToken(auth);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // above .authenticate() method will call this authentication provider in applicationConfig.java file -

//        public AuthenticationProvider authenticationProvider(){
//            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//            authProvider.setUserDetailsService(userDetailsService());
//            authProvider.setPasswordEncoder(passwordEncoder());
//            return authProvider;
//        }

        // which has our user details(userDetailsService()) from database and password encoder(as password in db is in encoded form)
        // now it will decode the password from database(as it is stored in encoded form. see register method authentication obj)
        // and then match it with the password provided by above .authenticate() method. If they match then the code flow will continue
        // otherwise it will be stopped and a 400-series status code will be given.

        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
