package com.rookie.whisper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.rookie.whisper.mapper"})
public class WhisperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhisperApplication.class, args);
    }

}
