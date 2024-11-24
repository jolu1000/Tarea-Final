package Logica;
import java.util.Random;

public class Patentes {
    public static String generarPatenteAleatoria() {
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
