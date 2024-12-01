package Logica;

public class AsientoCama extends Asiento {
    public AsientoCama(String identificador) {
        super(identificador, TipoAsiento.CAMA);
    }

    @Override
    public AsientoCama clone() {
        return (AsientoCama) super.clone();
    }
}