package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@Component
@RestController
public class WelcomeController {

    @Autowired
    Daytime aa;

    @GetMapping(path = "/welcome")
    public String welcome() {
        return "It is " + aa.getName() + "now! Welcome to Spring!";
    }
}
// END
