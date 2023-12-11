package com.dinesh.taskmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    String isServerUpAndRunning() {
        return "Server is up and running";
    }
}
