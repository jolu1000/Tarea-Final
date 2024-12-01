package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class RutaPanel extends JPanel {

    private JComboBox<Ruta> rutasComboBox;

    public RutaPanel(List<Ruta> rutas, ActionListener actionListener) {
        setLayout(new FlowLayout());

        // Crear JComboBox con las rutas disponibles
        rutasComboBox = new JComboBox<>(rutas.toArray(new Ruta[0]));

        // Crear el botón para ver los asientos
        JButton verAsientosButton = new JButton("Ver Asientos");
        verAsientosButton.addActionListener(actionListener);

        // Añadir componentes al panel
        add(new JLabel("Seleccionar Ruta:"));
        add(rutasComboBox);
        add(verAsientosButton);
    }

    // Método para obtener la ruta seleccionada
    public Ruta getRutaSeleccionada() {
        return (Ruta) rutasComboBox.getSelectedItem();
    }
    public void agregarRuta(Ruta nuevaRuta) {
        rutasComboBox.addItem(nuevaRuta);
    }
}


