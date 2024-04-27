package spring.api.uteating.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("")
    public String checkHealth() {
        return "Welcome to Uteating Server";
    }
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World";
    }
}
