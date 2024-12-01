import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        // Crear un cliente con los datos de ejemplo
        cliente = new Cliente("Juan", "Pérez", "12345678-9", "juan@mail.com");
    }

    @Test
    public void testEquals() {
        Cliente cliente1 = new Cliente("Ana", "Lopez", "98765432-1", "ana@mail.com");
        Cliente cliente2 = new Cliente("Ana", "Lopez", "98765432-1", "ana@mail.com");

        // Verificar que dos clientes con los mismos datos son iguales
        assertTrue(cliente1.equals(cliente2), "Los clientes con los mismos datos deben ser iguales.");
    }

    @Test
    public void testEqualsConClienteDiferente() {
        Cliente cliente1 = new Cliente("Ana", "Lopez", "98765432-1", "ana@mail.com");
        Cliente cliente2 = new Cliente("Luis", "Gonzalez", "12345678-9", "luis@mail.com");

        // Verificar que dos clientes con datos diferentes no son iguales
        assertFalse(cliente1.equals(cliente2), "Los clientes con datos diferentes no deben ser iguales.");
    }
}
