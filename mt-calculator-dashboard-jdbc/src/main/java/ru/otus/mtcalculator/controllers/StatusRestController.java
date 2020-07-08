package ru.otus.mtcalculator.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusRestController {

    @GetMapping("/")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Smile please! :-D");
    }
}
