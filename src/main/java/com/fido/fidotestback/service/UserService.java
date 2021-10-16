package com.fido.fidotestback.service;

import com.fido.fidotestback.domain.User;
import com.fido.fidotestback.repo.UserRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Mono<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Mono<User> addOne(User user) {
        return userRepo.save(user);
    }

    public Mono<Void> delete(User user) {
        return userRepo.delete(user);
    }
}
