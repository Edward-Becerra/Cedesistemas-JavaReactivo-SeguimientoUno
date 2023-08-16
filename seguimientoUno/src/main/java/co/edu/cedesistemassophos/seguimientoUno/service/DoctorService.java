package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.repository.DoctorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    public Mono<DoctorDTO> getDoctorById(Integer id){
        return doctorRepository.findDoctorWithDetailsById(id)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Failed to get Doctor by Id: "+id).getMostSpecificCause()));
    }

    public Mono<Void> updateDoctorById(Integer id, DoctorDTO doctorDTO){
        return doctorRepository.updateDoctorDetails(id, doctorDTO.getIsActive())
                        .then(doctorRepository.updatePersonDetails(id, doctorDTO.getFirstName(), doctorDTO.getLastName()));
                /*.then(doctorRepository.findDoctorWithDetailsById(id))
                .flatMap(existingDoctor -> {
                    existingDoctor.setUpdatedAt(LocalDateTime.now());
                    return doctorRepository.save(existingDoctor);
                });*/
    }

    public Mono<Void> deleteDoctorById(Integer id){
        return doctorRepository.deleteDoctorById(id)
                .then(doctorRepository.deletePersonById(id));
    }


    public Mono<Doctor> createDoctor(Doctor doctor){
        return doctorRepository.save(doctor)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Doctor can not be created.").getMostSpecificCause()));
    }
}