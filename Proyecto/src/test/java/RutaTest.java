import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;
import java.util.Date;

public class RutaTest {

    private Bus1Piso bus1;
    private Bus2Pisos bus2;
    private Ruta ruta1;
    private Ruta ruta2;

    @BeforeEach
    public void setUp() {
        // Crear instancias de buses
        bus1 = new Bus1Piso(40, 10, 10, 10, 10);  // Crear un Bus1Piso
        bus2 = new Bus2Pisos(50, 15, 15, 10, 10);  // Crear un Bus2Pisos

        // Crear instancias de rutas
        Date fecha1 = new Date();
        LocalTime hora1 = LocalTime.of(8, 30);
        ruta1 = new Ruta(Ciudades.SANTIAGO, Ciudades.LOS_ANGELES, fecha1, hora1, 15000, bus1);

        Date fecha2 = new Date();
        LocalTime hora2 = LocalTime.of(10, 45);
        ruta2 = new Ruta(Ciudades.SANTIAGO, Ciudades.CONCEPCION, fecha2, hora2, 12000, bus2);
    }

    @Test
    public void testRutaCreadaCorrectamente() {
        // Verificar que la ruta se ha creado correctamente
        assertNotNull(ruta1, "La ruta no debe ser nula.");
        assertEquals(Ciudades.SANTIAGO, ruta1.getCiudadOrigen(), "La ciudad de origen debe ser SANTIAGO.");
        assertEquals(Ciudades.LOS_ANGELES, ruta1.getCiudadDestino(), "La ciudad de destino debe ser LOS_ANGELES.");
        assertEquals(15000, ruta1.getPrecio(), "El precio debe ser 15000.");
        assertNotNull(ruta1.getBus(), "El bus de la ruta no debe ser nulo.");
    }

    @Test
    public void testRutaConHoraCorrecta() {
        // Verificar que la hora de la ruta es correcta
        assertEquals(LocalTime.of(8, 30), ruta1.getHora(), "La hora debe ser 08:30.");
        assertEquals(LocalTime.of(10, 45), ruta2.getHora(), "La hora debe ser 10:45.");
    }

    @Test
    public void testCambioDeBus() {
        // Cambiar el bus de una ruta y verificar que el cambio se realizó correctamente
        Bus nuevoBus = new Bus1Piso(40, 15, 10, 10, 5);
        ruta1.setBus(nuevoBus);
        assertEquals(nuevoBus, ruta1.getBus(), "El bus de la ruta debe haber cambiado correctamente.");
    }

    @Test
    public void testPrecioRuta() {
        // Verificar que el precio de la ruta es el correcto
        assertEquals(15000, ruta1.getPrecio(), "El precio de la ruta debe ser 15000.");
        assertEquals(12000, ruta2.getPrecio(), "El precio de la ruta debe ser 12000.");
    }

    @Test
    public void testRutaConFechaCorrecta() {
        // Verificar que la fecha de la ruta no sea nula
        assertNotNull(ruta1.getFecha(), "La fecha de la ruta no debe ser nula.");
    }

    @Test
    public void testCambioDeCiudadDestino() {
        // Cambiar la ciudad de destino de la ruta y verificar que el cambio se realizó correctamente
        ruta1.setCiudadDestino(Ciudades.CONCEPCION);
        assertEquals(Ciudades.CONCEPCION, ruta1.getCiudadDestino(), "La ciudad de destino debe ser PUERTO_MONTT.");
    }

    @Test
    public void testComparacionRutas() {
        // Verificar que las rutas con diferentes ciudades y buses sean distintas
        assertNotEquals(ruta1, ruta2, "Las rutas deben ser diferentes.");
    }
}
