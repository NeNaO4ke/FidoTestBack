package com.fido.fidotestback.service;

import com.fido.fidotestback.domain.Booking;
import com.fido.fidotestback.repo.BookingRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;

    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }
    public Flux<Booking> findByName(String name){
        return bookingRepo.findByName(name);
    }
    public Mono<Booking> addOne(Booking booking){
        return bookingRepo.save(booking);
    }
}
