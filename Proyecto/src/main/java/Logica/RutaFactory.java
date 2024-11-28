package Logica;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
public class RutaFactory {
    private List<Bus> buses;
    public RutaFactory(List<Bus> buses){
        this.buses=buses;
    }
    int crearRutas(){

    }
    int crearPrecioViaje(Ciudades origen, Ciudades destino){
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
    int precioAsiento(TipoAsiento asiento){
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
            precioTotal += precioAsiento(asiento.getTipoAsiento());
        }
        return precioTotal;
    }
}
