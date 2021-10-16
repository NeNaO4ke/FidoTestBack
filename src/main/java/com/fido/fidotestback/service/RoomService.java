package com.fido.fidotestback.service;

import com.fido.fidotestback.domain.Room;
import com.fido.fidotestback.repo.RoomRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RoomService {
    private final RoomRepo roomRepo;

    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public Mono<Room> findByName(String name){
        return roomRepo.findByName(name);
    }
    public Mono<Room> addOne(Room room){
        return roomRepo.save(room);
    }
    public Mono<Void> delete(Room room){
        return roomRepo.delete(room);
    }
}
