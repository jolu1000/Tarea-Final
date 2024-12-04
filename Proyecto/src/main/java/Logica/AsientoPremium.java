package Logica;
/**
 * Clase que representa un asiento del tipo premium.
 * Extiende la clase abstracta Asiento.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class AsientoPremium extends Asiento {
    /**
     * Constructor que inicializa un asiento premium con un identificador.
     *
     * @param identificador Identificador único del asiento premium.
     */
    public AsientoPremium(String identificador) {
        super(identificador, TipoAsiento.PREMIUM);
    }
    /**
     * Crea y devuelve una copia del objeto actual de tipo AsientoPremium.
     *
     * @return Una copia del objeto AsientoPremium.
     */
    @Override
    public AsientoPremium clone() {
        return (AsientoPremium) super.clone();
    }
}