package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    protected Integer personId;
    protected String documentType;
    protected Integer documentNumber;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected String rol;
    protected Integer age;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;


}
