package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @ComponentScan(basePackages = "com.example.demo.config")
public class DemoController {

    @GetMapping("")
    public DemoResponse demoRoute() {
        return new DemoResponse("Hello, welcome to the demo route!", 200);
    }

    // @GetMapping("/error")
    // public String handleError500() {
    //     return "custom-500";  // This would refer to custom-500.html for 500 error pages
    // }
}
