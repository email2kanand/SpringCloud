package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
@RestController
@RequestMapping("/hello")
public class SSEWebflux {
    @GetMapping(value = "/get",produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<String> getData(){
       AtomicInteger val=new AtomicInteger(0);
        Stream<String> stream=Stream.generate(()->{

            try {
               if(val.get()==500){
                   throw new RuntimeException("Limit reached");
               }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "<br/>TEST-"+val.getAndIncrement();
        });
          return Flux
                  .fromStream(stream).delayElements(Duration.ofMillis(500));
    }
    @GetMapping(value = "/get1",produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<String> getData1(){
        return  Flux
                .just("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten").delayElements(Duration.ofMillis(100));

    }

    @GetMapping("/get3")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String> builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }


}

