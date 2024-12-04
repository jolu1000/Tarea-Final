import Logica.Asiento;
import Logica.Cliente;
import Logica.TipoAsiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Clase de pruebas unitarias para la clase Asiento.
 * Realiza pruebas sobre las funcionalidades de la clase Asiento.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class AsientoTest {
    private Asiento asiento;
    private Cliente cliente;
    private String getId;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Juan", "Pérez", "12345678-9", "juan@mail.com");
        asiento = new Asiento("A1", TipoAsiento.ESTANDAR) {};}
    /**
     * Test para verificar que un asiento recién creado está disponible.
     */
    @Test
    public void testAsientoInicialDisponible() {
        assertTrue(asiento.isDisponible(), "El asiento recién creado debe estar disponible.");
    }
    /**
     * Test para verificar que un asiento se ocupa correctamente.
     */
    @Test
    public void testOcuparAsiento() {
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado después de llamar a ocupar.");
    }
    /**
     * Test para verificar que cuando se asigna un cliente a un asiento, este queda ocupado.
     */
    @Test
    public void testSetClienteAsignaYOcupaAsiento() {
        asiento.setCliente(cliente);
        assertEquals(cliente, asiento.getCliente(), "El cliente debe ser asignado correctamente al asiento.");
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado después de asignar un cliente.");
    }
    /**
     * Test para verificar que se pueda clonar un asiento correctamente.
     * El asiento clonado no debe ser el mismo objeto, pero sí debe tener las mismas propiedades.
     */
    @Test
    public void testCloneAsiento() {
        Asiento clonedAsiento = asiento.clone();
        assertNotNull(clonedAsiento, "El asiento clonado no debe ser nulo.");
        assertNotSame(asiento, clonedAsiento, "El asiento clonado debe ser una instancia diferente.");
        assertEquals(asiento.getId(), clonedAsiento.getId(), "El ID del asiento clonado debe coincidir con el original.");
        assertEquals(asiento.isDisponible(), clonedAsiento.isDisponible(), "El estado de disponibilidad debe ser el mismo.");
    }

    /**
     * Test para verificar que se puede asignar un valor nulo al cliente y que el asiento vuelve a estar disponible.
     */
    @Test
    public void testSetClienteConNulo() {
        asiento.setCliente(null);
        assertNull(asiento.getCliente(), "El cliente debe ser nulo después de asignar null.");
        assertTrue(asiento.isDisponible(), "El asiento debe estar disponible si no se asigna ningún cliente.");
    }
    /**
     * Test para verificar que intentar ocupar un asiento dos veces no cambia su estado.
     */
    @Test
    public void testOcuparAsientoDosVeces() {
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado después de la primera ocupación.");

        // Intentamos ocuparlo de nuevo
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento sigue ocupado y no debe cambiar de estado.");
    }
    /**
     * Test para verificar que un asiento se puede ocupar incluso sin asignar un cliente.
     */
    @Test
    public void testOcuparSinAsignarCliente() {
        // Asiento no tiene cliente aún
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado incluso sin asignar un cliente.");
    }
    /**
     * Test para verificar que al clonar un asiento con cliente asignado, el cliente también se clona correctamente.
     */
    @Test
    public void testClonePropiedades() {
        asiento.setCliente(cliente);  // Asignamos un cliente al asiento
        Asiento clonedAsiento = asiento.clone();
        assertEquals(asiento.getId(), clonedAsiento.getId(), "El ID del asiento clonado debe coincidir con el original.");
        assertEquals(asiento.isDisponible(), clonedAsiento.isDisponible(), "El estado de disponibilidad debe ser el mismo.");
        assertEquals(asiento.getCliente(), clonedAsiento.getCliente(), "El cliente del asiento clonado debe ser el mismo.");
    }

}