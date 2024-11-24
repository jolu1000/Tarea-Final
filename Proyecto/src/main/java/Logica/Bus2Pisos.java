package Logica;

public class Bus2Pisos extends Bus {

    public Bus2Pisos(int capacidad, int estandar, int semiCama, int cama, int premium) {
        super(capacidad);
        inicializarAsientos(estandar, semiCama, cama, premium);
    }

}

