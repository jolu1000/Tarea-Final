package Logica;

public abstract class Asiento implements Cloneable {
    protected String Id;
    protected boolean disponible = true;
    private TipoAsiento tipoAsiento;
    private Cliente cliente;

    public Asiento(String Id, TipoAsiento tipoAsiento) {
        this.Id = Id;
        this.tipoAsiento = tipoAsiento;
        this.disponible = true;
    }

    public String getId() {
        return Id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    public void ocupar() {
        this.disponible = false;
    }

    @Override
    public Asiento clone() {
        try {
            return (Asiento) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Asiento " + Id + " (" + tipoAsiento + ") - " + (disponible ? "Disponible" : "Ocupado");
    }
}

