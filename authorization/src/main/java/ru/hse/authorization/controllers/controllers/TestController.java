package ru.hse.authorization.controllers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.authorization.services.dto.UserInService;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/is-auth")
    public String isAuth() {
        return "Hello!";
    }

}
