package Logica;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
/**
 * Representa una ruta de viaje entre dos ciudades, con detalles como la fecha, hora,
 * precio y el bus asignado.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class Ruta {
    private Ciudades ciudadOrigen;
    private Ciudades ciudadDestino;
    private Date fecha;
    private LocalTime hora;
    private int precio;
    private Bus bus;
    /**
     * Constructor de la clase Ruta.
     *
     * @param ciudadOrigen La ciudad de origen de la ruta.
     * @param ciudadDestino La ciudad de destino de la ruta.
     * @param fecha La fecha del viaje.
     * @param hora La hora de salida del viaje.
     * @param precio El precio del pasaje.
     * @param bus El bus que realiza la ruta.
     */
    public Ruta(Ciudades ciudadOrigen, Ciudades ciudadDestino, Date fecha, LocalTime hora, int precio, Bus bus) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.bus = bus;
    }
    /**
     * Getters y Setters de la clase Ruta
     */
    public Ciudades getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudades ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudades getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudades ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
    /**
     * Devuelve una representación en string de la ruta con los detalles de las ciudades de origen y destino,
     * la fecha, la hora y el tipo de bus (1 o 2 pisos).
     *
     * @return Un string con los detalles de la ruta.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato de fecha
        String fechaFormateada = dateFormat.format(fecha);

        String horaFormateada = hora.toString(); // Hora en formato HH:mm
        if(bus.pisos() == true){
            return "Ruta: " + ciudadOrigen.getNombre() + " -> " + ciudadDestino.getNombre() +
                    " |Pisos: 2"+
                    " | Fecha: " + fechaFormateada +
                    " | Hora: " + horaFormateada +
                    " | Bus: " + bus.getPatente();
        }else {
            return "Ruta: " + ciudadOrigen.getNombre() + " -> " + ciudadDestino.getNombre() +
                    " |Pisos: 1"+
                    " | Fecha: " + fechaFormateada +
                    " | Hora: " + horaFormateada +
                    " | Bus: " + bus.getPatente();
        }
    }
}