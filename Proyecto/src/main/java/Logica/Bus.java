package Logica;

import java.util.ArrayList;
import java.util.List;

public abstract class Bus implements Cloneable {
    private String patente;
    private int capacidad;
    private List<Asiento> asientos;


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


    public Asiento ocuparAsiento(String identificador, Cliente cliente) {
        for (Asiento asiento : asientos) {
            if (asiento.getId().equals(identificador) && asiento.isDisponible()) {
                asiento.setCliente(cliente);
                asiento.ocupar();
                return asiento;
            }
        }
        return null;
    }

    public void mostrarAsientosConClientes() {
        for (Asiento asiento : asientos) {
            if (!asiento.isDisponible()) {
                System.out.println(asiento + " - Cliente: " + asiento.getCliente());
            } else {
                System.out.println(asiento + " - Disponible");
            }
        }
    }

    public List<Asiento> getAsientosOcupados() {
        List<Asiento> asientosOcupados = new ArrayList<>();
        for (Asiento asiento : asientos) {
            if (!asiento.isDisponible()) { // Si el asiento está ocupado
                asientosOcupados.add(asiento);
            }
        }
        return asientosOcupados;
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

    public void addAsiento(Asiento asiento) {
        if (asiento == null) {
            throw new IllegalArgumentException("El asiento no puede ser nulo.");
        }

        if (asientos.size() >= capacidad) {
            throw new IllegalStateException("No se pueden agregar más asientos. Se ha alcanzado la capacidad máxima.");
        }

        for (Asiento a : asientos) {
            if (a.getId().equals(asiento.getId())) {
                throw new IllegalArgumentException("Ya existe un asiento con el mismo ID: " + asiento.getId());
            }
        }

        asientos.add(asiento);
    }
    public abstract boolean pisos();

}





