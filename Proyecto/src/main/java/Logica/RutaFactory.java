package Logica;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RutaFactory {
    private List<Bus> buses;
    public RutaFactory(List<Bus> buses){
        this.buses=buses;
    }
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
    public int PrecioTotal(Ciudades origen, Ciudades destino, Bus bus) {
        int precioBase = crearPrecioViaje(origen, destino);
        int precioTotal = precioBase;
        for (Asiento asiento : bus.getAsientos()) {
            precioTotal += precioAsiento(asiento.getTipoAsiento())/10;
        }
        return precioTotal;
    }
}
