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
                .map(doctor -> {
                    System.out.println("Doctor found: "+doctor);
                    return ResponseEntity.ok(doctor);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> updateDoctorById(@PathVariable Integer id, @RequestBody DoctorDTO doctorDTO){
        return doctorService.updateDoctorById(id, doctorDTO)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("result", "Doctor Updated")))
                .onErrorResume(throwable -> {
                  return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> deleteDoctorById(@PathVariable Integer id){
        return doctorService.deleteDoctorById(id)
                .thenReturn(ResponseEntity.ok().body(Collections.singletonMap("result", "Doctor Deleted")))
                .onErrorResume(throwable -> {
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @PostMapping("/create-doctor")
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }
}
