package bloque5.examen;

public record Compra(Cliente cliente, String descripcion, double importe) {
    public static Compra of(Cliente cliente, String descripcion, double importe) {
        return new Compra(cliente, descripcion, importe);
    }

    @Override
    public String toString() {
        return "Compra [Nombre de cliente= " + cliente.nombre() +
               ", descripción= " + descripcion +
               ", importe= " + String.format("%.2f", importe) + " €]";
    }
}