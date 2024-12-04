package Interfaz;
import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 * La clase AsientosPanel es responsable de mostrar la distribución de los asientos en la interfaz gráfica
 * y proporcionar detalles sobre los asientos cuando el usuario interactúa con ellos.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class AsientosPanel extends JPanel {
    private JPanel asientosPanel;
    /**
     * Constructor de la clase AsientosPanel.
     * Inicializa el panel que contendrá los asientos y lo coloca dentro de un JScrollPane
     * para permitir la visualización de una gran cantidad de asientos si es necesario.
     */
    public AsientosPanel() {
        setLayout(new BorderLayout());
        asientosPanel = new JPanel();
        add(new JScrollPane(asientosPanel), BorderLayout.CENTER);
    }
    /**
     * Muestra la distribución de los asientos en un bus de acuerdo con la ruta seleccionada.
     * Los asientos se organizan en una cuadrícula con botones representando cada uno.
     * Los botones tienen un color de fondo que indica si el asiento está disponible.
     *
     * @param rutaSeleccionada La ruta seleccionada que contiene el bus y sus asientos.
     */
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
    /**
     * Muestra los detalles del asiento seleccionado en un cuadro de diálogo.
     * Los detalles incluyen el ID del asiento, la categoría del asiento, el precio del asiento,
     * el precio de la ruta, y el precio total (suma del precio del asiento y la ruta).
     *
     * @param asiento El asiento para el cual se deben mostrar los detalles.
     * @param rutaSeleccionada La ruta seleccionada para calcular los precios.
     */
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
