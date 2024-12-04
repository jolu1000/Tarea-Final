package Logica;
/**
 * Clase que representa un bus de dos pisos.
 * Extiende la clase abstracta Bus y define su comportamiento específico.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class Bus2Pisos extends Bus {
    /**
     * Constructor que inicializa un bus de dos pisos con la capacidad total y la distribución de los asientos.
     *
     * @param capacidad Capacidad máxima del bus.
     * @param estandar Cantidad de asientos estándar.
     * @param semiCama Cantidad de asientos semi cama.
     * @param cama Cantidad de asientos cama.
     * @param premium Cantidad de asientos premium.
     */
    public Bus2Pisos(int capacidad, int estandar, int semiCama, int cama, int premium) {
        super(capacidad);
        inicializarAsientos(estandar, semiCama, cama, premium);
    }
    /**
     * Indica si el bus tiene más de un piso.
     *
     * @return true, ya que este bus tiene dos pisos.
     */
    public boolean pisos(){
        return true;
    }
    /**
     * Crea y devuelve una copia exacta del bus actual.
     *
     * @return Un nuevo objeto Bus2Pisos que es una copia del actual.
     */
    @Override
    public Bus2Pisos clone() {
        return (Bus2Pisos) super.clone();
    }
}

