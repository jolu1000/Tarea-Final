package Interfaz;

import javax.swing.*;

public class Ventana {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear ventana principal
            JFrame frame = new JFrame("Reserva de Asientos de Buses");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);

            // Añadir el panel principal al frame
            frame.setContentPane(new PanelPrincipal()); // Integra PanelPrincipal como contenido

            // Mostrar ventana
            frame.setVisible(true);
        });
    }
}

