package co.edu.cedesistemassophos.seguimientoUno.controller;

import co.edu.cedesistemassophos.seguimientoUno.model.Patient;
import co.edu.cedesistemassophos.seguimientoUno.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public Flux<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Patient>> getPatientById(@PathVariable Integer id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> updatePatientById(@PathVariable Integer id, @RequestBody Patient patient) {
        return patientService.updatePatientById(id, patient)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("result", "Patient Updated")))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> deletePatientById(@PathVariable Integer id) {
        return patientService.deletePatientById(id)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("result", "Patient Deleted")))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-patient")
    public Mono<ResponseEntity<Map<String, String>>> savePatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("result", "Patient created")))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
