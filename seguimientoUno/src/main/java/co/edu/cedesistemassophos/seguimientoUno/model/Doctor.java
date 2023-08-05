package co.edu.cedesistemassophos.seguimientoUno.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Person implements DoctorTasks{
    private String speciality;
    private boolean isActive;
    private Integer personId;
}
