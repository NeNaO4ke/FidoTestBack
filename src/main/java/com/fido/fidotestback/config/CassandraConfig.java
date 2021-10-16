package com.fido.fidotestback.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class CassandraConfig {
    @Bean
    public CqlSession cqlSession() {
        CqlSession cqlSession;
        cqlSession = CqlSession.builder()
                .withAuthCredentials("cassandra", "cassandra")
                .withKeyspace("fido")
                .withLocalDatacenter("datacenter1")
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .build();

        return cqlSession;
    }

}
