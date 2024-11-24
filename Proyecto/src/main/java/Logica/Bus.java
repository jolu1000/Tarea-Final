package Logica;

public abstract class Bus implements Cloneable {
    private String patente;
    private int capacidad;
    private Asientos asientos;

    public Bus(String patente, int capacidad) {
        this.patente = patente;
        this.capacidad = capacidad;
        this.asientos = new Asientos();
    }

    public void inicializarAsientos(int estandar, int semiCama, int cama, int premium) {
        int contador = 1;

        for (int i = 0; i < estandar; i++) {
            asientos.agregarAsiento(new AsientoEstandar("E" + contador++));
        }
        for (int i = 0; i < semiCama; i++) {
            asientos.agregarAsiento(new AsientoSemiCama("SC" + contador++));
        }
        for (int i = 0; i < cama; i++) {
            asientos.agregarAsiento(new AsientoCama("C" + contador++));
        }
        for (int i = 0; i < premium; i++) {
            asientos.agregarAsiento(new AsientoPremium("P" + contador++));
        }
    }

    @Override
    public Bus clone() {
        try {
            Bus clon = (Bus) super.clone();
            clon.asientos = this.asientos.clone();
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar el bus", e);
        }
    }

    public String getPatente() {
        return patente;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Asientos getAsientos() {
        return asientos;
    }
}

