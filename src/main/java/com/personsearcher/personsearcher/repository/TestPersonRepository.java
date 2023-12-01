package com.personsearcher.personsearcher.repository;

import com.personsearcher.personsearcher.entity.TestPerson;
import com.personsearcher.personsearcher.entity.records.PersonRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestPersonRepository extends JpaRepository<TestPerson,Integer> {
}
