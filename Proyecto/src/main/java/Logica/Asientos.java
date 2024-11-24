package Logica;

import java.util.ArrayList;

public class Asientos implements Cloneable {
    private ArrayList<Asiento> listaAsientos;

    public Asientos() {
        this.listaAsientos = new ArrayList<>();
    }

    public void agregarAsiento(Asiento asiento) {
        listaAsientos.add(asiento);
    }

    public ArrayList<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void mostrarAsientos() {
        for (Asiento asiento : listaAsientos) {
            System.out.println(asiento);
        }
    }

    @Override
    public Asientos clone() {
        try {
            Asientos clon = (Asientos) super.clone();
            clon.listaAsientos = new ArrayList<>();
            for (Asiento asiento : listaAsientos) {
                clon.listaAsientos.add(asiento.clone());
            }
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar los asientos", e);
        }
    }
}
