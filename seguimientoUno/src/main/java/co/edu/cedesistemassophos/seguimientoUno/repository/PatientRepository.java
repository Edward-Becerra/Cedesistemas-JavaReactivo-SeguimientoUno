package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.model.Patient;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PatientRepository extends R2dbcRepository<Patient, Integer> {
    Flux<Patient> findByDiagnosis (String diagnosis);
}
