package co.edu.cedesistemassophos.seguimientoUno.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {
    @Id
    private Integer specialityId;
    private String SpecialityName;
}
