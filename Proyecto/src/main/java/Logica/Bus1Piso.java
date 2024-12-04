package Logica;
/**
 * Clase que representa un bus de un solo piso.
 * Extiende la clase abstracta Bus y define su comportamiento específico.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class Bus1Piso extends Bus {
    /**
     * Constructor que inicializa un bus de un piso con la capacidad total y la distribución de los asientos.
     *
     * @param capacidad Capacidad máxima del bus.
     * @param estandar Cantidad de asientos estándar.
     * @param semiCama Cantidad de asientos semi cama.
     * @param cama Cantidad de asientos cama.
     * @param premium Cantidad de asientos premium.
     */
    public Bus1Piso(int capacidad, int estandar, int semiCama, int cama, int premium) {
        super(capacidad);
        inicializarAsientos(estandar, semiCama, cama, premium);
    }
    /**
     * Indica si el bus tiene más de un piso.
     *
     * @return false, ya que este bus solo tiene un piso.
     */
    public boolean pisos(){
        return false;
    }
    /**
     * Crea y devuelve una copia exacta del bus actual.
     *
     * @return Un nuevo objeto Bus1Piso que es una copia del actual.
     */
    @Override
    public Bus1Piso clone() {
        return (Bus1Piso) super.clone();
    }
}


