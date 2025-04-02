package entrega2;

import java.util.ArrayList;
import java.util.List;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {
    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
        return new ColaPrioridad<>();
    }

    @Override
    public void add(PriorityElement<E, P> element) {
        elementos.add(element);
        elementos.sort((a, b) -> a.priority().compareTo(b.priority()));
    }

    public void add(E value, P priority) {
        add(new PriorityElement<>(value, priority));
    }

    public List<E> valuesAsList() {
        List<E> values = new ArrayList<>();
        for (PriorityElement<E, P> element : elementos) {
            values.add(element.value());
        }
        return values;
    }

    public void decreasePriority(E value, P newPriority) {
        elementos.removeIf(e -> e.value().equals(value));
        add(value, newPriority);
    }

    public E removeValue() {
        return isEmpty() ? null : remove().value();
    }

    public void addAllValues(List<E> values, P priority) {
        for (E value : values) {
            add(value, priority);
        }
    }
}
