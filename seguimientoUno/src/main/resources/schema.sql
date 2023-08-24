DROP TABLE IF EXISTS prescription_detail;
DROP TABLE IF EXISTS prescription;
DROP TABLE IF EXISTS diet;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS speciality;
DROP TABLE IF EXISTS doctor_specialities;

CREATE TABLE IF NOT EXISTS doctor (
  doctor_id SERIAL PRIMARY KEY,
  doctor_name VARCHAR(100),
  is_active BOOLEAN NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS patient (
  patient_id SERIAL PRIMARY KEY,
  document_type VARCHAR(50) NOT NULL,
  document_number INT NOT NULL UNIQUE,
  patient_name VARCHAR(100) NOT NULL,
  birth_date TIMESTAMP,
  diagnosis VARCHAR(100) NOT NULL,
  diagnosis_description VARCHAR(255) NOT NULL,
  is_allergic BOOLEAN NOT NULL,
  room INT NOT NULL,
  date_inning DATE NOT NULL,
  date_out DATE DEFAULT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS speciality (
  speciality_id SERIAL PRIMARY KEY,
  speciality_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor_specialities (
  speciality_id INT,
  doctor_id INT
);

CREATE TABLE IF NOT EXISTS diet (
  diet_id SERIAL PRIMARY KEY,
  diet_type VARCHAR(100) NOT NULL,
  diet_description VARCHAR(255) NOT NULL
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
  meal_time VARCHAR(50),
  quantity INT,
  observations VARCHAR(255)
);

INSERT INTO doctor (doctor_name, is_active)
  VALUES
  ('John Doe', true),
  ('Jane Smith', true),
  ('Ema Brown', true),
  ('Jonas Dawson', true),
  ('Valentine Brown', true);

INSERT INTO doctor_specialities (speciality_id, doctor_id)
  VALUES
  (2,1),
  (3,1),
  (4,1),
  (1,2),
  (2,2),
  (3,2),
  (2,3),
  (5,3),
  (4,3),
  (1,4),
  (3,4),
  (4,4),
  (1,5),
  (1,5),
  (5,5);

INSERT INTO patient (document_type, document_number, patient_name, birth_date, diagnosis, diagnosis_description, is_allergic, room, date_inning)
  VALUES
  ('NIT', 87654321, 'Michael Johnson', '1978-08-10', 'Hypertension', 'High blood pressure', false, 101, '2023-08-10'),
  ('CC', 55555555, 'Emily Brown', '1994-08-10', 'Flu', 'Respiratory infection', false, 102, '2023-08-10'),
  ('VISA', 987654321, 'Jane Smith', '1995-08-10', 'Diabetes', 'High glucose', true, 103, '2023-08-10'),
  ('NIT', 876543212, 'Michael Johnson', '1978-08-10', 'Broken leg', 'Broken leg bone', false, 105, '2023-08-10'),
  ('CC', 555555556, 'Valery Brown', '1994-08-10', 'Broken arm', 'Broken arm bone', false, 105, '2023-08-10');

INSERT INTO speciality (speciality_name)
  VALUES
  ('Cardiology'),
  ('Neurology'),
  ('Pediatrics'),
  ('Gastroenterology'),
  ('Dermatology'),
  ('Ophthalmology'),
  ('Orthopedics'),
  ('Oncology'),
  ('Endocrinology'),
  ('Psychiatry');

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

INSERT INTO prescription_detail (prescription_id, diet_id, meal_time, quantity, observations)
  VALUES
  (1, 1, 'breakfast', 1, 'No saturated fats'),
  (2, 2, 'breakfast', 1, 'High protein'),
  (3, 3, 'breakfast', 1, 'No sugar'),
  (4, 4, 'breakfast', 1, 'No bread'),
  (5, 5, 'breakfast', 1, 'No saturated fats, no sugar, no bread, high protein');

