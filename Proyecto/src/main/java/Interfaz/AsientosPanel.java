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
        asientosPanel.setLayout(new GridLayout(0, 5, 10, 10));

        for (int i = 0; i < rutaSeleccionada.getBus().getAsientos().size(); i++) {
            Asiento asiento = rutaSeleccionada.getBus().getAsientos().get(i);

            if (i % 4 == 2) {
                asientosPanel.add(new JLabel());
            }

            JButton asientoButton = new JButton(asiento.getId());
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
        String categoria = asiento.getTipoAsiento().name();
        int precioAsiento = rutaFactory.precioAsiento(asiento.getTipoAsiento());
        int precioRuta = rutaFactory.crearPrecioViaje(rutaSeleccionada.getCiudadOrigen(), rutaSeleccionada.getCiudadDestino());
        int precioTotal = precioAsiento + precioRuta;

        JOptionPane.showMessageDialog(this,
                "Asiento: " + asiento.getId() + "\n" +
                        "Categoría: " + categoria + "\n" +
                        "Precio del Asiento: $" + precioAsiento + "\n" +
                        "Precio de la Ruta: $" + precioRuta + "\n" +
                        "Precio Total: $" + precioTotal,
                "Detalles del Asiento", JOptionPane.INFORMATION_MESSAGE);
    }
}
