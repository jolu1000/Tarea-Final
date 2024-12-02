import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class BusTest {

    private Bus1Piso bus1Piso;
    private Cliente cliente;
    private int numEstandar;
    private int numSemiCama;
    private int numCama;
    private int numPremium;

    @BeforeEach
    public void setUp() {
        numEstandar = 3;
        numSemiCama = 2;
        numCama = 2;
        numPremium = 3;
        bus1Piso = new Bus1Piso(10,numEstandar, numSemiCama, numCama, numPremium);  // Bus con capacidad 10 (Ejemplo con 1piso)
        cliente = new Cliente("Juan", "Pérez", "12345678-9", "juan@mail.com");

        // Inicializando asientos: 3 Estándar, 2 SemiCama, 2 Cama, 3 Premium
        bus1Piso.inicializarAsientos(3, 2, 2, 3);
    }

    @Test
    public void testInicializarAsientos() {
        int totalAsientosEsperados = numEstandar + numSemiCama + numCama + numPremium;
        List<Asiento> asientos = bus1Piso.getAsientos();
        assertEquals(totalAsientosEsperados, asientos.size(), "El bus debe tener " + totalAsientosEsperados + " asientos.");

        // Verificación de los tipos de asientos
        assertTrue(asientos.get(0) instanceof AsientoEstandar, "El primer asiento debe ser de tipo Estandar.");
        assertTrue(asientos.get(3) instanceof AsientoSemiCama, "El asiento 4 debe ser de tipo SemiCama.");
        assertTrue(asientos.get(5) instanceof AsientoCama, "El asiento 6 debe ser de tipo Cama.");
        assertTrue(asientos.get(8) instanceof AsientoPremium, "El asiento 9 debe ser de tipo Premium.");
    }


    @Test
    public void testOcuparAsiento() {
        // Prueba de ocupar un asiento
        boolean ocupado = bus1Piso.ocuparAsiento("E1", cliente);  // Intentando ocupar el primer asiento
        assertTrue(ocupado, "El asiento debe ser ocupado correctamente.");
        assertFalse(bus1Piso.getAsientos().get(0).isDisponible(), "El asiento no debe estar disponible después de ser ocupado.");
        assertEquals(cliente, bus1Piso.getAsientos().get(0).getCliente(), "El cliente debe ser asignado correctamente al asiento.");
    }

    @Test
    public void testOcuparAsientoInexistente() {
        // Intentar ocupar un asiento que no existe
        boolean ocupado = bus1Piso.ocuparAsiento("X1", cliente);
        assertFalse(ocupado, "El asiento no debe existir y no debe poder ser ocupado.");
    }

    @Test
    public void testMostrarAsientosConClientes() {
        // Ocupamos un asiento y verificamos la salida del método
        bus1Piso.ocuparAsiento("E1", cliente);

        // Método para mostrar asientos ocupados y disponibles
        bus1Piso.mostrarAsientosConClientes();  // Imprime el estado de los asientos en la consola
        // Este test se hace más para ver la salida en la consola
        // No puedes hacer un assert de la salida de la consola sin redirigirla en el código.
    }

    @Test
    public void testCloneBus1Piso() {
        Bus1Piso bus1Piso = new Bus1Piso(20, 5, 5, 5, 5);
        Bus clonedBus = bus1Piso.clone();

        assertNotNull(clonedBus, "El bus clonado no debe ser nulo.");
        assertNotSame(bus1Piso, clonedBus, "El bus clonado debe ser una instancia diferente.");
        assertEquals(bus1Piso.getAsientos().size(), clonedBus.getAsientos().size(), "El bus clonado debe tener la misma cantidad de asientos.");
    }

    @Test
    public void testCloneBus2Pisos() {
        Bus2Pisos bus2Pisos = new Bus2Pisos(20, 5, 5, 5, 5);
        Bus clonedBus = bus2Pisos.clone();

        assertNotNull(clonedBus, "El bus clonado no debe ser nulo.");
        assertNotSame(bus2Pisos, clonedBus, "El bus clonado debe ser una instancia diferente.");
        assertEquals(bus2Pisos.getAsientos().size(), clonedBus.getAsientos().size(), "El bus clonado debe tener la misma cantidad de asientos.");
    }

    @Test
    public void testCapacidadExacta() {
        bus1Piso.inicializarAsientos(3, 2, 2, 3);  // Inicializa con una capacidad de 10 asientos
        assertEquals(10, bus1Piso.getCapacidad(), "La capacidad del bus debe ser igual a la cantidad de asientos.");
    }


}