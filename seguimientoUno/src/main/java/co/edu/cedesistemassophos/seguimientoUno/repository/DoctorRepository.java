package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DoctorRepository extends R2dbcRepository<Doctor, Integer> {
    Flux<Doctor> findBySpecialityId(Integer specialityId);
    Flux<Doctor> findByIsActive(boolean status);
    Flux<Doctor> findBySpecialityIdAndIsActive(Integer specialityId, Boolean status);
    Mono<Doctor> findByDocumentNumber(Integer documentNumber);

    @Query("SELECT p.*, d.*, s.* FROM person p " +
            "INNER JOIN doctor d ON p.person_id = d.person_id " +
            "INNER JOIN speciality s ON d.speciality_id = s.speciality_id")
    Flux<DoctorDTO> findAllDoctorsWithDetails();
}
