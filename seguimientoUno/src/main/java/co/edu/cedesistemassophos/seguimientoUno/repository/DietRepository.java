package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.model.Diet;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DietRepository extends R2dbcRepository<Diet, Integer> {
    Flux<Diet> findByDietDescriptionContaining(String title);
}
