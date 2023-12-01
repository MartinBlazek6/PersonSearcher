-- db/migration/V1__create_test_person_table.sql

CREATE TABLE test_person (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             first_name VARCHAR(255),
                             last_name VARCHAR(255),
                             national_insurance_number VARCHAR(255)
);
