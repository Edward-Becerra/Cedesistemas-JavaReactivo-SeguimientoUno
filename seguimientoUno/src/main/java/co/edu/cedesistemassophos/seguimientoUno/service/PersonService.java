package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.model.Person;
import co.edu.cedesistemassophos.seguimientoUno.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Flux<Person> findAllPeople(){
        return personRepository.findAll()
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Any Person found").getMostSpecificCause()));
    }

    public Mono<Person> getPersonByIdentifier(Integer id){
        return personRepository.findById(id)
                .doOnNext(person -> System.out.println("Person found: " + person))
                .doOnError(throwable -> System.err.println("Error while fetching person: " + throwable.getMessage()))
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Failed to get person by Id: "+id).getMostSpecificCause()));
    }

    public Mono<Person> savePerson(Person person){
        return personRepository.save(person)
                .onErrorResume(throwable -> {
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to save person: " + throwable.getMessage(), throwable));
        });
    }

    public Mono<Void> deletePerson(Integer id) {
        return personRepository.deleteById(id)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Failed to delete person with Id:"+id).getMostSpecificCause()));
    }
}
