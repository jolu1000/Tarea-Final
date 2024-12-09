package Logica;
/**
 * Clase abstracta que representa un asiento del bus.
 * Implementa la interfaz "Cloneable" para permitir la clonación de objetos de esta clase.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 *
 */
public abstract class Asiento implements Cloneable {
    protected String Id;
    protected boolean disponible = true;
    private TipoAsiento tipoAsiento;
    private Cliente cliente;
    /**
     * Constructor que inicializa un asiento con su identificador y tipo.
     *
     * @param Id Identificador único del asiento.
     * @param tipoAsiento Tipo del asiento.
     */
    public Asiento(String Id, TipoAsiento tipoAsiento) {
        this.Id = Id;
        this.tipoAsiento = tipoAsiento;
    }
    /**
     * Getter que obtiene el identificador del asiento.
     *
     * @return El identificador del asiento.
     */
    public String getId() {
        return Id;
    }
    /**
     * Verifica si el asiento está disponible.
     *
     * @return true si el asiento está disponible, de lo contrario false.
     */
    public boolean isDisponible() {
        return disponible;
    }
    /**
     * Getter que obtiene el tipo de asiento.
     *
     * @return El tipo de asiento.
     */
    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }
    /**
     * Metodo que marca el asiento como ocupado.
     */
    public void ocupar() {
        this.disponible = false;
    }
    /**
     * Getter que obtiene el cliente que ocupa el asiento.
     *
     * @return El cliente que ocupa el asiento, o nulo si está disponible.
     */
    public Cliente getCliente(){
        return cliente;
    }
    /**
     * Getter que obtiene el precio del asiento según su tipo.
     *
     * @return El precio del asiento.
     */
    public int getPrecio() {
        return tipoAsiento.getPrecio();
    }
    /**
     * Setter que asigna un cliente al asiento si está disponible y
     * marca el asiento como ocupado.
     *
     * @param cliente El cliente que ocupará el asiento.
     * @throws IllegalStateException si el asiento ya está ocupado.
     */
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            this.cliente = null;
            this.disponible = true;  // Si el cliente es nulo, el asiento debe estar disponible
        } else if (this.disponible) {
            this.cliente = cliente;
            this.ocupar();  // Si el asiento está disponible, lo ocupa
        } else {
            throw new IllegalStateException("Este asiento ya está ocupado.");
        }
    }

    /**
     * Crea y devuelve una copia del objeto actual.
     *
     * @return Una copia del objeto Asiento.
     */
    @Override
    public Asiento clone() {
        try {
            return (Asiento) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Devuelve una representación en cadena del asiento.
     *
     * @return Una cadena que representa el estado del asiento.
     */
    @Override
    public String toString() {
        return "Asiento " + Id + " (" + tipoAsiento + ") - " + (disponible ? "Disponible" : "Ocupado");
    }
}

