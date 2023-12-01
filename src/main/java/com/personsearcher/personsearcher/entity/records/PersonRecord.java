package com.personsearcher.personsearcher.entity.records;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record PersonRecord(String firstName, String lastName, String nationalInsuranceNumber) {
}
