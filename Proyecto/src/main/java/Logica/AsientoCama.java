package Logica;
/**
 * Clase que representa un asiento del tipo cama.
 * Extiende la clase abstracta Asiento.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class AsientoCama extends Asiento {
    /**
     * Constructor que inicializa un asiento cama con un identificador.
     *
     * @param identificador Identificador único del asiento cama.
     */
    public AsientoCama(String identificador) {
        super(identificador, TipoAsiento.CAMA);
    }
    /**
     * Crea y devuelve una copia del objeto actual de tipo AsientoCama.
     *
     * @return Una copia del objeto AsientoCama.
     */
    @Override
    public AsientoCama clone() {
        return (AsientoCama) super.clone();
    }
}