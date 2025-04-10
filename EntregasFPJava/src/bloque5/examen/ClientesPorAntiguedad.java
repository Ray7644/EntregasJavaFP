package bloque5.examen;

import entrega2.ListaOrdenada;

import java.util.Comparator;
import java.util.List;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {
    public ClientesPorAntiguedad() {
        super(Comparator.comparingInt(Cliente::antigüedad).reversed());
    }

    public List<Cliente> topClientes(int n) {
        return elements().subList(0, Math.min(n, size()));
    }
}