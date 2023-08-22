package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.model.DoctorSpecialities;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface DoctorSpecialitiesRepository extends R2dbcRepository<DoctorSpecialities, Integer> {
    Flux<DoctorSpecialities> findByDoctorId(Integer id);
}
