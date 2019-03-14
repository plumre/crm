package com.cahodental.crm.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.cahodental.crm.core",
        "com.cahodental.crm.service",
        "com.cahodental.crm.admin"
        })
public class CrmAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmAdminApplication.class, args);
    }

}
