package co.edu.cedesistemassophos.seguimientoUno.dto;

import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietDTO {
    private Integer DietId;
    private String dietType;
    private String dietDescription;
    private LocalDateTime dietCreatedAt;
    private LocalDateTime dietUpdatedAt;
}