package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person implements PatientTasks{
    private Integer patientId;
    private String diagnosis;
    private String diagnosisDescription;
    private boolean isAllergic;
    private Integer room;
    private boolean isIntern;
    private Person person;
}
