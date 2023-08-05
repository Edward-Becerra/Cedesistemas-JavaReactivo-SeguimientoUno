package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.model.Person;
import co.edu.cedesistemassophos.seguimientoUno.repository.DoctorRepository;
import co.edu.cedesistemassophos.seguimientoUno.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final PersonRepository personRepository;

    public DoctorService(DoctorRepository doctorRepository, PersonRepository personRepository){
        this.doctorRepository = doctorRepository;
        this.personRepository = personRepository;
    }

    public Flux<Doctor> getAllDoctors() {
        return doctorRepository.findAll()
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
                        "Doctor's Id: "+id+" not found").getMostSpecificCause()));
    }

    public Mono<Doctor> saveDoctor(Doctor doctor) {
        return personExists(doctor.getPersonId())
                .flatMap(person -> {
                    doctor.setDocumentType(person.getDocumentType());
                    doctor.setDocumentNumber(person.getDocumentNumber());
                    doctor.setFirstName(person.getFirstName());
                    doctor.setLastName(person.getLastName());
                    doctor.setAge(person.getAge());
                    doctor.setActive(true);
                    return doctorRepository.save(doctor)
                            .doOnSuccess(savedDoctor -> System.out.println("Doctor saved: " + savedDoctor));
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found: " + doctor)))
                .onErrorResume(throwable -> Mono.empty());
    }

    protected Mono<Person> personExists(Integer id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id: " + id)));
    }

    public Mono<Doctor> updateDoctor(Integer id, Doctor doctor){
        return doctorRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalDoctor -> {
                    if (optionalDoctor.isPresent()){
                        doctor.setSpeciality("some_speciality");
                        doctor.setActive(Boolean.TRUE);
                        return doctorRepository.save(doctor)
                                .onErrorResume(throwable -> {
                                    return Mono.empty();
                                })
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Doctor "+doctor+" not updated").getMostSpecificCause()));
                    }
                    return Mono.empty();
                });
    }

    public Mono<Doctor> deleteDoctorById(Integer id){
        return doctorRepository.findById(id)
                .flatMap(doctor -> doctorRepository.deleteById(doctor.getIdentifier()).thenReturn(doctor))
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor's Id: "+id+" not deleted").getMostSpecificCause()));
    }

    public Mono<Void> deleteAllDoctors(){
        return doctorRepository.deleteAll()
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctors aren't deleted").getMostSpecificCause()));
    }

    public Flux<Doctor> findByIsActive(Boolean status){
        return doctorRepository.findByIsActive(status)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Active doctors not found").getMostSpecificCause()));
    }

    public Flux<Doctor> findBySpeciality(String speciality){
        return doctorRepository.findBySpeciality(speciality)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor by speciality "+speciality+" not found").getMostSpecificCause()));
    }

    public Flux<Doctor> findBySpecialityAndIsActive(String speciality, Boolean status){
        return doctorRepository.findBySpecialityAndIsActive(speciality, status)
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when find by "+speciality+" and by status not found").getMostSpecificCause()));
    }
}
