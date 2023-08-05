package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.model.Person;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends R2dbcRepository<Person, Integer> {

}
