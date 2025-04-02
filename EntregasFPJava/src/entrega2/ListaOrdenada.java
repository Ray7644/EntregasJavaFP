package entrega2;

import java.util.Comparator;

public class ListaOrdenada<E> extends AgregadoLineal<E> {
    private Comparator<E> comparator;

    public ListaOrdenada(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
        return new ListaOrdenada<>(comparator);
    }

    private int indexOrder(E e) {
        int i = 0;
        while (i < elementos.size() && comparator.compare(e, elementos.get(i)) > 0) {
            i++;
        }
        return i;
    }

    @Override
    public void add(E e) {
        elementos.add(indexOrder(e), e);
    }
}
