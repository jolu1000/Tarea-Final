package Logica;
/**
 * Enum que representa los diferentes tipos de asientos disponibles en un bus,
 * con su precio correspondiente.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public enum TipoAsiento {
    ESTANDAR(0),
    SEMI_CAMA(3000),
    CAMA(7000),
    PREMIUM(10000);

    private final int precio;
    /**
     * Constructor del enum TipoAsiento. Asigna el precio correspondiente a cada tipo de asiento.
     *
     * @param precio El precio adicional del tipo de asiento.
     */
    TipoAsiento(int precio) {
        this.precio = precio;
    }
    /**
     * Getter que obtiene el precio del tipo de asiento.
     *
     * @return El precio asociado al tipo de asiento.
     */
    public int getPrecio() {
        return precio;
    }
}
