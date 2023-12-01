package com.personsearcher.personsearcher.controller;

import com.personsearcher.personsearcher.entity.exception.PersonNotFoundException;
import com.personsearcher.personsearcher.entity.records.PersonRecord;
import com.personsearcher.personsearcher.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public ResponseEntity<?> getDetailsById(@RequestParam Integer id) {
        PersonRecord personRecord;
        try {
            personRecord = personService.getPersonDetailsById(id);
        } catch (PersonNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personRecord, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deletePersonById(@RequestParam Integer id) {
        try {
            personService.deletePersonById(id);
        }catch (PersonNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
