package com.scut.moonlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@ServletComponentScan
public class MoonLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonLoginApplication.class, args);

    }

}
