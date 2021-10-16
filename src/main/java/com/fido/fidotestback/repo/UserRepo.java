package com.fido.fidotestback.repo;

import com.fido.fidotestback.domain.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;


public interface UserRepo extends ReactiveCassandraRepository<User, String> {
        Mono<User> findByEmail(String email);

        @AllowFiltering
        Mono<User> findByUsername(String name);

}
