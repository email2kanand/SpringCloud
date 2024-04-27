package com.micro.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import zipkin2.server.internal.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
@ComponentScan("com.micro")
public class ZipkinServerApplication {

public static void main(String[] args) {
SpringApplication.run(ZipkinServerApplication.class, args);
}
}