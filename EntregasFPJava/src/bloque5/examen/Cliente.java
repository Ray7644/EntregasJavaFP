package bloque5.examen;

public record Cliente(String nombre, int antigüedad) {
    public static Cliente of(String nombre, int antigüedad) {
        return new Cliente(nombre, antigüedad);
    }
}
