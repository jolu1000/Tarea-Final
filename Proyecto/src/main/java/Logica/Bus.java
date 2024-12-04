package Logica;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase abstracta que representa un bus con una capacidad específica y una lista de asientos.
 * Implementa la interfaz "Cloneable" para permitir la clonación del bus.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public abstract class Bus implements Cloneable {
    private String patente;
    private int capacidad;
    private List<Asiento> asientos;
    /**
     * Constructor que inicializa un bus con una capacidad dada.
     * Genera automáticamente una patente aleatoria.
     *
     * @param capacidad Capacidad máxima del bus.
     */
    public Bus(int capacidad) {
        this.patente = Patentes.generarPatenteAleatoria();
        this.capacidad = capacidad;
        this.asientos = new ArrayList<>();
    }
    /**
     * Inicializa los asientos del bus con diferentes tipos según las cantidades indicadas.
     *
     * @param estandar Número de asientos estándar.
     * @param semiCama Número de asientos semi cama.
     * @param cama Número de asientos cama.
     * @param premium Número de asientos premium.
     */
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
    /**
     * Getter que obtiene la patente del bus.
     *
     * @return La patente del bus.
     */
    public String getPatente() {
        return patente;
    }
    /**
     * Getter que obtiene la capacidad máxima del bus.
     *
     * @return La capacidad máxima del bus.
     */
    public int getCapacidad() {
        return capacidad;
    }
    /**
     * Getter que obtiene la lista de asientos del bus.
     *
     * @return Una lista de asientos.
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }
    /**
     * Ocupa un asiento específico, asignándole un cliente.
     *
     * @param identificador Identificador del asiento.
     * @param cliente Cliente que ocupará el asiento.
     * @return El asiento ocupado, o null si no está disponible.
     */
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
    /**
     * Muestra la lista de asientos junto con la información del cliente que los ocupa.
     * Si el asiento está disponible, lo indica.
     */
    public void mostrarAsientosConClientes() {
        for (Asiento asiento : asientos) {
            if (!asiento.isDisponible()) {
                System.out.println(asiento + " - Cliente: " + asiento.getCliente());
            } else {
                System.out.println(asiento + " - Disponible");
            }
        }
    }
    /**
     * Obtiene la lista de asientos ocupados en el bus.
     *
     * @return Una lista de asientos ocupados.
     */
    public List<Asiento> getAsientosOcupados() {
        List<Asiento> asientosOcupados = new ArrayList<>();
        for (Asiento asiento : asientos) {
            if (!asiento.isDisponible()) { // Si el asiento está ocupado
                asientosOcupados.add(asiento);
            }
        }
        return asientosOcupados;
    }
    /**
     * Crea y devuelve una copia del bus actual.
     * La copia tendrá una nueva patente y una lista de asientos clonada.
     *
     * @return Un objeto clonado de tipo Bus.
     */
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

    /**
     * Devuelve una representación en string del bus.
     *
     * @return Un string que representa el estado del bus.
     */
    @Override
    public String toString() {
        return "Bus [Patente: " + patente + ", Capacidad: " + capacidad + "]";
    }
    /**
     * Agrega un asiento al bus.
     *
     * @param asiento Asiento a agregar.
     * @throws IllegalArgumentException Si el asiento es nulo o su ID ya existe.
     * @throws IllegalStateException Si se ha alcanzado la capacidad máxima.
     */
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





