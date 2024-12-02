package Logica;

public abstract class Asiento implements Cloneable {
    protected String Id;
    protected boolean disponible = true;
    private TipoAsiento tipoAsiento;
    private Cliente cliente;

    public Asiento(String Id, TipoAsiento tipoAsiento) {
        this.Id = Id;
        this.tipoAsiento = tipoAsiento;
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
    public Cliente getCliente(){
        return cliente;

    }

    public int getPrecio() {
        return tipoAsiento.getPrecio();
    }

    public void setCliente(Cliente cliente) {
        if (this.disponible) {
            this.cliente = cliente;
            this.ocupar();
        } else {
            throw new IllegalStateException("Este asiento ya está ocupado.");
        }
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

