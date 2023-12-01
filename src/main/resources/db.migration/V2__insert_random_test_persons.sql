-- db/migration/V2__insert_random_test_persons.sql

-- Insert 20 random users into the test_person table
INSERT INTO test_person (first_name, last_name, national_insurance_number)
VALUES
    ('John', 'Doe', '961012/4321'),
    ('Jane', 'Smith', '970521/1234'),
    ('Alice', 'Johnson', '930706/5678'),
    ('Bob', 'Williams', '880315/9876'),
    ('Emily', 'Brown', '841124/6543'),
    ('David', 'Jones', '910430/3210'),
    ('Sophia', 'Miller', '900615/7890'),
    ('Michael', 'Davis', '810827/4321'),
    ('Olivia', 'Martinez', '871009/8765'),
    ('William', 'Taylor', '950218/1098'),
    ('Emma', 'Anderson', '890502/3546'),
    ('Daniel', 'White', '920724/8765'),
    ('Grace', 'Harris', '900813/2345'),
    ('Henry', 'Clark', '931205/5678'),
    ('Ava', 'Moore', '870321/8910'),
    ('Joseph', 'Lee', '940603/4321'),
    ('Madison', 'Thomas', '860714/7654'),
    ('Ethan', 'Hill', '900926/1098'),
    ('Chloe', 'Scott', '911117/5432'),
    ('Liam', 'Adams', '940828/8765');
