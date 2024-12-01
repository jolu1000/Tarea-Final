package Interfaz;

import Logica.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class AsientoColor {
    private static final Map<TipoAsiento, Color> coloresAsientos = new HashMap<>();

    static {
        coloresAsientos.put(TipoAsiento.ESTANDAR, Color.GREEN);
        coloresAsientos.put(TipoAsiento.SEMI_CAMA, Color.YELLOW);
        coloresAsientos.put(TipoAsiento.CAMA, Color.ORANGE);
        coloresAsientos.put(TipoAsiento.PREMIUM, Color.MAGENTA);
    }

    public static Color getColorPorTipo(TipoAsiento tipo) {
        return coloresAsientos.getOrDefault(tipo, Color.WHITE);
    }
}
