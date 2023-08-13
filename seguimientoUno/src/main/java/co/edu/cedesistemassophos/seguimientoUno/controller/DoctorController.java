package co.edu.cedesistemassophos.seguimientoUno.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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
    public Mono<ResponseEntity<Doctor>> getDoctorById(@PathVariable Integer id){
        return doctorService.getDoctorById(id)
                .map(doctor -> {
                    System.out.println("Doctor found: "+doctor);
                    return ResponseEntity.ok(doctor);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping("/create-doctor")
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDoctorById(@PathVariable Integer id){
        return doctorService.deleteDoctorById(id);
    }
}
