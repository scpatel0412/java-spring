package com.example.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        WebRequest webRequest = (WebRequest) request; // Cast HttpServletRequest to WebRequest
        Map<String, Object> errorDetails = errorAttributes.getErrorAttributes(
            webRequest,
            org.springframework.boot.web.error.ErrorAttributeOptions.defaults()
        );

        int status = (int) errorDetails.get("status");

        if (status == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute(
                "message",
                "The page you are looking for does not exist."
            );
            return "custom-404";
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute(
                "message",
                "An error occurred on the server. Please try again later."
            );
            return "custom-500";
        }

        model.addAttribute("message", "An unexpected error occurred.");
        return "error"; // Fallback to a generic error page
    }
}
