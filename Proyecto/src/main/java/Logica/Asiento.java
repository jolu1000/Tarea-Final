package Logica;

public abstract class Asiento implements Cloneable {
    private String Id;
    private boolean ocupado;
    private double precio;

    public Asiento(String Id, double precio) {
        this.Id = Id;
        this.precio = precio;
        this.ocupado = false;
    }

    public String getId() {
        return Id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public Asiento clone() {
        try {
            return (Asiento) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar el asiento", e);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + Id + " (Precio: $" + precio + ")" + (ocupado ? " (Ocupado)" : " (Disponible)");
    }
}
