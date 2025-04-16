package com.example.Hello_world;
import com.example.helloworld.model.greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/api/greeting")
    public greeting greetingJson(@RequestParam(value = "name",
    defaultValue = "World") String name) {
        return new greeting(counter.incrementAndGet(),
                String.format(TEMPLATE, name));
    }
}
