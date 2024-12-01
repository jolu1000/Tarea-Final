package Logica;

public class AsientoPremium extends Asiento {
    public AsientoPremium(String identificador) {
        super(identificador, TipoAsiento.PREMIUM);
    }

    @Override
    public AsientoPremium clone() {
        return (AsientoPremium) super.clone();
    }
}