package bloque5.examen;

import entrega2.Pila;

import java.util.List;
import java.util.stream.Collectors;

public class HistorialCompras extends Pila<Compra> {

    public double totalGastadoPor(Cliente cliente) {
        return elements().stream()
                .filter(c -> c.cliente().equals(cliente))
                .mapToDouble(Compra::importe)
                .sum();
    }

    public List<Compra> comprasMayoresA(double cantidad) {
        return elements().stream()
                .filter(c -> c.importe() > cantidad)
                .collect(Collectors.toList());
    }
}