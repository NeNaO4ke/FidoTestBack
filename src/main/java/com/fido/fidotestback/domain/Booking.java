package com.fido.fidotestback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("booking")
public class Booking {
    @NotEmpty
    @PrimaryKeyColumn(name="name",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String name;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @PrimaryKeyColumn(name="start",ordinal = 2,type = PrimaryKeyType.CLUSTERED)
    private Date start;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @PrimaryKeyColumn(name="end",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private Date end;
}
