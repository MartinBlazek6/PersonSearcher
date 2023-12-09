package com.personsearcher.personsearcher.repository;

import com.personsearcher.personsearcher.entity.TestPerson;
import com.personsearcher.personsearcher.entity.records.PersonRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public interface TestPersonRepository extends JpaRepository<TestPerson,Integer> {

//    @Query("SELECT tp FROM TestPerson tp WHERE " +
//            "(:firstName is null or tp.firstName like %:firstName%) and " +
//            "(:lastName is null or tp.lastName like %:lastName%)")
//    List<TestPerson> searchByFirstNameAndLastName(@Param("firstName") String firstName,
//                                                  @Param("lastName") String lastName);


    @Query("SELECT tp FROM TestPerson tp WHERE " +
            "(:firstName is null or tp.firstName like %:firstName%) and " +
            "(:lastName is null or tp.lastName like %:lastName%) and " +
            "(:birthDate is null or SUBSTRING(tp.nationalInsuranceNumber, 1, LENGTH(tp.nationalInsuranceNumber) - 4) = :birthDate)")
    List<TestPerson> searchByFirstNameAndLastNameAndBirthDate(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("birthDate") String birthDate
    );

    static LocalDate convertYYMMDDToDate(String yyMMdd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        return LocalDate.parse(yyMMdd, formatter);
    }

    default List<TestPerson> searchByFirstNameAndLastNameAndBirthDateConverted(
            String firstName,
            String lastName,
            String birthDate) {

        return searchByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate.substring(0, birthDate.length() - 4));
    }
}
