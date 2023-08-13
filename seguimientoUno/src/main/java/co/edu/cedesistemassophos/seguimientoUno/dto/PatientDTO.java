package co.edu.cedesistemassophos.seguimientoUno.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class PatientDTO extends PersonDTO {
    private String diagnosis;
    private String diagnosisDescription;
    private boolean isAllergic;
    private Integer room;
    private boolean isInterned;
    private LocalDate dateInning;
    private LocalDate dateOut;
}
