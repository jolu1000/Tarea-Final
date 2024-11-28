package Logica;

public enum Ciudades {
    CONCEPCION("Concepción"),
    LOS_ANGELES("Los Ángeles"),
    CHILLAN("Chillán"),
    SANTIAGO("Santiago");

    private String nombre;

    Ciudades(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
