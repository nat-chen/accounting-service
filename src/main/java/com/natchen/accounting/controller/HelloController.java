package com.natchen.accounting.controller;

import com.natchen.accounting.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    private AtomicLong counter = new AtomicLong();

    @GetMapping("v1/greeting")
    public Greeting sayHello(@PathParam("name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format("hello, %s", name));
    }
}
