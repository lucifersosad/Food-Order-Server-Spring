package spring.api.uteating.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("")
    public String lucAnCoc() {
        return "Hungtien - Tình Ca Quê Hương";
    }
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World";
    }
}
