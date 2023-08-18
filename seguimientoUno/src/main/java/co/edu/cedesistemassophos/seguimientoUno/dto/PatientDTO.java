package co.edu.cedesistemassophos.seguimientoUno.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
public class PatientDTO{
    @Id
    private Integer patientId;
    private String documentType;
    private Integer documentNumber;
    private String patientName;
    private LocalDate birthDate;
    private String diagnosis;
    private String diagnosisDescription;
    private Boolean isAllergic;
    private Integer room;
    private LocalDate dateInning;
    private LocalDate dateOut;
    private Integer age;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Integer getAge(){
        if(birthDate != null){
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
        }
        return null;
    }
}
