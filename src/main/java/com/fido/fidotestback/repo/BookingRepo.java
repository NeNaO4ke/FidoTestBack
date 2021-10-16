package com.fido.fidotestback.repo;

import com.fido.fidotestback.domain.Booking;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface BookingRepo extends ReactiveCassandraRepository<Booking, String> {
    Flux<Booking> findByName(String name);

}
