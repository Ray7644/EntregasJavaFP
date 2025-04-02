package entrega2;

public class Cola<E> extends AgregadoLineal<E> {
    public static <E> Cola<E> of() {
        return new Cola<>();
    }

    @Override
    public void add(E e) {
        elementos.add(e);
    }

    public E remove() {
        return isEmpty() ? null : elementos.remove(0);
    }
}
