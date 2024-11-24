package Logica;

public enum TipoAsiento {
    ESTANDAR(10000),
    SEMI_CAMA(15000),
    CAMA(22000),
    PREMIUM(30000);

    private final int precio;

    TipoAsiento(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }
}
