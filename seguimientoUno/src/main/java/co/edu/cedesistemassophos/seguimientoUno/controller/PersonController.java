package co.edu.cedesistemassophos.seguimientoUno.controller;

import co.edu.cedesistemassophos.seguimientoUno.model.Person;
import co.edu.cedesistemassophos.seguimientoUno.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
public class PersonController {
    PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/")
    public Flux<Person> getAllPeople(){
        return personService.findAllPeople();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> getPersonById(@PathVariable Integer id) {
        return personService.getPersonByIdentifier(id)
                .map(person -> {
                    System.out.println("Person found: " + person);
                    return ResponseEntity.ok(person);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-person")
    public Mono<Person> createPerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePerson(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }
}
