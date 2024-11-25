package Interfaz;
import javax.swing.*;

public class Ventana {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Reserva de Asientos de Buses");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }
}
