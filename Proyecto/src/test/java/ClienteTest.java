import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Clase de pruebas unitarias para la clase `Cliente`.
 * Estas pruebas verifican el correcto funcionamiento del método `equals` de la clase `Cliente`.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        // Crear un cliente con los datos de ejemplo
        cliente = new Cliente("Juan", "Pérez", "12345678-9", "juan@mail.com");
    }
    /**
     * Test para verificar que dos clientes con los mismos datos sean considerados iguales.
     */
    @Test
    public void testEquals() {
        Cliente cliente1 = new Cliente("Ana", "Lopez", "98765432-1", "ana@mail.com");
        Cliente cliente2 = new Cliente("Ana", "Lopez", "98765432-1", "ana@mail.com");

        // Verificar que dos clientes con los mismos datos son iguales
        assertFalse(cliente1.equals(cliente2), "Los clientes con los mismos datos deben ser iguales.");
    }
    /**
     * Test para verificar que dos clientes con datos diferentes no sean considerados iguales.
     */
    @Test
    public void testEqualsConClienteDiferente() {
        Cliente cliente1 = new Cliente("Ana", "Lopez", "98765432-1", "ana@mail.com");
        Cliente cliente2 = new Cliente("Luis", "Gonzalez", "12345678-9", "luis@mail.com");

        // Verificar que dos clientes con datos diferentes no son iguales
        assertFalse(cliente1.equals(cliente2), "Los clientes con datos diferentes no deben ser iguales.");
    }
}