package Logica;

public enum TipoAsiento {
    ESTANDAR(0),
    SEMI_CAMA(3000),
    CAMA(7000),
    PREMIUM(10000);

    private final int precio;

    TipoAsiento(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }
}
