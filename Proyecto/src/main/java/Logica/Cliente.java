package Logica;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que representa un cliente que puede reservar asientos en un bus.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class Cliente {
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private List<Asiento> asientosReservados;
    /**
     * Constructor que inicializa un cliente con su información personal.
     *
     * @param nombre Nombre del cliente.
     * @param apellido Apellido del cliente.
     * @param rut RUT del cliente (identificación).
     * @param email Correo electrónico del cliente.
     */
    public Cliente(String nombre, String apellido, String rut, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.email = email;
        this.asientosReservados = new ArrayList<>();
    }
    /**
     * Getter que obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setter que establece el nombre del cliente.
     *
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter que obtiene el apellido del cliente.
     *
     * @return El apellido del cliente.
     */
    public String getApellido() {
        return apellido;
    }
    /**
     * Setter que establece el apellido del cliente.
     *
     * @param apellido Nuevo apellido del cliente.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    /**
     * Getter que obtiene el rut del cliente.
     *
     * @return El rut del cliente.
     */
    public String getRut() {
        return rut;
    }
    /**
     * Setter que establece el rut del cliente.
     *
     * @param rut Nuevo rut del cliente.
     */
    public void setRut(String rut) {
        this.rut = rut;
    }
    /**
     * Getter que obtiene el email del cliente.
     *
     * @return El email del cliente.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setter que establece el email del cliente.
     *
     * @param email Nuevo email del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Agrega un asiento a la lista de asientos reservados por el cliente.
     *
     * @param asiento Asiento que se reserva.
     */
    public void agregarAsientoReservado(Asiento asiento) {
        this.asientosReservados.add(asiento);
    }
    /**
     * Obtiene la lista de asientos reservados por el cliente.
     *
     * @return Lista de asientos reservados.
     */
    public List<Asiento> getAsientosReservados() {
        return asientosReservados;
    }
    /**
     * Representación en formato de texto de un cliente.
     *
     * @return Un string con los detalles del cliente.
     */
    @Override
    public String toString() {
        return "Cliente [Nombre: " + nombre + ", Apellido: " + apellido + ", RUT: " + rut + ", Email: " + email + "]";
    }
}