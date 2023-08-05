package co.edu.cedesistemassophos.seguimientoUno.model;

public interface DoctorTasks {
    default boolean isActive(){
        return false;
    }
}
