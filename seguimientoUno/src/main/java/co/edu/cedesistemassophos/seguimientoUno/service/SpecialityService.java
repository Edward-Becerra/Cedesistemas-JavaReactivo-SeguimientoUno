package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.model.Speciality;
import co.edu.cedesistemassophos.seguimientoUno.repository.SpecialityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    public SpecialityService(SpecialityRepository specialityRepository){
        this.specialityRepository = specialityRepository;
    }
    public Flux<Speciality> getAllSpecialities(){
        return specialityRepository.findAll();
    }

    public Mono<Speciality> getSpecialityById(Integer id){
        return specialityRepository.findById(id)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Failed to get speciality by Id: "+id).getMostSpecificCause()));
    }
}
