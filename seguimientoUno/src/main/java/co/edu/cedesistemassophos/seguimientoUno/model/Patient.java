package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person{
    private String diagnosis;
    private String diagnosisDescription;
    private boolean isAllergic;
    private Integer room;
    private boolean isInterned;
    private LocalDate dateInning;
    private LocalDate dateOut;
}
