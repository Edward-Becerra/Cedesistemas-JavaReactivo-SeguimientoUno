package co.edu.cedesistemassophos.seguimientoUno.dto;

import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.model.Person;
import co.edu.cedesistemassophos.seguimientoUno.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO{
    private Integer personId;
    private String documentType;
    private Integer documentNumber;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private Integer age;
    private String rol;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
    private String specialityName;

    public DoctorDTO(Person person, Doctor doctor, Speciality speciality) {
        this.personId = person.getPersonId();
        this.documentType = person.getDocumentType();
        this.documentNumber = person.getDocumentNumber();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.birthDate = LocalDateTime.parse(person.getBirthDate().toString());
        this.age = person.getAge();
        this.rol = person.getRol();
        this.createdAt = LocalDateTime.parse(person.getCreatedAt().toString());
        this.updatedAt = LocalDateTime.parse(person.getUpdatedAt().toString());
        this.isActive = doctor.getIsActive();
        this.specialityName = speciality.getSpecialityName();
    }
}
