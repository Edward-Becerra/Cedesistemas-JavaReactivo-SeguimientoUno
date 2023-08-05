DROP TABLE IF EXISTS diet;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS person;

CREATE TABLE IF NOT EXISTS person (
  identifier INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  document_type VARCHAR(50) NOT NULL,
  document_number INT NOT NULL UNIQUE,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  age INT NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor (
  doctor_id SERIAL PRIMARY KEY,
  speciality VARCHAR(100) NOT NULL,
  is_active BOOLEAN NOT NULL,
  person_id INT NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person(identifier) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS patient (
  patient_id SERIAL PRIMARY KEY,
  diagnosis VARCHAR(100) NOT NULL,
  diagnosis_description VARCHAR(255) NOT NULL,
  is_allergic BOOLEAN NOT NULL,
  room INT NOT NULL,
  is_intern BOOLEAN NOT NULL,
  person_id INT NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person(identifier) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS diet (
  diet_id SERIAL PRIMARY KEY,
  diet_type VARCHAR(100) NOT NULL,
  diet_description VARCHAR(255) NOT NULL,
  patient_id INT NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
  doctor_id INT NOT NULL,
  FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id)
);