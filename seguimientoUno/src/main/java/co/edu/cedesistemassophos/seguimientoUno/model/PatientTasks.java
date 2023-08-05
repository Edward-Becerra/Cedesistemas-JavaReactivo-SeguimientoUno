package co.edu.cedesistemassophos.seguimientoUno.model;

public interface PatientTasks {
    default boolean isIntern(){
        return true;
    }
}
