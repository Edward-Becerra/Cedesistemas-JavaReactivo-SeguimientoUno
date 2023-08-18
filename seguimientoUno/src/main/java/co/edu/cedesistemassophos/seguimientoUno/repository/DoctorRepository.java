package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DoctorRepository extends R2dbcRepository<Doctor, Integer> {
    @Query("SELECT d.*, s.* FROM doctor d " +
            "INNER JOIN speciality s ON d.speciality_id = s.speciality_id")
    Flux<DoctorDTO> findAllDoctorsWithDetails();

    @Query("SELECT d.*, s.* FROM doctor d " +
            "INNER JOIN speciality s ON d.speciality_id = s.speciality_id " +
            "WHERE d.doctor_id = :id")
    Mono<DoctorDTO> findDoctorWithDetailsById(@Param("id") Integer id);
}
