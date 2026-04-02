package com.noxus.services;

import com.noxus.data.dto.security.AccountCredentialsDTO;
import com.noxus.data.dto.security.TokenDTO;
import com.noxus.repository.UserRepository;
import com.noxus.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public TokenDTO signIn(AccountCredentialsDTO credentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            credentials.getUsername(),
            credentials.getPassword()
        ));

        var user = repository.findByUsername(credentials.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username "+ credentials.getUsername() + " not found");
        }

        return tokenProvider.createAccessToken(credentials.getUsername(), user.getRoles());
    }
}
