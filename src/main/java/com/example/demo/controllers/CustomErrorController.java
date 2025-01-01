package com.example.demo.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    public CustomErrorController() {
        super();
    }
    
    
    @RequestMapping("/error")
    public String handleError() {
        // You can customize this method to redirect or return a custom error page
        return "custom-404";  // This will refer to a view named "custom-404.html" in resources/templates
    }

    // @Override
    // public String getErrorPath() {
    //     return "/error";  // This is the default error page path
    // }

    // @RequestMapping("/not-found")
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // public String handle404() {
    //     return "custom-404"; // Custom 404 page
    // }

    // @RequestMapping("/internal-server-error")
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public String handle500() {
    //     return "custom-500"; // Custom 500 page
    // }

    // @Override
    // public String getErrorPath() {
    //     return "/error"; // You should use this path for any error pages
    // }
}
