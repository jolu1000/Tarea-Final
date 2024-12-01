package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelPrincipal extends JFrame {

    private RutaFactory rutaFactory;
    private RutaPanel panelSeleccionRuta;
    private AsientosPanel panelAsientos;
    private ClientePanel panelReservaCliente;

    public PanelPrincipal() {
        setTitle("BUSES JBG");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear autobuses y rutas
        Bus bus1 = new Bus1Piso(40, 10, 15, 10, 5);
        Bus bus2 = new Bus2Pisos(50, 20, 10, 15, 5);
        rutaFactory = new RutaFactory(List.of(bus1, bus2));
        List<Ruta> rutas = rutaFactory.crearRutas(Ciudades.CONCEPCION, Ciudades.SANTIAGO);


        // Panel de selección de ruta
        panelSeleccionRuta = new RutaPanel(rutas, this::mostrarDistribucionAsientos);
        add(panelSeleccionRuta, BorderLayout.NORTH);

        // Panel de asientos
        panelAsientos = new AsientosPanel();
        add(new JScrollPane(panelAsientos), BorderLayout.CENTER);

        // Panel de reserva del cliente
        panelReservaCliente = new ClientePanel();
        add(panelReservaCliente, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void mostrarDistribucionAsientos(ActionEvent e) {
        // Obtener la ruta seleccionada
        Ruta rutaSeleccionada = panelSeleccionRuta.getRutaSeleccionada();
        if (rutaSeleccionada != null) {
            panelAsientos.mostrarDistribucionAsientos(rutaSeleccionada);
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazDistribucionBus::new);
    }
}
