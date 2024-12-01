package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClientePanel extends JPanel {
    private JTextField nombreField, apellidoField, rutField, emailField;
    private JButton reservarButton;

    public ClientePanel() {
        setLayout(new GridLayout(5, 2, 10, 10));

        nombreField = new JTextField();
        apellidoField = new JTextField();
        rutField = new JTextField();
        emailField = new JTextField();
        reservarButton = new JButton("Reservar Asiento");

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Apellido:"));
        add(apellidoField);
        add(new JLabel("RUT:"));
        add(rutField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel());
        add(reservarButton);

        reservarButton.addActionListener(this::reservarAsiento);
    }

    private void reservarAsiento(ActionEvent e) {
        try {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String rut = rutField.getText();
            String email = emailField.getText();

            if (!nombre.isBlank() && !apellido.isBlank() && !rut.isBlank() && !email.isBlank()) {
                // Obtener la ruta seleccionada desde el panel principal
                PanelPrincipal panelPrincipal = (PanelPrincipal) getTopLevelAncestor();
                Ruta rutaSeleccionada = panelPrincipal.getPanelSeleccionRuta().getRutaSeleccionada();

                Cliente cliente = new Cliente(nombre, apellido, rut, email);
                String asientoId = JOptionPane.showInputDialog(this, "Ingrese el ID del asiento:");

                if (rutaSeleccionada.getBus().ocuparAsiento(asientoId, cliente)) {
                    JOptionPane.showMessageDialog(this, "Asiento reservado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    panelPrincipal.getAsientosPanel().mostrarDistribucionAsientos(rutaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(this, "El asiento no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Complete todos los campos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

