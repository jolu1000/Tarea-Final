package Logica;

public class Bus1Piso extends Bus {
    public Bus1Piso(String patente, int capacidad, int estandar, int semiCama, int cama, int premium) {
        super(patente, capacidad);
        inicializarAsientos(estandar, semiCama, cama, premium);
    }
}

