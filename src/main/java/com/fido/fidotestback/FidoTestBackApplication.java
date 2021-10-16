package com.fido.fidotestback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.Http2;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FidoTestBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FidoTestBackApplication.class, args);
    }

    /*
    use keytool from jdk8(11 is not compatible with HTTP2 & TLSv1.3 ciphers)
    keytool -genkey -keyalg RSA -alias selfsigned -keystore D:\jks\new\keystore.jks -storepass password -validity 360 -keysize 2048
    Don`t forget to change path:) or just comment next Bean
    */
    @Bean
    public WebServerFactoryCustomizer<NettyReactiveWebServerFactory> customizer() {
        return factory -> {
            Http2 http2 = new Http2();
            http2.setEnabled(true);
            factory.setHttp2(http2);

            Ssl ssl = new Ssl();
            ssl.setEnabled(true);
            ssl.setKeyStore("D:\\jks\\new\\keystore.jks");
            ssl.setKeyPassword("password");
            ssl.setKeyAlias("selfsigned");
            ssl.setCiphers(new String[]{
                    /* openssl = ECDHE-ECDSA-AES128-GCM-SHA256 */
                    "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",

                    /* REQUIRED BY HTTP/2 SPEC */
                    /* openssl = ECDHE-RSA-AES128-GCM-SHA256 */
                    "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                    /* REQUIRED BY HTTP/2 SPEC */

                    /* openssl = ECDHE-ECDSA-AES256-GCM-SHA384 */
                    "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
                    /* openssl = ECDHE-RSA-AES256-GCM-SHA384 */
                    "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
                    /* openssl = ECDHE-ECDSA-CHACHA20-POLY1305 */
           //         "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256",
                    /* openssl = ECDHE-RSA-CHACHA20-POLY1305 */
           //        "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256",

                    /* TLS 1.3 ciphers */
                    "TLS_AES_128_GCM_SHA256",
                    "TLS_AES_256_GCM_SHA384",
           //         "TLS_CHACHA20_POLY1305_SHA256"
            });
            ssl.setProtocol("Tls");
            ssl.setEnabledProtocols(new String[]{"TLSv1.3", "TLSv1.2"});
            factory.setSsl(ssl);
        };
    }

}
