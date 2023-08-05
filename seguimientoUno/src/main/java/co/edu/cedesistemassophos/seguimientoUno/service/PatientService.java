package co.edu.cedesistemassophos.seguimientoUno.service;

import co.edu.cedesistemassophos.seguimientoUno.model.Patient;
import co.edu.cedesistemassophos.seguimientoUno.repository.PatientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Flux<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Mono<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    public Mono<Patient> savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Mono<Void> deletePatient(Integer id) {
        return patientRepository.deleteById(id);
    }

}

