package Logica;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private List<Asiento> asientosReservados;

    public Cliente(String nombre, String apellido, String rut, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.email = email;
        this.asientosReservados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void agregarAsientoReservado(Asiento asiento) {
        this.asientosReservados.add(asiento);
    }

    public List<Asiento> getAsientosReservados() {
        return asientosReservados;
    }

    @Override
    public String toString() {
        return "Cliente [Nombre: " + nombre + ", Apellido: " + apellido + ", RUT: " + rut + ", Email: " + email + "]";
    }
}