package co.edu.cedesistemassophos.seguimientoUno.controller;

import co.edu.cedesistemassophos.seguimientoUno.model.Patient;
import co.edu.cedesistemassophos.seguimientoUno.service.PatientService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public Flux<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Mono<Patient> getPatientById(@PathVariable Integer id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/create-patient")
    public Mono<Patient> savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePatient(@PathVariable Integer id) {
        return patientService.deletePatient(id);
    }
}
