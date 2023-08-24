package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.time.Period;

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
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Transient
    private Integer age;

    public Integer getAge(){
        if(birthDate != null){
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
        }
        return null;
    }
}
