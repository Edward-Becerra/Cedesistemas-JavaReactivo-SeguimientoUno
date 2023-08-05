package co.edu.cedesistemassophos.seguimientoUno.controller;

import co.edu.cedesistemassophos.seguimientoUno.model.Diet;
import co.edu.cedesistemassophos.seguimientoUno.service.DietService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/diet")
public class DietController {
    DietService dietService;

    public DietController(DietService dietService){
        this.dietService = dietService;
    }
    @GetMapping("/{id}")
    public Mono<Diet> getDoctorById(@PathVariable Integer id){
        return dietService.getDietById(id);
    }

    @GetMapping("/")
    public Flux<Diet> getAllDiets(){
        return dietService.getAllDiets();
    }

    @PostMapping("/create-diet")
    public Mono<Diet> createDiet(@RequestBody Diet diet){
        return dietService.saveDiet(diet);
    }

    @DeleteMapping("/")
    public Mono<Void> deleteAllDiets(){
        return dietService.deleteAllDiets();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDietById(@PathVariable Integer id){
        return dietService.deleteDietById(id);
    }
}
