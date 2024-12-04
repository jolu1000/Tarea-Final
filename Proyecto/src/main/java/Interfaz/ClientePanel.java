package Interfaz;
import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 * Panel para la gestión de los datos del cliente y la reserva de asientos y compra de pasajes.
 * Este panel permite al usuario ingresar sus datos personales, reservar un asiento
 * y comprar los pasajes correspondientes, mostrando los detalles de la compra.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class ClientePanel extends JPanel {
    private PanelPrincipal panelPrincipal;
    private JTextField nombreField, apellidoField, rutField, emailField;
    private JButton reservarButton, comprarPasajesButton;
    private Cliente clienteActual;
    /**
     * Constructor de la clase `ClientePanel`.
     *
     * Este constructor inicializa el panel con campos de texto para ingresar los datos del cliente
     * y botones para realizar la reserva de asiento y la compra de pasajes.
     *
     * @param panelPrincipal El panel principal que contiene la interfaz principal de la aplicación.
     */
    public ClientePanel(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        setLayout(new BorderLayout());
        // Panel de formulario para ingresar los datos del cliente
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        nombreField = new JTextField();
        apellidoField = new JTextField();
        rutField = new JTextField();
        emailField = new JTextField();
        reservarButton = new JButton("Reservar Asiento");

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Apellido:"));
        formPanel.add(apellidoField);
        formPanel.add(new JLabel("RUT:"));
        formPanel.add(rutField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        // Panel de botones para reservar y comprar pasajes
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(reservarButton);

        comprarPasajesButton = new JButton("Comprar Pasajes");
        buttonPanel.add(comprarPasajesButton);

        add(formPanel, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
        // Ajustar el tamaño de los botones
        Dimension buttonSize = reservarButton.getPreferredSize();
        comprarPasajesButton.setPreferredSize(buttonSize);
        // Agregar listeners para los botones
        reservarButton.addActionListener(this::reservarAsiento);
        comprarPasajesButton.addActionListener(this::comprarPasajes);
    }
    /**
     * Método para realizar la reserva de un asiento.
     * Este método valida los datos ingresados, crea o actualiza el cliente, y realiza la reserva del asiento.
     * Si todo es correcto, muestra un mensaje de éxito, de lo contrario muestra mensajes de error.
     *
     * @param e El evento generado por el clic en el botón "Reservar Asiento".
     */
    private void reservarAsiento(ActionEvent e) {
        try {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String rut = rutField.getText();
            String email = emailField.getText();

            if (!nombre.isBlank() && !apellido.isBlank() && !rut.isBlank() && !email.isBlank()) {
                if (!validarRut(rut)) {
                    JOptionPane.showMessageDialog(this, "El RUT ingresado no es válido. Asegúrese de que el formato sea correcto.", "Error", JOptionPane.ERROR_MESSAGE);
                    rutField.setText("");
                    return;
                }

                if (clienteActual == null) {
                    clienteActual = new Cliente(nombre, apellido, rut, email); // Crear cliente solo si no hay uno
                } else {
                    clienteActual.setNombre(nombre); // Si ya existe, actualiza los datos
                    clienteActual.setApellido(apellido);
                    clienteActual.setRut(rut);
                    clienteActual.setEmail(email);
                }

                Ruta rutaSeleccionada = panelPrincipal.getPanelSeleccionRuta().getRutaSeleccionada();
                String asientoId = JOptionPane.showInputDialog(this, "Ingrese el ID del asiento:");

                Asiento asiento = rutaSeleccionada.getBus().ocuparAsiento(asientoId, clienteActual);
                if (asiento != null) {
                    clienteActual.agregarAsientoReservado(asiento);  // Agregar el asiento a la lista del cliente
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
    /**
     * Método para realizar la compra de los pasajes.
     * Este método muestra los detalles de los asientos comprados y el total de la compra.
     *
     * @param e El evento generado por el clic en el botón "Comprar Pasajes".
     */
    private void comprarPasajes(ActionEvent e) {
        Ruta rutaSeleccionada = panelPrincipal.getPanelSeleccionRuta().getRutaSeleccionada();
        if (rutaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "No hay una ruta seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (clienteActual == null) {
            JOptionPane.showMessageDialog(this, "No hay un cliente asociado a la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Asiento> asientosComprados = clienteActual.getAsientosReservados();  // Obtener todos los asientos reservados por el cliente
        if (asientosComprados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay asientos comprados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder detalles = new StringBuilder("Asientos Comprados:\n");
        int total = 0;

        int precioRuta = rutaSeleccionada.getPrecio();
        detalles.append("Precio Ruta: $").append(precioRuta).append("\n");
        total += precioRuta;

        for (Asiento asiento : asientosComprados) {
            detalles.append("Asiento: ").append(asiento.getId())
                    .append(", Tipo: ").append(asiento.getTipoAsiento())
                    .append(", Bus (Patente): ").append(rutaSeleccionada.getBus().getPatente())
                    .append(", Precio Asiento: $").append(asiento.getPrecio())
                    .append("\n");
            total += asiento.getPrecio();
        }

        detalles.append("\nCliente:\n")
                .append("Nombre: ").append(clienteActual.getNombre())
                .append(", Apellido: ").append(clienteActual.getApellido())
                .append(", RUT: ").append(clienteActual.getRut())
                .append("\n");

        detalles.append("Total: $").append(total);

        JOptionPane.showMessageDialog(this, detalles.toString(), "Detalles de la Compra", JOptionPane.INFORMATION_MESSAGE);

        clienteActual = null;
        nombreField.setText("");
        apellidoField.setText("");
        rutField.setText("");
        emailField.setText("");
    }
    /**
     * Método para validar el formato del RUT ingresado.
     *
     * @param rut El RUT a validar.
     * @return True si el RUT tiene el formato correcto, false en caso contrario.
     */
    private boolean validarRut(String rut) {
        String[] partes = rut.split("-");
        if (partes.length != 2) {
            return false;
        }

        String numeros = partes[0];
        String verificador = partes[1];

        if (!numeros.matches("\\d+")) {
            return false;
        }

        if (!verificador.matches("[0-9a-zA-Z]+")) {
            return false;
        }

        return true;
    }
}

