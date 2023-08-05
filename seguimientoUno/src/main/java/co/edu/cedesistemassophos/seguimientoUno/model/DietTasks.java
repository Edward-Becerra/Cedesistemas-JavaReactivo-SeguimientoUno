package co.edu.cedesistemassophos.seguimientoUno.model;

public interface DietTasks {
    default boolean isPrescription(){
        return true;
    }
}
