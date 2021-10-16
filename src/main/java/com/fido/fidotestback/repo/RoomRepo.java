package com.fido.fidotestback.repo;

import com.fido.fidotestback.domain.Room;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;


public interface RoomRepo extends ReactiveCassandraRepository<Room, String> {
    Mono<Room> findByName(String name);

}
