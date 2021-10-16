package com.fido.fidotestback.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Table("room")
public class Room {

   // private String timeuuid;

    @PrimaryKey
    @Size(max=50)
    @NotEmpty
    private String name;

    @Size(max=256)
    private String location;

    @Max(100)
    @NotNull
    @Positive
    private byte capacity;

    public Room(String name, String location, byte capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }
}
