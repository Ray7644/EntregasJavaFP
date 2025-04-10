package bloque5.examen;

import entrega2.Cola;

import java.util.List;
import java.util.stream.Collectors;

public class ColaComprasPendientes extends Cola<Compra> {

    public Compra buscarCompraPorDescripcion(String desc) {
        for (Compra compra : elements()) {
            if (compra.descripcion().toLowerCase().contains(desc.toLowerCase())) {
                return compra;
            }
        }
        return null;
    }

    public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto) {
        return elements().stream()
                .filter(c -> c.cliente().equals(cliente) &&
                             c.descripcion().toLowerCase().contains(producto.toLowerCase()))
                .collect(Collectors.toList());
    }
}