package co.edu.cedesistemassophos.seguimientoUno.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDetailDTO {
    @Id
    private Integer prescriptionDetailId;
    private Integer prescriptionId;
    private Integer dietId;
    private String schedule;
    private Integer quantity;
    private String observations;
}
