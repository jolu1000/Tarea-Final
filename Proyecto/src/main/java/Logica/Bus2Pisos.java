package Logica;

public class Bus2Pisos extends Bus {

    public Bus2Pisos(int capacidad, int estandar, int semiCama, int cama, int premium) {
        super(capacidad);
        inicializarAsientos(estandar, semiCama, cama, premium);
    }
    public boolean pisos(){
        return true;
    }
    @Override
    public Bus2Pisos clone() {
        return (Bus2Pisos) super.clone();
    }
}

