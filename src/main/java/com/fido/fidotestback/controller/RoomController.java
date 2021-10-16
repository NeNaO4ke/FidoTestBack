package com.fido.fidotestback.controller;

import com.fido.fidotestback.domain.Room;
import com.fido.fidotestback.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/addRoom")
    public Mono<ResponseEntity> addRoom(@Valid @RequestBody Room room){
        return roomService.findByName(room.getName())
                .flatMap(r -> Mono.just(new ResponseEntity(HttpStatus.BAD_REQUEST)))
                .switchIfEmpty(roomService.addOne(room)
                        .then(Mono.just(new ResponseEntity(room,HttpStatus.OK))));
    }
    @DeleteMapping("/deleteRoom/{name}")
    Mono<ResponseEntity> delete(@PathVariable(value = "name") String name){
        return roomService.findByName(name)
                .flatMap(room -> roomService.delete(room)
                        .then(Mono.just(new ResponseEntity(HttpStatus.OK))))
                .switchIfEmpty(Mono.just(new ResponseEntity(HttpStatus.NOT_FOUND)));
    }

}
