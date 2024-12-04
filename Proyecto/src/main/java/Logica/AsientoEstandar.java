package Logica;
/**
 * Clase que representa un asiento del tipo estandar.
 * Extiende la clase abstracta Asiento.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class AsientoEstandar extends Asiento {
    /**
     * Constructor que inicializa un asiento estandar con un identificador.
     *
     * @param identificador Identificador único del asiento estandar.
     */
    public AsientoEstandar(String identificador) {
        super(identificador, TipoAsiento.ESTANDAR);
    }
    /**
     * Crea y devuelve una copia del objeto actual de tipo AsientoEstandar.
     *
     * @return Una copia del objeto AsientoEstandar.
     */
    @Override
    public AsientoEstandar clone() {
        return (AsientoEstandar) super.clone();
    }
}