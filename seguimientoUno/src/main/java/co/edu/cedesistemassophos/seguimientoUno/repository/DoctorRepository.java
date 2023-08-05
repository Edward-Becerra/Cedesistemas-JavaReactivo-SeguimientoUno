package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DoctorRepository extends R2dbcRepository<Doctor, Integer> {
    Flux<Doctor> findBySpeciality(String speciality);
    Flux<Doctor> findByIsActive(boolean status);
    Flux<Doctor> findBySpecialityAndIsActive(String speciality, boolean status);
}
