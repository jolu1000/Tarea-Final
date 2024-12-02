import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/*class Bus1PisoTest {

    private Bus1Piso bus1Piso;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        bus1Piso = new Bus1Piso(10,3, 2, 2, 3);  // Bus con capacidad 10 (Ejemplo con 1piso)
        cliente = new Cliente("Juan", "Pérez", "12345678-9", "juan@mail.com");

        // Inicializando asientos: 3 Estándar, 2 SemiCama, 2 Cama, 3 Premium
        bus1Piso.inicializarAsientos(3, 2, 2, 3);
    }

    @Test
    public void testInicializarAsientos() {
        // Verifica que los asientos sean creados correctamente
        List<Asiento> asientos = bus1Piso.getAsientos();
        assertEquals(10, asientos.size(), "El bus debe tener 10 asientos.");
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
        // Clonamos el bus y verificamos
        Bus clonedBus = bus1Piso.clone();

        assertNotNull(clonedBus, "El bus clonado no debe ser nulo.");
        assertNotSame(bus1Piso, clonedBus, "El bus clonado debe ser una instancia diferente.");
        assertEquals(bus1Piso.getPatente(), clonedBus.getPatente(), "Las patentes deberían ser diferentes ya que se genera aleatoriamente.");
        assertEquals(bus1Piso.getAsientos().size(), clonedBus.getAsientos().size(), "El bus clonado debe tener la misma cantidad de asientos.");
    }

    @Test
    public void testAddAsiento() {
        // Intentar agregar un asiento extra cuando no se alcanza la capacidad
        Asiento nuevoAsiento = new AsientoEstandar("E10");
        bus1Piso.addAsiento(nuevoAsiento);
        assertEquals(11, bus1Piso.getAsientos().size(), "El asiento debe ser agregado correctamente.");

        // Intentar agregar un asiento cuando el bus ya está lleno
        Asiento asientoExtra = new AsientoEstandar("E12");
        try {
            bus1Piso.addAsiento(asientoExtra);
            fail("Debería lanzar IllegalStateException cuando se agrega un asiento extra.");
        } catch (IllegalStateException e) {
            assertEquals("No se pueden agregar más asientos. Se ha alcanzado la capacidad máxima.", e.getMessage());
        }
    }

    @Test
    public void testAddAsientoConIdDuplicado() {
        // Intentar agregar un asiento con un ID duplicado
        Asiento asientoDuplicado = new AsientoEstandar("E1");
        try {
            bus1Piso.addAsiento(asientoDuplicado);
            fail("Debería lanzar IllegalArgumentException por ID duplicado.");
        } catch (IllegalArgumentException e) {
            assertEquals("Ya existe un asiento con el mismo ID: E1", e.getMessage());
        }
    }
}


