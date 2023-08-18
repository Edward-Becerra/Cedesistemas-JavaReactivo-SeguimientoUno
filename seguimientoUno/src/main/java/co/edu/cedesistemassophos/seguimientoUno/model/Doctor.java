package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor{
    @Id
    private Integer doctorId;
    private String doctorName;
    private Boolean isActive;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
