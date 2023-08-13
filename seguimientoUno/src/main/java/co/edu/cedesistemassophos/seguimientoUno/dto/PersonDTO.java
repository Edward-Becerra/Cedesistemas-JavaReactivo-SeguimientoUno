package co.edu.cedesistemassophos.seguimientoUno.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    @Id
    protected Integer personId;
    protected String documentType;
    protected Integer documentNumber;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected Integer age;
    protected String rol;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}

