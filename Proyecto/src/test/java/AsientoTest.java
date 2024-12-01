import Logica.Asiento;
import Logica.Cliente;
import Logica.TipoAsiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AsientoTest {
    private Asiento asiento;
    private Cliente cliente;
    private String getId;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Juan", "Pérez", "12345678-9", "juan@mail.com");
        asiento = new Asiento("A1", TipoAsiento.ESTANDAR) {};  // Implementación anónima para probar la clase abstracta
    }

    @Test
    public void testAsientoInicialDisponible() {
        assertTrue(asiento.isDisponible(), "El asiento recién creado debe estar disponible.");
    }

    @Test
    public void testOcuparAsiento() {
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado después de llamar a ocupar.");
    }

    @Test
    public void testSetClienteAsignaYOcupaAsiento() {
        asiento.setCliente(cliente);
        assertEquals(cliente, asiento.getCliente(), "El cliente debe ser asignado correctamente al asiento.");
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado después de asignar un cliente.");
    }

    @Test
    public void testCloneAsiento() {
        Asiento clonedAsiento = asiento.clone();
        assertNotNull(clonedAsiento, "El asiento clonado no debe ser nulo.");
        assertNotSame(asiento, clonedAsiento, "El asiento clonado debe ser una instancia diferente.");
        assertEquals(asiento.getId(), clonedAsiento.getId(), "El ID del asiento clonado debe coincidir con el original.");
        assertEquals(asiento.isDisponible(), clonedAsiento.isDisponible(), "El estado de disponibilidad debe ser el mismo.");
    }
    @Test
    public void testSetClienteConNulo() {
        asiento.setCliente(null);
        assertNull(asiento.getCliente(), "El cliente debe ser nulo después de asignar null.");
        assertTrue(asiento.isDisponible(), "El asiento debe estar disponible si no se asigna ningún cliente.");
    }
    @Test
    public void testOcuparAsientoDosVeces() {
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado después de la primera ocupación.");

        // Intentamos ocuparlo de nuevo
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento sigue ocupado y no debe cambiar de estado.");
    }
    @Test
    public void testOcuparSinAsignarCliente() {
        // Asiento no tiene cliente aún
        asiento.ocupar();
        assertFalse(asiento.isDisponible(), "El asiento debe estar ocupado incluso sin asignar un cliente.");
    }
}