package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.model.Person;
import co.edu.cedesistemassophos.seguimientoUno.model.Speciality;
import co.edu.cedesistemassophos.seguimientoUno.repository.DoctorRepository;
import co.edu.cedesistemassophos.seguimientoUno.repository.PersonRepository;
import co.edu.cedesistemassophos.seguimientoUno.repository.SpecialityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DoctorService {

    private static final Logger logger = Logger.getLogger(DoctorService.class.getName());
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Flux<DoctorDTO> findAllDoctors(){
        return doctorRepository.findAllDoctorsWithDetails()
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Any Doctor found").getMostSpecificCause()));
    }

    public Mono<Doctor> getDoctorById(Integer id){
        return doctorRepository.findById(id)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Failed to get Doctor by Id: "+id).getMostSpecificCause()));
    }
    public Mono<Doctor> createDoctor(Doctor doctor){
        return doctorRepository.save(doctor)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Doctor can not be created.").getMostSpecificCause()));
    }

    public Mono<Void> deleteDoctorById(Integer id){
        return doctorRepository.deleteById(id)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Failed to delete Doctor with Id:"+id).getMostSpecificCause()));
    }
}