package Logica;
import java.util.Random;
/**
 * Clase que proporciona un método para generar patentes aleatorias.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class Patentes {
    public static String generarPatenteAleatoria() {
        /**
         * Genera una patente aleatoria con el formato de 4 letras seguidas de 2 dígitos numéricos.
         * Ejemplo: "ABCD12"
         *
         * @return Una cadena que representa una patente aleatoria.
         */
        Random random = new Random();
        StringBuilder patente = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            patente.append((char) (random.nextInt(26) + 'A'));
        }
        for (int i = 0; i < 2; i++) {
            patente.append(random.nextInt(10));
        }
        return patente.toString();
    }
}
