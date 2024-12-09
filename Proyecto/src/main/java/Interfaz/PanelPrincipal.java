package Interfaz;
import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 * Clase que representa el panel principal de la aplicación para la reserva de asientos en buses.
 *
 * Esta clase organiza y maneja los paneles correspondientes a la selección de rutas, la distribución de asientos
 * y la reserva de cliente. Utiliza la clase `RutaFactory` para crear las rutas disponibles y actualiza la vista
 * conforme el usuario interactúa con la aplicación.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class PanelPrincipal extends JPanel {
    private RutaFactory rutaFactory;
    private RutaPanel panelSeleccionRuta;
    private AsientosPanel panelAsientos;
    private ClientePanel panelReservaCliente;
    private Cliente clienteActual;
    /**
     * Constructor de la clase `PanelPrincipal`. Inicializa los paneles y la lógica de las rutas y asientos.
     *
     * En este método se crean las rutas disponibles utilizando la clase `RutaFactory`. Luego, se asignan los paneles
     * correspondientes a la selección de ruta, distribución de asientos y reserva de cliente.
     */
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

        panelAsientos = new AsientosPanel(this);
        add(new JScrollPane(panelAsientos), BorderLayout.CENTER);

        panelReservaCliente = new ClientePanel(this);
        add(panelReservaCliente, BorderLayout.SOUTH);
    }

    /**
     * Getters y Setters del panel principal
     */
    public ClientePanel getPanelReservaCliente() {
        return panelReservaCliente;  // Devuelve la instancia de ClientePanel
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
    /**
     * Método que limpia el cliente actual, dejándolo como `null`.
     */
    public void limpiarClienteActual() {
        this.clienteActual = null;
    }
    /**
     * Método que maneja la acción de mostrar la distribución de asientos al seleccionar una ruta.
     *
     * @param e El evento de acción que dispara la actualización de la distribución de asientos.
     */
    private void mostrarDistribucionAsientos(ActionEvent e) {
        Ruta rutaSeleccionada = panelSeleccionRuta.getRutaSeleccionada();
        if (rutaSeleccionada != null) {
            panelAsientos.mostrarDistribucionAsientos(rutaSeleccionada);
        }
    }
}

