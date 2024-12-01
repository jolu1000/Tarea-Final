package Logica;

public class AsientoEstandar extends Asiento {
    public AsientoEstandar(String identificador) {
        super(identificador, TipoAsiento.ESTANDAR);
    }

    @Override
    public AsientoEstandar clone() {
        return (AsientoEstandar) super.clone();
    }
}