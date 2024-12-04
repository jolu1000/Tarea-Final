import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
/**
 * Clase de pruebas unitarias para la clase Bus.
 * Realiza pruebas sobre las funcionalidades de la clase Bus.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
class BusTest {

    private Bus1Piso bus1Piso;
    private Bus2Pisos bus2Pisos;
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

        bus1Piso = new Bus1Piso(10, numEstandar, numSemiCama, numCama, numPremium);  // Bus 1 Piso
        bus2Pisos = new Bus2Pisos(20, numEstandar, numSemiCama, numCama, numPremium); // Bus 2 Pisos
        cliente = new Cliente("Juan", "Pérez", "12345678-9", "juan@mail.com");

        // Inicializando asientos: 3 Estándar, 2 SemiCama, 2 Cama, 3 Premium
        bus1Piso.inicializarAsientos(numEstandar, numSemiCama, numCama, numPremium);
        bus2Pisos.inicializarAsientos(numEstandar, numSemiCama, numCama, numPremium);
    }
    /**
     * Test para verificar que un asiento puede ser ocupado correctamente en ambos tipos de buses.
     */
    @Test
    public void testOcuparAsiento() {
        // Prueba de ocupar un asiento en Bus 1 Piso
        Asiento asiento1Piso = bus1Piso.ocuparAsiento("E1", cliente);  // Obtener el asiento después de ocuparlo
        assertNotNull(asiento1Piso, "El asiento debería ser encontrado.");
        assertFalse(asiento1Piso.isDisponible(), "El asiento no debe estar disponible después de ser ocupado.");
        assertEquals(cliente, asiento1Piso.getCliente(), "El cliente debe ser asignado correctamente al asiento.");

        // Prueba de ocupar un asiento en Bus 2 Pisos
        Asiento asiento2Pisos = bus2Pisos.ocuparAsiento("E1", cliente);  // Obtener el asiento después de ocuparlo
        assertNotNull(asiento2Pisos, "El asiento debería ser encontrado.");
        assertFalse(asiento2Pisos.isDisponible(), "El asiento no debe estar disponible después de ser ocupado.");
        assertEquals(cliente, asiento2Pisos.getCliente(), "El cliente debe ser asignado correctamente al asiento.");
    }
    /**
     * Test para verificar que no se pueda ocupar un asiento inexistente en cualquiera de los buses.
     */
    @Test
    public void testOcuparAsientoInexistente() {
        // Intentar ocupar un asiento que no existe en Bus 1 Piso
        Asiento asiento1Piso = bus1Piso.ocuparAsiento("X1", cliente);  // El asiento debería ser null o no ocupable
        assertNull(asiento1Piso, "El asiento no debe existir y no debe poder ser ocupado.");

        // Intentar ocupar un asiento que no existe en Bus 2 Pisos
        Asiento asiento2Pisos = bus2Pisos.ocuparAsiento("X1", cliente);  // El asiento debería ser null o no ocupable
        assertNull(asiento2Pisos, "El asiento no debe existir y no debe poder ser ocupado.");
    }
    /**
     * Test para mostrar los asientos ocupados en ambos tipos de buses.
     */
    @Test
    public void testMostrarAsientosConClientes() {
        // Ocupamos un asiento en ambos buses y verificamos la salida
        bus1Piso.ocuparAsiento("E1", cliente);
        bus2Pisos.ocuparAsiento("E1", cliente);

        // El siguiente paso sería verificar en la consola si el asiento fue mostrado correctamente como ocupado.
        bus1Piso.mostrarAsientosConClientes(); // Verificar visualmente en la consola
        bus2Pisos.mostrarAsientosConClientes(); // Verificar visualmente en la consola
    }
    /**
     * Test para verificar el correcto funcionamiento del método `clone` en los buses.
     */
    @Test
    public void testCloneBus() {
        // Clonamos el bus 1 piso y 2 pisos, luego verificamos
        Bus clonedBus1Piso = bus1Piso.clone();
        Bus clonedBus2Pisos = bus2Pisos.clone();

        assertNotNull(clonedBus1Piso, "El bus 1 piso clonado no debe ser nulo.");
        assertNotSame(bus1Piso, clonedBus1Piso, "El bus 1 piso clonado debe ser una instancia diferente.");

        assertNotNull(clonedBus2Pisos, "El bus 2 pisos clonado no debe ser nulo.");
        assertNotSame(bus2Pisos, clonedBus2Pisos, "El bus 2 pisos clonado debe ser una instancia diferente.");

        assertEquals(bus1Piso.getAsientos().size(), clonedBus1Piso.getAsientos().size(), "El bus 1 piso clonado debe tener la misma cantidad de asientos.");
        assertEquals(bus2Pisos.getAsientos().size(), clonedBus2Pisos.getAsientos().size(), "El bus 2 pisos clonado debe tener la misma cantidad de asientos.");
    }
}
