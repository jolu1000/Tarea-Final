package Logica;
/**
 * Enumeración que representa las principales ciudades.
 * Cada ciudad tiene un nombre asociado.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public enum Ciudades {
    CONCEPCION("Concepción"),
    LOS_ANGELES("Los Ángeles"),
    CHILLAN("Chillán"),
    SANTIAGO("Santiago");
    /**
     * Constructor que inicializa una ciudad con su nombre.
     *
     * @param nombre Nombre de la ciudad.
     */

    private String nombre;

    Ciudades(String nombre){
        this.nombre = nombre;
    }
    /**
     * Getter que obtiene el nombre de la ciudad.
     *
     * @return El nombre de la ciudad.
     */
    public String getNombre() {
        return nombre;
    }
}
