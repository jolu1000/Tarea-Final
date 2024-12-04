package Logica;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Clase que se encarga de la creación de rutas entre ciudades utilizando buses disponibles.
 * Las rutas se generan para diferentes fechas y horas, y se calculan los precios según el origen,
 * destino y tipo de bus.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class RutaFactory {
    private List<Bus> buses;
    /**
     * Constructor de la clase RutaFactory.
     *
     * @param buses Lista de buses disponibles para crear rutas.
     */
    public RutaFactory(List<Bus> buses){
        this.buses=buses;
    }
    /**
     * Crea una lista de rutas entre dos ciudades, con buses disponibles, en diferentes fechas
     * y horas dentro de un rango predefinido.
     *
     * @param ciudadOrigen La ciudad de origen de las rutas.
     * @param ciudadDestino La ciudad de destino de las rutas.
     * @return Una lista de rutas generadas.
     * @throws IllegalArgumentException Si alguna de las ciudades es nula.
     * @throws IllegalStateException Si no hay buses disponibles.
     */
    public List<Ruta> crearRutas(Ciudades ciudadOrigen,Ciudades ciudadDestino){
        if (ciudadOrigen == null || ciudadDestino == null) {
            throw new IllegalArgumentException("Origen y destino no pueden ser nulos");
        }
        if (buses.isEmpty()) {
            throw new IllegalStateException("No hay buses disponibles para crear rutas");
        }

        List<Ruta> rutas = new ArrayList<>();
        LocalTime start = LocalTime.of(8, 0); // Hora de inicio general
        LocalTime end = LocalTime.of(22, 0); // Hora de fin
        int busIndex = 0;

        LocalDate[] fechas = {LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)};

        for (int i = 0; i < fechas.length; i++) {
            LocalDate localDate = fechas[i];
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            LocalTime currentStart = (i == 0 && LocalTime.now().isAfter(start)) ? LocalTime.now() : start;
            currentStart = currentStart.truncatedTo(ChronoUnit.MINUTES);

            LocalTime current = currentStart;
            while (!current.isAfter(end)) {

                Bus bus1Piso = buses.get(0);
                Bus bus1PisoClonado = bus1Piso.clone();
                int precio1Piso = PrecioTotal(ciudadOrigen, ciudadDestino, bus1PisoClonado);
                rutas.add(new Ruta(ciudadOrigen, ciudadDestino, date, current, precio1Piso, bus1PisoClonado));

                Bus bus2Pisos = buses.get(1);
                Bus bus2PisosClonado = bus2Pisos.clone();
                int precio2Pisos = PrecioTotal(ciudadOrigen, ciudadDestino, bus2PisosClonado);
                rutas.add(new Ruta(ciudadOrigen, ciudadDestino, date, current, precio2Pisos, bus2PisosClonado));

                current = current.plusHours(1);
            }
        }

        return rutas;
    }

    /**
     * Calcula el precio base de un viaje entre dos ciudades.
     *
     * @param origen La ciudad de origen.
     * @param destino La ciudad de destino.
     * @return El precio base del viaje.
     */
    public int crearPrecioViaje(Ciudades origen, Ciudades destino){
        if (origen == Ciudades.LOS_ANGELES && destino == Ciudades.CONCEPCION || origen== Ciudades.CONCEPCION && destino== Ciudades.LOS_ANGELES) {
            return 4000;
        } else if (origen == Ciudades.LOS_ANGELES && destino == Ciudades.CHILLAN || origen== Ciudades.CHILLAN && destino== Ciudades.LOS_ANGELES ) {
            return 4000;
        }else if (origen == Ciudades.CHILLAN && destino == Ciudades.CONCEPCION || origen== Ciudades.CONCEPCION && destino== Ciudades.CHILLAN ) {
            return 3000;
        }else if (origen == Ciudades.SANTIAGO && destino == Ciudades.CONCEPCION || origen== Ciudades.CONCEPCION && destino== Ciudades.SANTIAGO ) {
            return 15000;
        }else if (origen == Ciudades.SANTIAGO && destino == Ciudades.LOS_ANGELES|| origen== Ciudades.LOS_ANGELES && destino== Ciudades.SANTIAGO ) {
            return 15000;
        }else if (origen == Ciudades.SANTIAGO && destino == Ciudades.CHILLAN ||origen== Ciudades.CHILLAN && destino== Ciudades.SANTIAGO ) {
            return 13000;
        }else return 0;

    }
    /**
     * Calcula el precio adicional de un asiento basado en su tipo.
     *
     * @param asiento El tipo de asiento (Estandar, Semi Cama, Cama, Premium).
     * @return El precio adicional correspondiente al tipo de asiento.
     */
    public int precioAsiento(TipoAsiento asiento){
        if(asiento== TipoAsiento.ESTANDAR){
            return 0;
        }else if(asiento==TipoAsiento.SEMI_CAMA){
            return 3000;
        }else if(asiento==TipoAsiento.CAMA){
            return 7000;
        }else if(asiento==TipoAsiento.PREMIUM){
            return 10000;
        }else return 0;
    }
    /**
     * Calcula el precio total de un viaje, considerando el precio base del viaje
     * y el precio de los asientos en el bus.
     *
     * @param origen La ciudad de origen.
     * @param destino La ciudad de destino.
     * @param bus El bus que realiza el viaje.
     * @return El precio total del viaje.
     */
    public int PrecioTotal(Ciudades origen, Ciudades destino, Bus bus) {
        int precioBase = crearPrecioViaje(origen, destino);
        int precioTotal = precioBase;
        for (Asiento asiento : bus.getAsientos()) {
            precioTotal += precioAsiento(asiento.getTipoAsiento())/10;
        }
        return precioTotal;
    }
}
