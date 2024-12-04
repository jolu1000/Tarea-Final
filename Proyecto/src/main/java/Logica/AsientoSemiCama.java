package Logica;
/**
 * Clase que representa un asiento del tipo semi cama.
 * Extiende la clase abstracta Asiento.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class AsientoSemiCama extends Asiento {
    /**
     * Constructor que inicializa un asiento semi cama con un identificador.
     *
     * @param identificador Identificador único del asiento semi cama.
     */
    public AsientoSemiCama(String identificador) {
        super(identificador, TipoAsiento.SEMI_CAMA);
    }
    /**
     * Crea y devuelve una copia del objeto actual de tipo AsientoSemiCama.
     *
     * @return Una copia del objeto AsientoSemiCama.
     */
    @Override
    public AsientoSemiCama clone() {
        return (AsientoSemiCama) super.clone();
    }
}