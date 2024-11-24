package Logica;

import java.util.ArrayList;
import java.util.List;

public abstract class Bus implements Cloneable {
    private String patente;
    private int capacidad;
    private List<Asiento> asientos;

    // Constructor que solo recibe la capacidad
    public Bus(int capacidad) {
        this.patente = Patentes.generarPatenteAleatoria();
        this.capacidad = capacidad;
        this.asientos = new ArrayList<>();
    }

    public void inicializarAsientos(int estandar, int semiCama, int cama, int premium) {
        int contador = 1;
        // Agregar asientos estándar
        for (int i = 0; i < estandar; i++) {
            asientos.add(new AsientoEstandar("E" + contador++));
        }
        // Agregar asientos semi cama
        for (int i = 0; i < semiCama; i++) {
            asientos.add(new AsientoSemiCama("SC" + contador++));
        }
        // Agregar asientos cama
        for (int i = 0; i < cama; i++) {
            asientos.add(new AsientoCama("C" + contador++));
        }
        // Agregar asientos premium
        for (int i = 0; i < premium; i++) {
            asientos.add(new AsientoPremium("P" + contador++));
        }
    }

    public String getPatente() {
        return patente;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    // Método para ocupar un asiento
    public boolean ocuparAsiento(String identificador) {
        for (Asiento asiento : asientos) {
            if (asiento.getId().equals(identificador) && asiento.isDisponible()) {
                asiento.ocupar();
                return true;
            }
        }
        return false;
    }

    @Override
    public Bus clone() {
        try {
            Bus clonedBus = (Bus) super.clone();
            clonedBus.asientos = new ArrayList<>();
            for (Asiento asiento : this.asientos) {
                clonedBus.asientos.add((Asiento) asiento.clone());
            }
            clonedBus.patente = Patentes.generarPatenteAleatoria();  // Nueva patente para el bus clonado
            return clonedBus;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Bus [Patente: " + patente + ", Capacidad: " + capacidad + "]";
    }
}




