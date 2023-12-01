package com.personsearcher.personsearcher.service;

import com.personsearcher.personsearcher.entity.TestPerson;
import com.personsearcher.personsearcher.entity.exception.PersonNotFoundException;
import com.personsearcher.personsearcher.entity.records.PersonRecord;
import com.personsearcher.personsearcher.repository.TestPersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final TestPersonRepository testPersonRepository;

    public PersonRecord getPersonDetailsById(Integer id){
        log.info("Gathering info about person with ID: " + id);
        TestPerson person = testPersonRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person with ID: " + id + " not found!"));
        return new PersonRecord(person.getFirstName(), person.getLastName(), person.getNationalInsuranceNumber());
    }

    public void deletePersonById(Integer id){
        log.info("Deleting person with ID: " + id);
        TestPerson person = testPersonRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person with ID: " + id + " not found!"));

        testPersonRepository.delete(person);
    }

}
