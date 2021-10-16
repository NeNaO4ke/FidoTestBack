package com.fido.fidotestback.controller;

import com.fido.fidotestback.domain.Booking;
import com.fido.fidotestback.service.BookingService;
import com.fido.fidotestback.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class BookingController {
    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @GetMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<Booking> interval(@RequestParam String name, @RequestParam long start, @RequestParam long end) {
        return bookingService.findByName(name)
                .filter(b ->
                        (b.getStart().getTime() >= start && b.getStart().getTime() <= end)
                                || (b.getStart().getTime() <= start && b.getEnd().getTime() >= start)
                                || (b.getStart().getTime() <= end && b.getEnd().getTime() >= end));
    }

    @PostMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity> bookRoom(@Valid @RequestBody Booking booking) {
        long start = booking.getStart().getTime();
        long end = booking.getEnd().getTime();

        return bookingService.findByName(booking.getName())
                .filter(b ->
                        ((b.getStart().getTime() >= start && b.getStart().getTime() <= end)
                                || (b.getStart().getTime() <= start && b.getEnd().getTime() >= start)
                                || (b.getStart().getTime() <= end && b.getEnd().getTime() >= end)))
                .next()
                .flatMap(booking1 -> Mono.just(new ResponseEntity(HttpStatus.BAD_REQUEST)))
                .switchIfEmpty(bookingService.addOne(booking)
                        .then(Mono.just(new ResponseEntity(booking, HttpStatus.OK))));
    }


}
