package br.com.roberto.springsecurity.rest;

import br.com.roberto.springsecurity.domain.dao.UserDao;
import br.com.roberto.springsecurity.rest.dto.AuthenticationRequest;
import br.com.roberto.springsecurity.security.config.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationRest {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    private final JwtUtils jwtUtils;

    public AuthenticationRest(AuthenticationManager authenticationManager,
                              UserDao userDao,
                              JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDao.findUserByEmail(request.getEmail());

        if (user != null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some Error has Ocurred!!");

    }

}
