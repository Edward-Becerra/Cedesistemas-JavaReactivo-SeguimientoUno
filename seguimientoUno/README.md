
# SeguimientoUno Diet-Control

The SeguimientoUno application is a medical monitoring system that allows you to manage doctors, patients, and diets associated with each patient. It is built using the Spring Boot framework and an H2 database to store the data.

## Logic purposed
The SeguimientoUno application allows you to manage doctors, patients and associated diets. Each doctor and patient is related to a "person" entity that stores personal information such as identifier, type of document, document number, names, surnames and age.

The main flows of the application are:

    * Creating a new doctor: When creating a new doctor, the database is searched to see if there is a person associated with the provided identifier. If the person exists, their data is assigned to the doctor and they are recorded as active. If the person is not found, an error is returned indicating that the person was not found.
    * Updating a doctor's information: You can update a doctor's information by his identifier. If the doctor does not exist, an error is returned. Otherwise, the doctor's data is updated with the information provided.
    * Elimination of a doctor: A doctor can be eliminated by his identifier. If the doctor does not exist, an error is returned. Otherwise, the doctor is removed from the database.
    * Get active and inactive doctors: You can get lists of doctors that are marked as active or inactive.
    * Creating a new patient: When creating a new patient, the database is searched to see if there is a person associated with the provided identifier. If the person exists, their data is assigned to the patient. If the person is not found, an error is returned indicating that the person was not found.
    * Updating a patient's information: A patient's information can be updated by his identifier. If the patient does not exist, an error is returned. Otherwise, the patient data is updated with the information provided.
    * Deletion of a patient: A patient can be deleted by his identifier. If the patient does not exist, an error is returned. Otherwise, the patient is removed from the database.
    * Creating a new diet: When creating a new diet, the database is searched to see if there is a patient associated with the provided identifier. If the patient exists, the diet is assigned to it. If the patient is not found, an error is returned indicating that the patient was not found.
    * Updating the information of a diet: You can update the information of a diet by its identifier. If the diet does not exist, an error is returned. Otherwise, the diet data is updated with the information provided.
    * Elimination of a diet: You can delete a diet by its identifier. If the diet does not exist, an error is returned. Otherwise, the diet is removed from the database.

### Note
It is important to note that the application uses an in-memory H2 database, so data will be lost when the application is stopped. For a production deployment, a persistent database should be configured. In addition, more validations and logic can be added based on specific system requirements.




## Dependencies

The application uses the following main dependencies:

    Spring Boot: Spring-based application development framework.
    Spring WebFlux: Framework to create reactive web applications.
    R2DBC: Reactive Database Access Library.
    H2 Database: In-memory database for local development.
    Lombok: Library to reduce boilerplate code.
    Actuator: Provides application monitoring and management features.


## API Reference

#### Get all Persons

```http
  GET /person
```


#### Get person by Id

```http
  GET /person/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of person to fetch |

#### Create person

```http
  POST /person/create-person
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `documentType`      | `string` | **Required**. Document type of person to create|
| `documentNumber`      | `number` | **Required**. Document number of person to create |
| `firstName`      | `string` | **Required**. name of person to create |
| `lastName`      | `string` | **Required**. last name of person to create |
| `age`      | `number` | **Required**. age of person to create |


#### Delete person by Id

```http
  DELETE /person/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of person to Delete |

#### Get all Doctors

```http
  GET /doctor
```

#### Get doctor by Id

```http
  GET /doctor/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of doctor to fetch |

#### Create doctor

```http
  POST /person/create-doctor
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `speciality`      | `string` | **Required**. Doctor's especiality|
| `identifier`      | `number` | **Required**. person id to add doctor attributes|

#### Delete all doctors

```http
  DELETE /doctor
```

#### Delete doctor by Id

```http
  DELETE /doctor/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of doctor to Delete |


#### Update doctor by Id

```http
  PUT /doctor/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of doctor to Update |

#### Get active Doctors

```http
  GET /doctor/get-active
```

#### Get inactive Doctors

```http
  GET /doctor/get-inactive
```

#### Get all Patients

```http
  GET /patient
```


#### Get patient by Id

```http
  GET /patient/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of patient to fetch |

#### Create patient

```http
  POST /patient/create-patient
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `diagnosis`      | `string` | **Required**. diagnosis of a patient|
| `diagnosisDescription`      | `string` | **Required**. Diagnosis Description patient |
| `isAllergic`      | `boolean` | **Required**. If patient is allergic? true : false |
| `room`      | `number` | **Required**. patient's room |
| `isIntern`      | `boolean` | **Required**. If patient is internt? true:false|
| `personId`      | `string` | **Required**. Id of person to add patient attributes|

#### Create diet

```http
  POST /diet/create-diet
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `dietType`      | `string` | **Required**. Diet type to assing a person|
| `dietDescription`      | `number` | **Required**. Diet Description of assigned diet |
| `patientId`      | `number` | **Required**. id of patiend to assing diet |
| `doctorId`      | `number` | **Required**. id of doctor who assing diet |


#### Delete diet by Id

```http
  DELETE /diet/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of diet to Delete |


