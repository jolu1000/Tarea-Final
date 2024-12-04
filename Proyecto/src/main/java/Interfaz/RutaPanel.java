package Interfaz;
import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Panel para la selección de rutas en la aplicación de reserva de asientos de buses.
 * Este panel permite al usuario seleccionar una ruta de un combo de rutas disponibles
 * y ver los asientos disponibles en el bus correspondiente a la ruta seleccionada.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class RutaPanel extends JPanel {

    private JComboBox<Ruta> rutasComboBox;
    /**
     * Constructor de la clase `RutaPanel`.
     *
     * Este constructor inicializa el panel con un JComboBox que contiene la lista de rutas
     * disponibles, así como un botón para mostrar la distribución de los asientos de la ruta
     * seleccionada.
     *
     * @param rutas Lista de rutas disponibles para seleccionar.
     * @param actionListener El `ActionListener` que maneja la acción de mostrar los asientos.
     */
    public RutaPanel(List<Ruta> rutas, ActionListener actionListener) {
        setLayout(new FlowLayout());

        rutasComboBox = new JComboBox<>(rutas.toArray(new Ruta[0]));

        JButton verAsientosButton = new JButton("Ver Asientos");
        verAsientosButton.addActionListener(actionListener);

        add(new JLabel("Seleccionar Ruta:"));
        add(rutasComboBox);
        add(verAsientosButton);
    }
    /**
     * Método que obtiene la ruta seleccionada en el JComboBox.
     *
     * @return La ruta seleccionada por el usuario.
     */
    public Ruta getRutaSeleccionada() {
        return (Ruta) rutasComboBox.getSelectedItem();
    }
    /**
     * Método que agrega una nueva ruta al JComboBox de rutas.
     *
     * @param nuevaRuta La nueva ruta que se desea agregar.
     */
    public void agregarRuta(Ruta nuevaRuta) {
        rutasComboBox.addItem(nuevaRuta);
    }
}


