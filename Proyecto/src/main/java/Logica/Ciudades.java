package Logica;

public enum Ciudades {
    CONCEPCION("Concepción"),
    LOS_ANGELES("Los Ángeles"),
    CHILLAN("Chillán");

    private String nombre;

    Ciudades(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
