package com.personsearcher.personsearcher.service;

import com.personsearcher.personsearcher.entity.exception.NationalInsurenceNumberException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Service
public class ValidationService {

    @Value("${webdev.url}")
    private String webdevUrl;


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
