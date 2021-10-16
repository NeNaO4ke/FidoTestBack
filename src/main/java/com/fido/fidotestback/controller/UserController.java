package com.fido.fidotestback.controller;

import com.fido.fidotestback.domain.User;
import com.fido.fidotestback.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Set;

import static com.fido.fidotestback.domain.UserRole.ROLE_USER;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

   @PostMapping(value="/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity> add(@Valid @RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(ROLE_USER));
        return userService.findByEmail(user.getEmail())
                .flatMap(r -> Mono.just(new ResponseEntity(HttpStatus.BAD_REQUEST)))
                .switchIfEmpty(userService.addOne(user)
                        .then(Mono.just(new ResponseEntity(user,HttpStatus.OK))));
    }

    @DeleteMapping("/deleteUser/{email}")
    Mono<ResponseEntity> delete(@PathVariable(value = "email") String email){
        return userService.findByEmail(email)
                .flatMap(usr -> userService.delete(usr)
                                .then(Mono.just(new ResponseEntity(HttpStatus.OK))))
                .switchIfEmpty(Mono.just(new ResponseEntity(HttpStatus.NOT_FOUND)));
    }

}
