package Interfaz;

import javax.swing.*;

public class Ventana {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Reserva de Asientos de Buses");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);

            frame.setContentPane(new PanelPrincipal());

            frame.setVisible(true);
        });
    }
}


