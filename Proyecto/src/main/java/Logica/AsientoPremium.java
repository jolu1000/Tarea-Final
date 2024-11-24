package Logica;

class AsientoPremium extends Asiento {
    public AsientoPremium(String identificador) {
        super(identificador, TipoAsiento.PREMIUM);
    }
}