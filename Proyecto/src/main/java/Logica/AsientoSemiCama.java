package Logica;

class AsientoSemiCama extends Asiento {
    public AsientoSemiCama(String identificador) {
        super(identificador, TipoAsiento.SEMI_CAMA);
    }
    @Override
    public AsientoSemiCama clone() {
        return (AsientoSemiCama) super.clone();
    }
}