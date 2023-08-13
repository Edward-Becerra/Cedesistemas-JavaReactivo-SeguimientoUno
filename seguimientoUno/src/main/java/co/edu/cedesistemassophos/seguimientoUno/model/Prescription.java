package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    private Integer prescriptionId;
    private Integer patientId;
    private Integer doctorId;
    private LocalDateTime prescriptionDate;
    private String observations;
}
