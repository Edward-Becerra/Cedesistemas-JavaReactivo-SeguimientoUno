package co.edu.cedesistemassophos.seguimientoUno.repository;

import co.edu.cedesistemassophos.seguimientoUno.dto.DoctorDTO;
import co.edu.cedesistemassophos.seguimientoUno.model.Doctor;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DoctorRepository extends R2dbcRepository<Doctor, Integer> {
    @Query("SELECT p.*, d.*, s.* FROM person p " +
            "INNER JOIN doctor d ON p.person_id = d.person_id " +
            "INNER JOIN speciality s ON d.speciality_id = s.speciality_id")
    Flux<DoctorDTO> findAllDoctorsWithDetails();

    @Query("SELECT p.*, d.*, s.* FROM person p " +
            "INNER JOIN doctor d ON p.person_id = d.person_id " +
            "INNER JOIN speciality s ON d.speciality_id = s.speciality_id " +
            "WHERE d.doctor_id = :id")
    Mono<DoctorDTO> findDoctorWithDetailsById(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE doctor d SET d.is_active = :isActive WHERE d.doctor_id = :id")
    Mono<Void> updateDoctorDetails(@Param("id") Integer id, @Param("isActive") Boolean isActive);

    @Modifying
    @Query("UPDATE person p SET p.first_name = :firstName, p.last_name = :lastName WHERE p.person_id IN (SELECT d.person_id FROM doctor d WHERE d.doctor_id = :id)")
    Mono<Void> updatePersonDetails(@Param("id") Integer id, @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Modifying
    @Query("DELETE FROM doctor WHERE doctor_id = :id")
    Mono<Void> deleteDoctorById(@Param("id") Integer id);

    @Modifying
    @Query("DELETE FROM person WHERE person_id = :id")
    Mono<Void> deletePersonById(@Param("id") Integer id);
}
