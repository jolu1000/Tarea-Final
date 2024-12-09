package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private Asiento asientoSeleccionado;
    private PanelPrincipal panelPrincipal;

    /**
     * Constructor de la clase AsientosPanel.
     * Inicializa el panel que contendrá los asientos y lo coloca dentro de un JScrollPane
     * para permitir la visualización de una gran cantidad de asientos si es necesario.
     */
    public AsientosPanel(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        setLayout(new BorderLayout());
        asientosPanel = new JPanel();
        add(new JScrollPane(asientosPanel), BorderLayout.CENTER);

        // Añadir leyenda
        JPanel leyendaPanel = crearLeyenda();
        add(leyendaPanel, BorderLayout.NORTH);
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

            // Manejar clic izquierdo (selección de asiento) y clic derecho (info del asiento)
            asientoButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        // Si el asiento no está disponible, no permitir la selección
                        if (!asiento.isDisponible()) {
                            JOptionPane.showMessageDialog(AsientosPanel.this,
                                    "Este asiento ya está reservado. No se puede seleccionar.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Seleccionar el asiento
                        asientoSeleccionado = asiento;
                        panelPrincipal.getPanelReservaCliente().setAsientoSeleccionado(asiento);
                        JOptionPane.showMessageDialog(AsientosPanel.this,
                                "Asiento seleccionado: " + asiento.getId(),
                                "Selección de Asiento", JOptionPane.INFORMATION_MESSAGE);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        // Clic derecho, mostrar detalles del asiento reservado (si está reservado)
                        mostrarDetallesAsiento(asiento, rutaSeleccionada);
                    }
                }
            });

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
        if (!asiento.isDisponible()) {
            // El asiento está reservado, mostrar los detalles del cliente
            Cliente clienteReservado = asiento.getCliente();  // Obtener el cliente del asiento
            String detalles = "Asiento reservado por:\n" +
                    "Nombre: " + clienteReservado.getNombre() + "\n" +
                    "Apellido: " + clienteReservado.getApellido() + "\n" +
                    "RUT: " + clienteReservado.getRut() + "\n" +
                    "Email: " + clienteReservado.getEmail();
            JOptionPane.showMessageDialog(this,
                    detalles,
                    "Detalles del Asiento Reservado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Si el asiento está disponible, mostrar detalles normales
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

    /**
     * Crea un panel de leyenda para mostrar el significado de los colores asociados a los asientos.
     *
     * @return JPanel que contiene la leyenda de colores.
     */
    private JPanel crearLeyenda() {
        JPanel leyendaPanel = new JPanel();
        leyendaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Agregar los elementos de la leyenda
        leyendaPanel.add(crearItemLeyenda("Estandar", AsientoColor.getColorPorTipo(TipoAsiento.ESTANDAR)));
        leyendaPanel.add(crearItemLeyenda("Semi Cama", AsientoColor.getColorPorTipo(TipoAsiento.SEMI_CAMA)));
        leyendaPanel.add(crearItemLeyenda("Cama", AsientoColor.getColorPorTipo(TipoAsiento.CAMA)));
        leyendaPanel.add(crearItemLeyenda("Premium", AsientoColor.getColorPorTipo(TipoAsiento.PREMIUM)));
        leyendaPanel.add(crearItemLeyenda("Ocupado", Color.RED));

        return leyendaPanel;
    }

    /**
     * Crea un elemento individual de la leyenda, que incluye un cuadro de color y una etiqueta de texto.
     *
     * @param texto Descripción del tipo de asiento.
     * @param color Color representativo del tipo de asiento.
     * @return JPanel que representa un elemento de la leyenda.
     */
    private JPanel crearItemLeyenda(String texto, Color color) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Crear un cuadro de color
        JLabel colorLabel = new JLabel(" ");
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);
        colorLabel.setPreferredSize(new Dimension(20, 20));

        // Añadir el texto descriptivo
        JLabel textoLabel = new JLabel(texto);

        // Añadir componentes al panel
        itemPanel.add(colorLabel);
        itemPanel.add(textoLabel);

        return itemPanel;
    }
}
