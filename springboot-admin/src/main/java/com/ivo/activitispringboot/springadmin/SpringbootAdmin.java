package com.ivo.activitispringboot.springadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringbootAdmin {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdmin.class, args);
    }

}
