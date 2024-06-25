
package com.example.demo.controller;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/sse/mvc")
public class SSEWebMvcController {
   private static final String[] WORDS = "The quick brown fox jumps over the lazy dog.".split(" ");
   private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
   @GetMapping(path = "/words", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   SseEmitter getWords() {
     System.out.println(Thread.currentThread().getName());
       SseEmitter emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
               for (int i = 0; i < WORDS.length; i++) {
                   emitter.send(WORDS[i]);
                   TimeUnit.SECONDS.sleep(1);
                   System.out.println(Thread.currentThread().getName());
               }
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });
       System.out.println(Thread.currentThread().getName());
       return emitter;
   }
}
/***
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <title>Server-Sent Events client example with EventSource</title>
</head>
<body>
<script>
   if (window.EventSource == null) {
       alert('The browser does not support Server-Sent Events');
   } else {
       var eventSource = new EventSource('/sse/mvc/words');
       eventSource.onopen = function () {
           console.log('connection is established');
       };
       eventSource.onerror = function (error) {
           console.log('connection state: ' + eventSource.readyState + ', error: ' + event);
       };
       eventSource.onmessage = function (event) {
           console.log('id: ' + event.lastEventId + ', data: ' + event.data);
           if (event.data.endsWith('.')) {
               eventSource.close();
               console.log('connection is closed');
           }
       };
   }
</script>
</body>
</html>
**/