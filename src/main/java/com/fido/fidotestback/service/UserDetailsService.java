package com.fido.fidotestback.service;

import com.fido.fidotestback.domain.AuthenticatedUser;
import com.fido.fidotestback.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public Mono findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(usr -> new AuthenticatedUser(usr.getUsername(),usr.getRoles(),usr.getPassword()));
    }

}
