package co.edu.cedesistemassophos.seguimientoUno.controller;

import co.edu.cedesistemassophos.seguimientoUno.model.Speciality;
import co.edu.cedesistemassophos.seguimientoUno.service.SpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/speciality")
public class SpecialityController {
    SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService){
        this.specialityService = specialityService;
    }

    @GetMapping("/")
    public Flux<Speciality> getAllSpecialities(){
        return specialityService.getAllSpecialities();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Speciality>> getSpecialityById(@PathVariable Integer id){
        return specialityService.getSpecialityById(id)
                .map(speciality -> {
                    System.out.println("Speciality found: " + speciality);
                    return ResponseEntity.ok(speciality);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
