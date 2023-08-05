package co.edu.cedesistemassophos.seguimientoUno.controller;

import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import co.edu.cedesistemassophos.seguimientoUno.service.DoctorService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }
    @GetMapping("/{id}")
    public Mono<Doctor> getDoctorById(@PathVariable Integer id){
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/")
    public Flux<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping("/create-doctor")
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor){
        return doctorService.saveDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Mono<Doctor> updateDoctorById(@PathVariable Integer id, @RequestBody Doctor doctor){
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/")
    public Mono<Void> deleteAllDoctors(){
        return doctorService.deleteAllDoctors();
    }

    @DeleteMapping("/{id}")
    public Mono<Doctor> deleteDoctorById(@PathVariable Integer id){
        return doctorService.deleteDoctorById(id);
    }

    @
    GetMapping("/active")
    public Flux<Doctor> getActiveDoctors(){
        return doctorService.findByIsActive(Boolean.TRUE);
    }

    @GetMapping("/inactive")
    public Flux<Doctor> getInactiveDoctors(){
        return doctorService.findByIsActive(Boolean.FALSE);
    }
}
