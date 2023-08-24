package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.model.DoctorSpecialities;
import co.edu.cedesistemassophos.seguimientoUno.repository.DoctorRepository;

import co.edu.cedesistemassophos.seguimientoUno.repository.DoctorSpecialitiesRepository;
import co.edu.cedesistemassophos.seguimientoUno.repository.SpecialityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DoctorService {

    private static final Logger LOGGER = Logger.getLogger(DoctorService.class.getName());
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final DoctorSpecialitiesRepository doctorSpecialitiesRepository;

    public DoctorService(DoctorRepository doctorRepository, SpecialityRepository specialityRepository, DoctorSpecialitiesRepository doctorSpecialitiesRepository) {
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.doctorSpecialitiesRepository = doctorSpecialitiesRepository;
    }

    public Flux<DoctorDTO> findAllDoctors(){
        return doctorRepository.findAll()
                .flatMapSequential(doctor -> doctorSpecialitiesRepository.findByDoctorId(doctor.getDoctorId())
                        .map(DoctorSpecialities::getSpecialityId)
                        .collectList()
                        .flatMapMany(specialityIds -> Flux.fromIterable(specialityIds)
                                .flatMap(specialityRepository::findById)
                                .collectList()
                                .map(specialities -> new DoctorDTO(doctor, specialities))
                        )
                )
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error fetching doctors");
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found")));
    }

    public Mono<DoctorDTO> getDoctorById(Integer id){
        return doctorRepository.findByDoctorId(id)
                .flatMap(doctor ->
                        doctorSpecialitiesRepository.findByDoctorId(id)
                        .collectList()
                        .flatMap(doctorSpecialitiesList -> {
                            List<Integer> specialityIds = doctorSpecialitiesList.stream()
                                    .map(DoctorSpecialities::getSpecialityId)
                                    .collect(Collectors.toList());
                            return specialityRepository.findBySpecialityIdIn(specialityIds)
                                    .collectList()
                                    .map(specialities -> new DoctorDTO(doctor, specialities));
                        }))
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error fetching doctor by Id: "+id);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found")));
    }

    public Mono<Void> updateDoctorById(Integer id, Doctor doctor){
        return doctorRepository.findById(id)
                        .flatMap(existingDoctor-> {
                            existingDoctor.setDoctorName(doctor.getDoctorName());
                            existingDoctor.setIsActive(doctor.getIsActive());
                            existingDoctor.setUpdatedAt(LocalDate.now());
                            return doctorRepository.save(existingDoctor)
                                    .doOnSuccess(savedDoctor -> LOGGER.info("Doctor Updated: "+id+"---"+ doctor))
                                    .doOnError(error -> LOGGER.warning("Error updating doctor: "+error));
                        })
                        .onErrorResume(throwable -> {
                            LOGGER.warning("Error updating doctor by Id: "+id);
                            return Mono.empty();
                        })
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"))).then();
    }

    public Mono<Void> deleteDoctorById(Integer id){
        return doctorRepository.findByDoctorId(id)
                .flatMap(doctor ->
                    doctorRepository.deleteById(doctor.getDoctorId()).thenReturn(doctor))
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error deleting doctor by Id: "+id);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not delete Doctor Id: "+id).getMostSpecificCause())).then();
    }


    public Mono<Void> createDoctor(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        doctor.setIsActive(true);
        doctor.setCreatedAt(LocalDate.now());
        doctor.setDoctorName(doctorDTO.getDoctorName());

        return  doctorRepository.save(doctor)
                .flatMap(savedDoctor -> {
                    List<String> specialities = doctorDTO.getDoctorSpecialities();
                    if (specialities != null && !specialities.isEmpty()) {
                        return Flux.fromIterable(specialities)
                                .flatMap(specialtyId -> {
                                    DoctorSpecialities doctorSpecialities = new DoctorSpecialities();
                                    doctorSpecialities.setDoctorId(savedDoctor.getDoctorId());
                                    doctorSpecialities.setSpecialityId(Integer.valueOf(specialtyId));
                                    return doctorSpecialitiesRepository.save(doctorSpecialities);
                                }).then();
                    } else {
                        return Mono.empty();
                    }
                })
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error creating doctor");
                    return Mono.empty();
                }).then();
    }
}