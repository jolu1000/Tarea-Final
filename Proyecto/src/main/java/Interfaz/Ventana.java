package Interfaz;
import javax.swing.*;
/**
 * Clase principal que inicializa y muestra la ventana para la reserva de asientos de buses.
 *
 * Esta clase utiliza la biblioteca Swing para crear la interfaz gráfica de usuario (GUI).
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class Ventana {
    /**
     * Método principal que inicia la aplicación y configura la ventana.
     *
     * @param args Argumentos de línea de comandos, no utilizados en este caso.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Crear ventana con el titulo
            JFrame frame = new JFrame("Reserva de Asientos de Buses");
            // Comportamiento de cierre de la ventana (salir de la aplicación).
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Tamaño de la ventana
            frame.setSize(1000, 600);
            //Centra la ventana en la pantalla
            frame.setLocationRelativeTo(null);
            // Asigna un panel principal a la ventana.
            frame.setContentPane(new PanelPrincipal());
            //Hacer visible la ventana
            frame.setVisible(true);
        });
    }
}


