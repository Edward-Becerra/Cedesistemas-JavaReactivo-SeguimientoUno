package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private Integer identifier;
    private String documentType;
    private Integer documentNumber;
    private String firstName;
    private String lastName;
    private Integer age;
}
