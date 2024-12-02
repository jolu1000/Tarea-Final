package Logica;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Ruta {
    private Ciudades ciudadOrigen;
    private Ciudades ciudadDestino;
    private Date fecha;
    private LocalTime hora;
    private int precio;
    private Bus bus;

    public Ruta(Ciudades ciudadOrigen, Ciudades ciudadDestino, Date fecha, LocalTime hora, int precio, Bus bus) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.bus = bus;
    }

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