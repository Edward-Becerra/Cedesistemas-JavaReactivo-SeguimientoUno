package co.edu.cedesistemassophos.seguimientoUno.controller;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("api/doctor")
public class DoctorController {
    DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public Flux<DoctorDTO> getAllDoctors(){
        return doctorService.findAllDoctors();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<DoctorDTO>> getDoctorById(@PathVariable Integer id){
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> updateDoctorById(@PathVariable Integer id, @RequestBody Doctor doctor){
        return doctorService.updateDoctorById(id, doctor)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("result", "Doctor Updated")))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> deleteDoctorById(@PathVariable Integer id){
        return doctorService.deleteDoctorById(id)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("result", "Doctor Deleted")))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-doctor")
    public Mono<ResponseEntity<Map<String, String>>> createDoctor(@RequestBody DoctorDTO doctorDTO){
        return doctorService.createDoctor(doctorDTO)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("Result", "Doctor created")))
                .onErrorResume(throwable -> {
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
