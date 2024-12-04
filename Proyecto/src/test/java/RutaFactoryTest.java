import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase de pruebas para la clase RutaFactory.
 * Esta clase contiene pruebas unitarias para la creación de rutas, validación de precios, y otros aspectos del sistema de rutas y buses.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class RutaFactoryTest {

    private List<Bus> buses;
    private RutaFactory rutaFactory;

    @BeforeEach
    public void setUp() {
        // Preparar buses con subclases Bus1Piso y Bus2Pisos antes de cada prueba
        buses = new ArrayList<>();

        // Crear un Bus1Piso con capacidad y tipos de asientos
        Bus1Piso bus1 = new Bus1Piso(40, 10, 10, 10, 10); // 40 asientos, 10 de cada tipo
        buses.add(bus1);

        // Crear un Bus2Pisos con capacidad y tipos de asientos
        Bus2Pisos bus2 = new Bus2Pisos(50, 15, 15, 10, 10); // 50 asientos, 15 de cada tipo
        buses.add(bus2);

        // Instanciar la RutaFactory con los buses creados
        rutaFactory = new RutaFactory(buses);
    }
    /**
     * Prueba la creación de rutas válidas entre dos ciudades.
     */
    @Test
    public void testCrearRutas_Validas() {
        // Prueba la creación de rutas entre dos ciudades válidas
        List<Ruta> rutas = rutaFactory.crearRutas(Ciudades.SANTIAGO, Ciudades.LOS_ANGELES);

        // Verifica que se hayan creado rutas
        assertNotNull(rutas);
        assertTrue(rutas.size() > 0, "Debe haber rutas generadas.");

        // Verifica que las rutas son correctas
        Ruta primeraRuta = rutas.get(0);
        assertEquals(Ciudades.SANTIAGO, primeraRuta.getCiudadOrigen(), "La ciudad de origen debe ser SANTIAGO.");
        assertEquals(Ciudades.LOS_ANGELES, primeraRuta.getCiudadDestino(), "La ciudad de destino debe ser LOS_ANGELES.");
    }
    /**
     * Prueba el caso donde no hay buses disponibles para la creación de rutas.
     */
    @Test
    public void testCrearRutas_SinBuses() {
        // Prueba si lanza excepción si no hay buses disponibles
        RutaFactory rutaFactoryVacio = new RutaFactory(new ArrayList<>());
        assertThrows(IllegalStateException.class, () -> rutaFactoryVacio.crearRutas(Ciudades.SANTIAGO, Ciudades.LOS_ANGELES),
                "Se esperaba una excepción porque no hay buses disponibles.");
    }
    /**
     * Prueba la creación de rutas con ciudades origen o destino nulas.
     */
    @Test
    public void testCrearRutas_Nulos() {
        // Verifica que lanza excepción si la ciudad origen o destino son nulas
        assertThrows(IllegalArgumentException.class, () -> rutaFactory.crearRutas(null, Ciudades.LOS_ANGELES),
                "Se esperaba una excepción porque la ciudad origen es nula.");

        assertThrows(IllegalArgumentException.class, () -> rutaFactory.crearRutas(Ciudades.SANTIAGO, null),
                "Se esperaba una excepción porque la ciudad destino es nula.");
    }

    @Test
    public void testPrecioViaje() {
        // Verifica que el precio de un viaje sea correcto entre dos ciudades
        int precio = rutaFactory.crearPrecioViaje(Ciudades.SANTIAGO, Ciudades.LOS_ANGELES);
        assertEquals(15000, precio, "El precio del viaje entre SANTIAGO y LOS_ANGELES debería ser 15000.");
    }
    /**
     * Prueba que el precio del viaje entre dos ciudades se calcule correctamente.
     */
    @Test
    public void testPrecioAsiento() {
        // Verifica el precio del asiento de tipo SEMI_CAMA
        int precioAsiento = rutaFactory.precioAsiento(TipoAsiento.SEMI_CAMA);
        assertEquals(3000, precioAsiento, "El precio de un asiento SEMI_CAMA debe ser 3000.");

        // Verifica el precio del asiento de tipo PREMIUM
        precioAsiento = rutaFactory.precioAsiento(TipoAsiento.PREMIUM);
        assertEquals(10000, precioAsiento, "El precio de un asiento PREMIUM debe ser 10000.");
    }
    /**
     * Prueba que el precio total del viaje se calcule correctamente.
     */
    @Test
    public void testPrecioTotal() {
        // Verifica que el cálculo del precio total de una ruta sea correcto
        Bus bus1 = buses.get(0); // Bus1Piso
        int precioTotal = rutaFactory.PrecioTotal(Ciudades.SANTIAGO, Ciudades.LOS_ANGELES, bus1);
        assertTrue(precioTotal > 0, "El precio total debe ser positivo.");
    }
    /**
     * Prueba la generación de rutas correctamente cuando los buses tienen asientos asignados.
     */
    @Test
    public void testGeneracionDeRutasConAsientos() {
        // Prueba si se generan rutas correctamente cuando los buses tienen asientos asignados
        Bus1Piso bus1 = new Bus1Piso(40, 10, 10, 10, 10);  // Crear un Bus1Piso
        buses.add(bus1);

        List<Ruta> rutas = rutaFactory.crearRutas(Ciudades.SANTIAGO, Ciudades.CHILLAN);
        assertNotNull(rutas);
        assertTrue(rutas.size() > 0, "Se deberían generar rutas.");
    }
    /**
     * Prueba la clonación de buses.
     */
    @Test
    public void testClonacionDeBuses() {
        // Verifica que la clonación de un Bus1Piso funcione correctamente
        Bus1Piso busOriginal = new Bus1Piso(40, 10, 10, 10, 10);
        Bus1Piso busClonado = busOriginal.clone();

        // Asegúrate de que el bus clonado es distinto del original
        assertNotSame(busOriginal, busClonado, "El bus clonado no debe ser el mismo que el original.");
        assertEquals(busOriginal.getClass(), busClonado.getClass(), "El tipo de bus clonado debe ser el mismo.");
    }
}
