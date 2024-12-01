package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AsientosPanel extends JPanel {
    private JPanel asientosPanel;

    public AsientosPanel() {
        setLayout(new BorderLayout());
        asientosPanel = new JPanel();
        add(new JScrollPane(asientosPanel), BorderLayout.CENTER);
    }

    public void mostrarDistribucionAsientos(Ruta rutaSeleccionada) {
        asientosPanel.removeAll();
        asientosPanel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columnas: 2 asientos - pasillo - 2 asientos

        for (int i = 0; i < rutaSeleccionada.getBus().getAsientos().size(); i++) {
            Asiento asiento = rutaSeleccionada.getBus().getAsientos().get(i);

            if (i % 4 == 2) { // Pasillo central
                asientosPanel.add(new JLabel()); // Espacio vacío
            }

            JButton asientoButton = new JButton(asiento.getId());
            // Usa el color basado en el tipo de asiento
            asientoButton.setBackground(asiento.isDisponible()
                    ? AsientoColor.getColorPorTipo(asiento.getTipoAsiento())
                    : Color.RED);
            asientoButton.setEnabled(asiento.isDisponible());

            asientoButton.addActionListener(event -> mostrarDetallesAsiento(asiento, rutaSeleccionada));
            asientosPanel.add(asientoButton);
        }

        revalidate();
        repaint();
    }

    private void mostrarDetallesAsiento(Asiento asiento, Ruta rutaSeleccionada) {
        RutaFactory rutaFactory = new RutaFactory(List.of(rutaSeleccionada.getBus()));
        String categoria = asiento.getTipoAsiento().name(); // Obtener la categoría del asiento
        int precioAsiento = rutaFactory.precioAsiento(asiento.getTipoAsiento()); // Obtener el precio del asiento
        int precioRuta = rutaFactory.crearPrecioViaje(rutaSeleccionada.getCiudadOrigen(), rutaSeleccionada.getCiudadDestino()); // Obtener el precio de la ruta
        int precioTotal = precioAsiento + precioRuta; // Precio total

        JOptionPane.showMessageDialog(this,
                "Asiento: " + asiento.getId() + "\n" +
                        "Categoría: " + categoria + "\n" +
                        "Precio del Asiento: $" + precioAsiento + "\n" +
                        "Precio de la Ruta: $" + precioRuta + "\n" +
                        "Precio Total: $" + precioTotal,
                "Detalles del Asiento", JOptionPane.INFORMATION_MESSAGE);
    }
}
