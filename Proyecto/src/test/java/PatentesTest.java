import Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Clase de pruebas unitarias para la clase `Patentes`.
 * Estas pruebas verifican el correcto funcionamiento de la generación y validación de patentes aleatorias.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class PatentesTest {

    private String patente;

    @BeforeEach
    public void setUp() {
        // Inicializamos la patente antes de cada test
        patente = Patentes.generarPatenteAleatoria();
    }
    /**
     * Test para verificar que la patente generada tiene el formato correcto.
     */
    @Test
    public void testFormatoPatente() {
        // Verifica que la patente tiene exactamente 6 caracteres
        assertEquals(6, patente.length(), "La patente debe tener 6 caracteres.");

        // Verifica que los primeros 4 caracteres son letras
        for (int i = 0; i < 4; i++) {
            char c = patente.charAt(i);
            assertTrue(Character.isLetter(c), "El caracter " + c + " debe ser una letra.");
        }

        // Verifica que los últimos 2 caracteres son números
        for (int i = 4; i < 6; i++) {
            char c = patente.charAt(i);
            assertTrue(Character.isDigit(c), "El caracter " + c + " debe ser un número.");
        }
    }
    /**
     * Test para verificar que las patentes generadas aleatoriamente no sean iguales.
     */
    @Test
    public void testGeneracionPatentesAleatorias() {
        String patente2 = Patentes.generarPatenteAleatoria();

        // Verifica que las patentes generadas sean diferentes
        assertNotEquals(patente, patente2, "Las patentes generadas no deben ser iguales.");
    }
    /**
     * Test para verificar que la patente generada sigue el formato válido.
     */
    @Test
    public void testPatenteEsValida() {
        // Verifica que la patente tiene el formato correcto (4 letras + 2 números)
        assertTrue(patente.matches("[A-Z]{4}[0-9]{2}"), "La patente no sigue el formato válido.");
    }
    /**
     * Test para verificar que las letras y números de la patente están en los rangos correctos.
     */
    @Test
    public void testRangoDeCaracteres() {
        // Verifica que las letras están en el rango A-Z
        for (int i = 0; i < 4; i++) {
            char c = patente.charAt(i);
            assertTrue(c >= 'A' && c <= 'Z', "La letra " + c + " no está en el rango A-Z.");
        }

        // Verifica que los números están en el rango 0-9
        for (int i = 4; i < 6; i++) {
            char c = patente.charAt(i);
            assertTrue(c >= '0' && c <= '9', "El número " + c + " no está en el rango 0-9.");
        }
    }
    /**
     * Test para verificar que se generen varias patentes únicas.
     */
    @Test
    public void testGenerarVariasPatentes() {
        Set<String> patentesGeneradas = new HashSet<>();

        // Genera 100 patentes y asegura que todas sean únicas
        for (int i = 0; i < 100; i++) {
            String nuevaPatente = Patentes.generarPatenteAleatoria();
            assertTrue(nuevaPatente.matches("[A-Z]{4}[0-9]{2}"), "La patente " + nuevaPatente + " no es válida.");
            patentesGeneradas.add(nuevaPatente);
        }

        // Verifica que todas las patentes sean únicas
        assertEquals(100, patentesGeneradas.size(), "No todas las patentes generadas son únicas.");
    }
}
