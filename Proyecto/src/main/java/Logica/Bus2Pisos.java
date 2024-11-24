package Logica;

public class Bus2Pisos extends Bus {
    private int asientosPrimerPiso;
    private int asientosSegundoPiso;

    public Bus2Pisos(String patente, int capacidad, int estandar, int semiCama, int cama, int premium, int asientosPrimerPiso, int asientosSegundoPiso) {
        super(capacidad);
        this.asientosPrimerPiso = asientosPrimerPiso;
        this.asientosSegundoPiso = asientosSegundoPiso;
        inicializarAsientos(estandar, semiCama, cama, premium);
    }
}
