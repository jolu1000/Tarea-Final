package Logica;
import java.util.Random;

public class Patentes {
    public static String generarPatenteAleatoria() {
        Random random = new Random();
        StringBuilder patente = new StringBuilder();
        // Generar 4 letras
        for (int i = 0; i < 4; i++) {
            patente.append((char) (random.nextInt(26) + 'A')); // Letras de A a Z
        }
        // Generar 2 números
        patente.append("-");
        for (int i = 0; i < 2; i++) {
            patente.append(random.nextInt(10)); // Números de 0 a 9
        }
        return patente.toString();
    }
}
