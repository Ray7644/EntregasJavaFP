package bloque5.examen;

import java.util.*;
import java.util.stream.Collectors;

public class EstadisticasClientes {

    public static Map<Cliente, List<Compra>> agruparComprasPorClienteFuncional(List<Compra> compras) {
        return compras.stream()
                .collect(Collectors.groupingBy(Compra::cliente));
    }

    public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativa(List<Compra> compras) {
        Map<Cliente, List<Compra>> mapa = new HashMap<>();
        for (Compra compra : compras) {
            Cliente cliente = compra.cliente();
            if (!mapa.containsKey(cliente)) {
                mapa.put(cliente, new ArrayList<>());
            }
            mapa.get(cliente).add(compra);
        }
        return mapa;
    }
}