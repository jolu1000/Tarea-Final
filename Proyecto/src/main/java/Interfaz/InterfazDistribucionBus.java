package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class InterfazDistribucionBus extends JFrame {

    private JComboBox<Ruta> rutasComboBox;
    private JPanel asientosPanel;
    private JTextField nombreField, apellidoField, rutField, emailField;
    private JButton verAsientosButton, reservarButton;
    private RutaFactory rutaFactory;

    public InterfazDistribucionBus() {
        // Configuración básica
        setTitle("BUSES JBG");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear autobuses y rutas
        Bus bus1 = new Bus1Piso(40, 10, 15, 10, 5);
        Bus bus2 = new Bus2Pisos(50, 20, 10, 15, 5);
        rutaFactory = new RutaFactory(List.of(bus1, bus2));  // Usar la fábrica para las rutas
        List<Ruta> rutas = rutaFactory.crearRutas(Ciudades.CONCEPCION, Ciudades.SANTIAGO);
        rutas.addAll(rutaFactory.crearRutas(Ciudades.CONCEPCION, Ciudades.SANTIAGO));
        rutas.addAll(rutaFactory.crearRutas(Ciudades.SANTIAGO, Ciudades.LOS_ANGELES));
        rutas.addAll(rutaFactory.crearRutas(Ciudades.CHILLAN, Ciudades.LOS_ANGELES));
        rutas.addAll(rutaFactory.crearRutas(Ciudades.CHILLAN, Ciudades.CONCEPCION));

        // Panel superior para seleccionar ruta
        JPanel panelSuperior = new JPanel(new FlowLayout());
        rutasComboBox = new JComboBox<>(rutas.toArray(new Ruta[0]));
        verAsientosButton = new JButton("Ver Asientos");
        panelSuperior.add(new JLabel("Seleccionar Ruta:"));
        panelSuperior.add(rutasComboBox);
        panelSuperior.add(verAsientosButton);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central para mostrar asientos
        asientosPanel = new JPanel();
        add(new JScrollPane(asientosPanel), BorderLayout.CENTER);

        // Panel inferior para ingresar datos del cliente
        JPanel panelInferior = new JPanel(new GridLayout(5, 2, 10, 10));
        nombreField = new JTextField();
        apellidoField = new JTextField();
        rutField = new JTextField();
        emailField = new JTextField();
        reservarButton = new JButton("Reservar Asiento");

        panelInferior.add(new JLabel("Nombre:"));
        panelInferior.add(nombreField);
        panelInferior.add(new JLabel("Apellido:"));
        panelInferior.add(apellidoField);
        panelInferior.add(new JLabel("RUT:"));
        panelInferior.add(rutField);
        panelInferior.add(new JLabel("Email:"));
        panelInferior.add(emailField);
        panelInferior.add(new JLabel());
        panelInferior.add(reservarButton);
        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        verAsientosButton.addActionListener(this::mostrarDistribucionAsientos);
        reservarButton.addActionListener(this::reservarAsiento);

        setVisible(true);
    }

    private void mostrarDistribucionAsientos(ActionEvent e) {
        Ruta rutaSeleccionada = (Ruta) rutasComboBox.getSelectedItem();
        if (rutaSeleccionada != null) {
            asientosPanel.removeAll();
            asientosPanel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columnas: 2 asientos - pasillo - 2 asientos

            for (int i = 0; i < rutaSeleccionada.getBus().getAsientos().size(); i++) {
                Asiento asiento = rutaSeleccionada.getBus().getAsientos().get(i);

                if (i % 4 == 2) { // Pasillo central
                    asientosPanel.add(new JLabel()); // Espacio vacío
                }

                JButton asientoButton = new JButton(asiento.getId());
                asientoButton.setBackground(asiento.isDisponible() ? Color.GREEN : Color.RED);
                asientoButton.setEnabled(asiento.isDisponible());

                // Acción al hacer clic en un asiento
                asientoButton.addActionListener(event -> mostrarDetallesAsiento(asiento, rutaSeleccionada));

                asientosPanel.add(asientoButton);
            }

            asientosPanel.revalidate();
            asientosPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una ruta válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDetallesAsiento(Asiento asiento, Ruta rutaSeleccionada) {
        String categoria = asiento.getTipoAsiento().name(); // Obtener la categoría del asiento
        int precioAsiento = rutaFactory.precioAsiento(asiento.getTipoAsiento()); // Obtener el precio del asiento
        int precioRuta = rutaFactory.crearPrecioViaje(rutaSeleccionada.getCiudadOrigen(), rutaSeleccionada.getCiudadDestino()); // Obtener el precio de la ruta
        int precioTotal = precioAsiento + precioRuta; // Precio total

        // Mostrar los detalles del asiento en un cuadro de diálogo
        JOptionPane.showMessageDialog(this,
                "Asiento: " + asiento.getId() + "\n" +
                        "Categoría: " + categoria + "\n" +
                        "Precio del Asiento: $" + precioAsiento + "\n" +
                        "Precio de la Ruta: $" + precioRuta + "\n" +
                        "Precio Total: $" + precioTotal,
                "Detalles del Asiento", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reservarAsiento(ActionEvent e) {
        try {
            Ruta rutaSeleccionada = (Ruta) rutasComboBox.getSelectedItem();
            if (rutaSeleccionada != null) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String rut = rutField.getText();
                String email = emailField.getText();

                if (!nombre.isBlank() && !apellido.isBlank() && !rut.isBlank() && !email.isBlank()) {
                    Cliente cliente = new Cliente(nombre, apellido, rut, email);
                    String asientoId = JOptionPane.showInputDialog(this, "Ingrese el ID del asiento:");

                    if (rutaSeleccionada.getBus().ocuparAsiento(asientoId, cliente)) {
                        JOptionPane.showMessageDialog(this, "Asiento reservado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        mostrarDistribucionAsientos(null); // Actualizar asientos
                    } else {
                        JOptionPane.showMessageDialog(this, "El asiento no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una ruta válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazDistribucionBus::new);
    }
}

