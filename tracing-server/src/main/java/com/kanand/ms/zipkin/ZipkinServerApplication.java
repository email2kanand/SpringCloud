package com.kanand.ms.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@ComponentScan("com.kanand.ms")
public class ZipkinServerApplication {

public static void main(String[] args) {
SpringApplication.run(ZipkinServerApplication.class, args);
}
}