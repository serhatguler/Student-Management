package com.example.service;

import com.example.payload.request.LoginRequest;
import com.example.payload.response.AuthResponse;
import com.example.security.jwt.JwtUtils;
import com.example.security.service.UserDetailsmpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;


    // ----------------------------LOGIN---------------------------------
    public ResponseEntity<AuthResponse> authenticateUser(LoginRequest loginRequest) {

        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        //Kullaniciyi valida etmek icin authenticationManager nesnesi cagriliyor
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        //valide edilen kullanici contexte atiliyor
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //JWT Token olusturuluyor
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);

        UserDetailsmpl userDetails = (UserDetailsmpl) authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        Optional<String> role = roles.stream().findFirst();

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();
        authResponse.userName(userDetails.getUsername());
        authResponse.token(token.substring(7));
        authResponse.name(userDetails.getName());
        authResponse.ssn(userDetails.getSsn());

        role.ifPresent(authResponse::role);

        return ResponseEntity.ok(authResponse.build());

    }
}
