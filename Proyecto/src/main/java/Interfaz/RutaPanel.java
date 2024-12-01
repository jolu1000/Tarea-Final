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

        rutasComboBox = new JComboBox<>(rutas.toArray(new Ruta[0]));

        JButton verAsientosButton = new JButton("Ver Asientos");
        verAsientosButton.addActionListener(actionListener);

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


