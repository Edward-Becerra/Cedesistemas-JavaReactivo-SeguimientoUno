package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.model.Diet;
import co.edu.cedesistemassophos.seguimientoUno.repository.DietRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DietService {

    private final DietRepository dietRepository;

    private DietService(DietRepository dietRepository){
        this.dietRepository = dietRepository;
    }

    public Mono<Diet> getDietById(Integer id) {
        return dietRepository.findById(id);
    }

    public Flux<Diet> getAllDiets() {
        return dietRepository.findAll();
    }

    public Mono<Diet> saveDiet(Diet diet) {
        return dietRepository.save(diet);
    }

    public Mono<Void> deleteAllDiets() {
        return dietRepository.deleteAll();
    }

    public Mono<Void> deleteDietById(Integer id) {
        return dietRepository.deleteById(id);
    }
}
