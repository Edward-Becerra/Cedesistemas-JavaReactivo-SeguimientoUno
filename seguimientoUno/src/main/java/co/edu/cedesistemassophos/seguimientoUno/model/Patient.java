package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient{
    @Id
    private Integer patientId;
    private String documentType;
    private Integer documentNumber;
    private String patientName;
    private LocalDate birthDate;
    private String diagnosis;
    private String diagnosisDescription;
    private boolean isAllergic;
    private Integer room;
    private LocalDate dateInning;
    private LocalDate dateOut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
