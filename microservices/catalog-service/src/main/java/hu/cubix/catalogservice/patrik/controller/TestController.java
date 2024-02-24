package hu.cubix.catalogservice.patrik.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "catalog.. 1 2 3 4.. ";
    }

    @PostMapping("/test2")
    public String test2() {
        return "catalog.. post..";
    }
}
