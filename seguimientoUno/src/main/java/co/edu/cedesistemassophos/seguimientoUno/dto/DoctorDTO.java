package co.edu.cedesistemassophos.seguimientoUno.dto;

import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO{
    @Id
    private Integer doctorId;
    private String doctorName;
    private Boolean isActive;
    private List<String> doctorSpecialities;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public DoctorDTO(Doctor doctor, List<Speciality> specialities) {
        this.doctorId = doctor.getDoctorId();
        this.doctorName = doctor.getDoctorName();
        this.isActive = doctor.getIsActive();
        this.doctorSpecialities = specialities.stream()
                .map(Speciality::getSpecialityName)
                .collect(Collectors.toList());
        this.createdAt = doctor.getCreatedAt();
        this.updatedAt = doctor.getUpdatedAt();
    }
}
