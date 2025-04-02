package entrega2;

public class Pila<E> extends AgregadoLineal<E> {
    @Override
    public void add(E e) {
        elementos.add(0, e);
    }

    public E top() {
        return isEmpty() ? null : elementos.get(0);
    }

    public E remove() {
        return isEmpty() ? null : elementos.remove(0);
    }
}
