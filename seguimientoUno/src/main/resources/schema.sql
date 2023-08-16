DROP TABLE IF EXISTS prescription_detail;
DROP TABLE IF EXISTS prescription;
DROP TABLE IF EXISTS diet;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS speciality;
DROP TABLE IF EXISTS person;


CREATE TABLE IF NOT EXISTS person (
  person_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  document_type VARCHAR(50) NOT NULL,
  document_number INT NOT NULL UNIQUE,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  birth_date TIMESTAMP,
  rol VARCHAR(50),
  age INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS doctor (
  doctor_id SERIAL PRIMARY KEY,
  is_active BOOLEAN NOT NULL,
  speciality_id INT,
  person_id INT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS patient (
  patient_id SERIAL PRIMARY KEY,
  diagnosis VARCHAR(100) NOT NULL,
  diagnosis_description VARCHAR(255) NOT NULL,
  is_allergic BOOLEAN NOT NULL,
  room INT NOT NULL,
  is_interned BOOLEAN NOT NULL,
  date_inning DATE NOT NULL,
  date_out DATE DEFAULT NULL,
  person_id INT
);

CREATE TABLE IF NOT EXISTS speciality (
  speciality_id SERIAL PRIMARY KEY,
  speciality_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS diet (
  diet_id SERIAL PRIMARY KEY,
  diet_type VARCHAR(100) NOT NULL,
  diet_description VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS prescription (
  prescription_id SERIAL PRIMARY KEY,
  patient_id INT,
  doctor_id INT,
  prescription_date DATE,
  observations VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS prescription_detail (
  prescription_detail_id SERIAL PRIMARY KEY,
  prescription_id INT,
  diet_id INT,
  schedule VARCHAR(50),
  quantity INT,
  observations VARCHAR(255)
);

INSERT INTO person (document_type, document_number, first_name, last_name, birth_date, age, rol)
  VALUES
  ('CC', 12345678, 'John', 'Doe', '1988-08-10', 35, 'doctor'),
  ('VISA', 98765432, 'Jane', 'Smith', '1995-08-10', 28, 'doctor'),
  ('NIT', 87654321, 'Michael', 'Johnson', '1978-08-10', 45, 'patient'),
  ('CC', 55555555, 'Emily', 'Brown', '1994-08-10', 29, 'patient'),
  ('CE', 1111111, 'Ema', 'Brown', '1994-08-10', 29, 'doctor'),
  ('CC', 123456789, 'John', 'Doe', '1988-08-10', 35, 'doctor'),
  ('VISA', 987654321, 'Jane', 'Smith', '1995-08-10', 28, 'patient'),
  ('NIT', 876543212, 'Michael', 'Johnson', '1978-08-10', 45, 'patient'),
  ('CC', 555555556, 'Valery', 'Brown', '1994-08-10', 29, 'patient'),
  ('CE', 555555557, 'Valentine', 'Brown', '1994-08-10', 29, 'doctor');


INSERT INTO doctor(speciality_id, is_active, person_id)
  VALUES
  (1, true, 1),
  (2, true, 2),
  (3, true, 5),
  (4, true, 6),
  (5, true, 10);

INSERT INTO patient (diagnosis, diagnosis_description, is_allergic, room, is_interned, date_inning, person_id)
  VALUES
  ('Hypertension', 'High blood pressure', false, 101, true, '2023-08-10', 3),
  ('Flu', 'Respiratory infection', false, 102, true, '2023-08-10', 4),
  ('Diabetes', 'High glucose', true, 103, true, '2023-08-10', 7),
  ('Broken leg', 'Broken leg bone', false, 105, false, '2023-08-10', 8),
  ('Broken arm', 'Broken arm bone', false, 105, false, '2023-08-10', 9);

INSERT INTO speciality (speciality_name)
  VALUES
  ('Cardiology'),
  ('Pediatric'),
  ('Oncology'),
  ('Sociology'),
  ('Urology');

INSERT INTO diet (diet_type, diet_description)
  VALUES
  ('Balanced', 'Balanced diet for the heart'),
  ('Vegetarian', 'Plant-based diets'),
  ('Low in Carbohydrates', 'Diet to control sugar'),
  ('Gluten free', 'Gluten free diet'),
  ('Balanced', 'Balanced diet for the heart');

INSERT INTO prescription (patient_id, doctor_id, prescription_date, observations)
  VALUES
  (3, 2, '2023-08-10', 'Balanced diet for the heart'),
  (4, 1, '2023-08-10', 'Plant-based diets'),
  (7, 5, '2023-08-10', 'Diet to control sugar'),
  (8, 6, '2023-08-10', 'Gluten free diet'),
  (9, 1, '2023-08-10', 'Balanced diet for the heart');

INSERT INTO prescription_detail (prescription_id, diet_id, schedule, quantity, observations)
  VALUES
  (1, 1, 'breakfast', 1, 'No saturated fats'),
  (2, 2, 'breakfast', 1, 'High protein'),
  (3, 3, 'breakfast', 1, 'No sugar'),
  (4, 4, 'breakfast', 1, 'No bread'),
  (5, 5, 'breakfast', 1, 'No saturated fats, no sugar, no bread, high protein');

SELECT * FROM person;
SELECT * FROM doctor WHERE person_id = 2;
SELECT * FROM patient;
SELECT * FROM diet;
SELECT * FROM prescription;
SELECT * FROM prescription_detail;

SELECT p.*, d.*, s.*
   FROM person p
   INNER JOIN doctor d ON p.person_id = d.person_id
   INNER JOIN speciality s ON d.speciality_id = s.speciality_id;

