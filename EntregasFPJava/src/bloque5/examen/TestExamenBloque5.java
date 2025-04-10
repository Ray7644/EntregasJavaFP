package bloque5.examen;

import java.util.List;
import java.util.Map;

public class TestExamenBloque5 {
    public static void main(String[] args) {
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
        Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
        Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
        Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

        System.out.println("======================================");
        System.out.println("TEST: ClientesPorAntiguedad");
        System.out.println("======================================");
        ClientesPorAntiguedad listaClientes = new ClientesPorAntiguedad();
        listaClientes.addAll(List.of(ana, juan, luis));
        System.out.println("Top 2 clientes por antigüedad:");
        listaClientes.topClientes(2).forEach(System.out::println);

        System.out.println("\n======================================");
        System.out.println("TEST: HistorialCompras");
        System.out.println("======================================");
        HistorialCompras historial = new HistorialCompras();
        historial.addAll(List.of(c1, c2, c3, c4));

        System.out.println("Total gastado por Ana: " + historial.totalGastadoPor(ana));
        System.out.println("Compras mayores a 30€:");
        historial.comprasMayoresA(30.0).forEach(System.out::println);

        System.out.println("\n======================================");
        System.out.println("TEST: ColaComprasPendientes");
        System.out.println("======================================");
        ColaComprasPendientes pendientes = new ColaComprasPendientes();
        pendientes.addAll(List.of(c1, c2, c3, c4));

        System.out.println("Primera compra que contiene 'Camiseta':");
        System.out.println(pendientes.buscarCompraPorDescripcion("Camiseta"));

        System.out.println("Compras de Ana que contienen 'agenda':");
        pendientes.filtrarPorClienteYProducto(ana, "agenda").forEach(System.out::println);

        System.out.println("\n======================================");
        System.out.println("TEST: EstadisticasClientes");
        System.out.println("======================================");
        List<Compra> compras = List.of(c1, c2, c3, c4);

        System.out.println("Agrupación funcional:");
        Map<Cliente, List<Compra>> agrupacionFuncional =
                EstadisticasClientes.agruparComprasPorClienteFuncional(compras);
        imprimirMapaCompras(agrupacionFuncional);

        System.out.println("\nAgrupación imperativa:");
        Map<Cliente, List<Compra>> agrupacionImperativa =
                EstadisticasClientes.agruparComprasPorClienteImperativa(compras);
        imprimirMapaCompras(agrupacionImperativa);
    }

    private static void imprimirMapaCompras(Map<Cliente, List<Compra>> mapa) {
        for (Map.Entry<Cliente, List<Compra>> entrada : mapa.entrySet()) {
            System.out.println("- " + entrada.getKey().nombre() + ":");
            for (Compra compra : entrada.getValue()) {
                System.out.println("    • " + compra);
            }
        }
    }
}