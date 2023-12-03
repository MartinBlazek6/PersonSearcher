package com.personsearcher.personsearcher.service;

import com.personsearcher.personsearcher.entity.TestPerson;
import com.personsearcher.personsearcher.entity.exception.NationalInsurenceNumberException;
import com.personsearcher.personsearcher.entity.exception.PersonNotFoundException;
import com.personsearcher.personsearcher.entity.records.PersonRecord;
import com.personsearcher.personsearcher.repository.TestPersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final TestPersonRepository testPersonRepository;

    @Value("${webdev.url}")
    private String webdevUrl;


    /**
     * Retrieves details of a person by their ID.
     *
     * @param id The ID of the person.
     * @return A {@link PersonRecord} containing details of the person.
     * @throws PersonNotFoundException if the person with the specified ID is not found.
     */
    public PersonRecord getPersonDetailsById(Integer id){
        log.info("Gathering info about person with ID: " + id);
        TestPerson person = testPersonRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person with ID: " + id + " not found!"));
        return new PersonRecord(person.getFirstName(), person.getLastName(), person.getNationalInsuranceNumber());
    }

    /**
     * Deletes a person by their ID.
     *
     * @param id The ID of the person to be deleted.
     * @throws PersonNotFoundException if the person with the specified ID is not found.
     */
    public void deletePersonById(Integer id){
        log.info("Deleting person with ID: " + id);
        TestPerson person = testPersonRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person with ID: " + id + " not found!"));

        testPersonRepository.delete(person);
    }

    /**
     * Updates details of a person by their ID.
     *
     * @param id            The ID of the person to be updated.
     * @param personRecord  A {@link PersonRecord} containing updated details.
     * @return A {@link PersonRecord} containing the updated details of the person.
     * @throws PersonNotFoundException if the person with the specified ID is not found.
     */
    public PersonRecord updatePersonDetailsById(Integer id, PersonRecord personRecord){
        log.info("Updating person details with ID: " + id);
        TestPerson person = testPersonRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person with ID: " + id + " not found!"));

        person.setFirstName(personRecord.firstName() != null ? personRecord.firstName() : person.getFirstName());
        person.setLastName(personRecord.lastName() != null ? personRecord.lastName() : person.getLastName());

        person = testPersonRepository.save(person);

        return new PersonRecord(person.getFirstName(), person.getLastName(), person.getNationalInsuranceNumber());
    }

    public boolean checkNino(String rc) {
        String url = webdevUrl + "/kontrola-rodneho-cisla/";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("rc", rc);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        String response = new RestTemplate().postForObject(url, requestEntity, String.class);

        if (response == null){
            throw new NationalInsurenceNumberException("Response is null");
        }

        return response.contains(" je OK.");
    }
}
