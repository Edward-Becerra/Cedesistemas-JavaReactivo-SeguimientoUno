package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface DoctorRepository extends R2dbcRepository<Doctor, Integer> {
    Mono<Doctor> findByDoctorId(Integer id);
    Flux<Doctor> findByIsActive(Boolean isActive);

    @Query("SELECT * FROM doctor d INNER JOIN doctor_specialities ds ON d.doctor_id = ds.doctor_id INNER JOIN speciality s ON ds.speciality_id = s.speciality_id WHERE s.speciality_name IN (:specialities)")
    Flux<Doctor> findBySpecialities(@Param("specialities") List<String> specialities);
}

