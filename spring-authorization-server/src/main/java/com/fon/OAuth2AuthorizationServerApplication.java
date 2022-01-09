package com.fon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Paths;

@SpringBootApplication
public class OAuth2AuthorizationServerApplication {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", Paths.get(new ClassPathResource("keystore/truststorenew.p12").getURI()).toString());
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
    }

}