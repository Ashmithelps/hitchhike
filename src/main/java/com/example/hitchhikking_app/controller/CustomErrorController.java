package com.example.hitchhikking_app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode", statusCode);
            
            // You can add more details to the model if needed
            String errorMessage = "An unexpected error has occurred.";
            switch (statusCode) {
                case 400:
                    errorMessage = "Bad Request - The request could not be understood or was missing required parameters.";
                    break;
                case 404:
                    errorMessage = "Not Found - The requested resource could not be found.";
                    break;
                case 500:
                    errorMessage = "Internal Server Error - An unexpected error occurred on the server.";
                    break;
                // Add more cases as needed
                default:
                    errorMessage = "An unexpected error has occurred.";
            }
            model.addAttribute("message", errorMessage);
        }
        return "error";
    }
}
