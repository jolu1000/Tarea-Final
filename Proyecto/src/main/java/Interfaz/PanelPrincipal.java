package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelPrincipal extends JPanel {

    private RutaFactory rutaFactory;
    private RutaPanel panelSeleccionRuta;
    private AsientosPanel panelAsientos;
    private ClientePanel panelReservaCliente;
    private Cliente clienteActual;

    public PanelPrincipal() {
        setLayout(new BorderLayout());

        Bus bus1 = new Bus1Piso(35, 13, 12, 10, 5);
        Bus bus2 = new Bus2Pisos(50, 20, 15, 10, 5);
        rutaFactory = new RutaFactory(List.of(bus1, bus2));
        List<Ruta> rutas = rutaFactory.crearRutas(Ciudades.CONCEPCION, Ciudades.SANTIAGO);
        rutas.addAll(rutaFactory.crearRutas(Ciudades.CONCEPCION, Ciudades.SANTIAGO));
        rutas.addAll(rutaFactory.crearRutas(Ciudades.SANTIAGO, Ciudades.LOS_ANGELES));
        rutas.addAll(rutaFactory.crearRutas(Ciudades.CHILLAN, Ciudades.LOS_ANGELES));
        rutas.addAll(rutaFactory.crearRutas(Ciudades.CHILLAN, Ciudades.CONCEPCION));

        panelSeleccionRuta = new RutaPanel(rutas, this::mostrarDistribucionAsientos);
        add(panelSeleccionRuta, BorderLayout.NORTH);

        panelAsientos = new AsientosPanel();
        add(new JScrollPane(panelAsientos), BorderLayout.CENTER);

        panelReservaCliente = new ClientePanel(this);
        add(panelReservaCliente, BorderLayout.SOUTH);
    }

    public RutaPanel getPanelSeleccionRuta() {
        return panelSeleccionRuta;
    }

    public AsientosPanel getAsientosPanel() {
        return panelAsientos;
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Cliente cliente) {
        this.clienteActual = cliente;
    }

    public void limpiarClienteActual() {
        this.clienteActual = null;
    }

    private void mostrarDistribucionAsientos(ActionEvent e) {
        Ruta rutaSeleccionada = panelSeleccionRuta.getRutaSeleccionada();
        if (rutaSeleccionada != null) {
            panelAsientos.mostrarDistribucionAsientos(rutaSeleccionada);
        }
    }
}

