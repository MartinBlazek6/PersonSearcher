package com.personsearcher.personsearcher.controller;

import com.personsearcher.personsearcher.service.PersonService;
import com.personsearcher.personsearcher.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/validation")
public class ValidationController {

    private final ValidationService validationService;


    @PostMapping("/checkNino")
    public ResponseEntity<String> checkNino(@RequestParam String rc) {
        return new ResponseEntity<>(validationService.checkNino(rc)
                ? "NINO is valid"
                : "NINO is not valid", HttpStatus.OK);
    }
}
