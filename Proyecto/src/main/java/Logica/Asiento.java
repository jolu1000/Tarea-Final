package Logica;

public abstract class Asiento implements Cloneable {
    protected String Id;
    protected boolean disponible = true;
    protected int precio;

    public Asiento(String Id , int precio) {
        this.Id = Id;
        this.precio = precio;
    }

    public String getIdentificador() {
        return Id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void ocupar() {
        this.disponible = false;
    }
    public double getPrecio() {
        return precio;
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
        return Id + (disponible ? " (Disponible)" : " (Ocupado)") + " - Precio: " + precio;
    }
}

