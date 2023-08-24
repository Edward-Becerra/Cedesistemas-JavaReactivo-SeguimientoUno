package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.model.Patient;
import co.edu.cedesistemassophos.seguimientoUno.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.logging.Logger;

@Service
public class PatientService {

    private static final Logger LOGGER = Logger.getLogger(PatientService.class.getName());
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Flux<Patient> getAllPatients() {
        return patientRepository.findAll()
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error fetching patients");
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found")));
    }

    public Mono<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id)
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error fetching patient by Id: "+id);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found")));
    }

    public Mono<Void> updatePatientById(Integer id, Patient patient){
        return patientRepository.findById(id)
                .flatMap(existingPatient -> {
                    existingPatient.setPatientName(patient.getPatientName());
                    existingPatient.setBirthDate(patient.getBirthDate());
                    existingPatient.setDiagnosis(patient.getDiagnosis());
                    existingPatient.setDiagnosisDescription(patient.getDiagnosisDescription());
                    existingPatient.setAllergic(patient.isAllergic());
                    existingPatient.setRoom(patient.getRoom());
                    existingPatient.setUpdatedAt(LocalDate.now());
                    return patientRepository.save(existingPatient)
                            .doOnSuccess(savePatient -> LOGGER.info("Patient updated: "+id+ "---"+ patient))
                            .doOnError(error -> LOGGER.warning("Error updating patient: "+error));
                })
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error updating patient by Id: "+id);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found")))
                .then();
    }

    public Mono<Void> deletePatientById(Integer id){
        return patientRepository.findById(id)
                .flatMap(patient ->
                    patientRepository.deleteById(patient.getPatientId()).thenReturn(patient))
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error deleting patient by Id: "+id);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found")))
                .then();
    }

    public Mono<Void> createPatient(Patient patient) {
        patient.setAge(patient.getAge());
        return patientRepository.save(patient)
                .onErrorResume(throwable -> {
                    LOGGER.warning("Error creating patient");
                    return Mono.empty();
                })
                .then();
    }
}

