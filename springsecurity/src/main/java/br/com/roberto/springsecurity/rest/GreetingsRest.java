package br.com.roberto.springsecurity.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsRest {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello From Our API");
    }
    @GetMapping("/say-good-bye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("Good Bye and See You Later");
    }

}
