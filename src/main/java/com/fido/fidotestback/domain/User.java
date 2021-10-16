package com.fido.fidotestback.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="email")
@Table("usr")
public class User{

   // private String timeuuid;

    @PrimaryKey
    @Length(max=100)
    @NotEmpty
    private String email;

    @NotEmpty
    @Length(min=6,max=100)
    private String password;

    @Length(max=50)
    @NotEmpty
    private String username;
    private Set<UserRole> roles;

    User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
