package Logica;

public class Bus1Piso extends Bus {
    public Bus1Piso(int capacidad, int estandar, int semiCama, int cama, int premium) {
        super(capacidad);
        inicializarAsientos(estandar, semiCama, cama, premium);
    }
    public boolean pisos(){
        return false;
    }
    @Override
    public Bus1Piso clone() {
        return (Bus1Piso) super.clone();
    }
}


