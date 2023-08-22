package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DoctorRepository extends R2dbcRepository<Doctor, Integer> {
    Mono<Doctor> findByDoctorId(Integer id);
}
