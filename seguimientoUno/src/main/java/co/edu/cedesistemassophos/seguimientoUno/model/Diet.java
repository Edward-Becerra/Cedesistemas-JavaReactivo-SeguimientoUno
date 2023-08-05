package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Diet implements DietTasks{
    private Integer DietId;
    private Integer patientId;
    private Integer doctorId;
    private String dietType;
    private String dietDescription;
}
