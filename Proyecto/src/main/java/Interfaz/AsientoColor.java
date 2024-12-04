package Interfaz;
import Logica.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
/**
 * La clase AsientoColor proporciona un mecanismo para obtener el color asociado con un tipo específico de asiento.
 * Esta clase estática mapea los tipos de asiento a colores específicos para su visualización en la interfaz gráfica.
 *
 * @author Gabriela
 * @author Joaquin
 * @author Benjamin
 */
public class AsientoColor {
    private static final Map<TipoAsiento, Color> coloresAsientos = new HashMap<>();

    static {
        coloresAsientos.put(TipoAsiento.ESTANDAR, Color.GREEN);
        coloresAsientos.put(TipoAsiento.SEMI_CAMA, Color.YELLOW);
        coloresAsientos.put(TipoAsiento.CAMA, Color.ORANGE);
        coloresAsientos.put(TipoAsiento.PREMIUM, Color.MAGENTA);
    }
    /**
     * Getter que devuelve el color asociado con el tipo de asiento proporcionado.
     * Si el tipo de asiento no tiene un color asociado, se retorna el color blanco por defecto.
     *
     * @param tipo El tipo de asiento para el cual se desea obtener el color.
     * @return El color correspondiente al tipo de asiento, o blanco si no se encuentra en el mapa.
     */

    public static Color getColorPorTipo(TipoAsiento tipo) {
        return coloresAsientos.getOrDefault(tipo, Color.WHITE);
    }
}
