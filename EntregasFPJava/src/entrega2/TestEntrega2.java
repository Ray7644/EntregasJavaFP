package entrega2;

import java.util.Arrays;
import java.util.List;

public class TestEntrega2 {
    public static void main(String[] args) {
        System.out.println("===== INICIANDO PRUEBAS DE ESTRUCTURAS LINEALES =====\n");
        
        System.out.println("----- Prueba de ListaOrdenada -----\n");
        ListaOrdenada<Integer> lista = ListaOrdenada.of(Integer::compareTo);
        List<Integer> valores = Arrays.asList(5, 2, 8, 1, 3);
        System.out.println("Añadiendo elementos: " + valores);
        lista.addAll(valores);
        System.out.println("\nElementos en la lista: " + lista.elements());
        System.out.println("\nTamaño de la lista: " + lista.size());
        System.out.println("\nEliminando el primer elemento: " + lista.remove());
        System.out.println("\nElementos después de eliminar: " + lista.elements());
        lista.addAll(Arrays.asList(4, 6, 7));
        System.out.println("\nAñadiendo elementos en lote: 4, 6, 7");
        System.out.println("\nElementos después de añadir lote: " + lista.elements());
        System.out.println("\nEliminando todos los elementos: " + lista.removeAll());
        System.out.println("\n¿Está vacía? " + lista.isEmpty());
        
        ListaOrdenada<String> listaStr = ListaOrdenada.of(String::compareTo);
        listaStr.addAll(Arrays.asList("banana", "apple", "cherry", "date"));
        System.out.println("\nPrueba con strings:");
        System.out.println("\nElementos ordenados: " + listaStr.elements() + "\n");
        
        System.out.println("----- Prueba de ListaOrdenadaSinRepeticion -----\n");
        ListaOrdenadaSinRepeticion<Integer> listaUnica = ListaOrdenadaSinRepeticion.of(Integer::compareTo);
        listaUnica.addAll(Arrays.asList(5, 2, 8, 1, 3, 5, 2));
        System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3, 5, 2");
        System.out.println("\nElementos en la lista: " + listaUnica.elements());
        System.out.println("\nTamaño de la lista: " + listaUnica.size());
        System.out.println("\nEliminando el primer elemento: " + listaUnica.remove());
        System.out.println("\nElementos después de eliminar: " + listaUnica.elements());
        listaUnica.addAll(Arrays.asList(4, 6, 7, 4));
        System.out.println("\nAñadiendo elementos en lote: 4, 6, 7, 4");
        System.out.println("\nElementos después de añadir lote: " + listaUnica.elements());
        
        System.out.println("\n----- Prueba de Cola (FIFO) -----\n");
        Cola<String> cola = Cola.of();
        cola.add("primero"); cola.add("segundo"); cola.add("tercero");
        System.out.println("Añadiendo elementos: 'primero', 'segundo', 'tercero'");
        System.out.println("\nElementos en la cola: " + cola.elements());
        System.out.println("\nTamaño de la cola: " + cola.size());
        System.out.println("\nDesencolando elementos: ");
        while (!cola.isEmpty()) {
            System.out.println("Desencolado: " + cola.remove());
            System.out.println("Cola restante: " + cola.elements());
        }
        System.out.println("\n¿Está vacía? " + cola.isEmpty());
        
        System.out.println("\n----- Prueba de Pila (LIFO) -----\n");
        Pila<Double> pila = new Pila<>();
        pila.add(1.1); pila.add(2.2); pila.add(3.3);
        System.out.println("Añadiendo elementos: 1.1, 2.2, 3.3");
        System.out.println("\nElementos en la pila: " + pila.elements());
        System.out.println("\nTamaño de la pila: " + pila.size());
        System.out.println("\nElemento en el tope: " + pila.top());
        System.out.println("\nDesapilando elementos: ");
        while (!pila.isEmpty()) {
            System.out.println("Desapilado: " + pila.remove());
            System.out.println("Pila restante: " + pila.elements());
        }
        System.out.println("\n¿Está vacía? " + pila.isEmpty());
        
        System.out.println("\n----- Prueba de ColaPrioridad -----\n");
        ColaPrioridad<String, Integer> colaPrioridad = ColaPrioridad.ofPriority();
        colaPrioridad.add("Crítico", 1);
        colaPrioridad.add("Normal", 3);
        colaPrioridad.add("Urgente", 2);
        colaPrioridad.add("Bajo", 4);
        System.out.println("Añadiendo elementos con prioridad: \n'Crítico' con prioridad 1 \n'Normal' con prioridad 3 \n'Urgente' con prioridad 2 \n'Bajo' con prioridad 4");
        System.out.println("\nElementos en la cola por prioridad: " + colaPrioridad.valuesAsList());
        System.out.println("\nTamaño de la cola: " + colaPrioridad.size());
        colaPrioridad.decreasePriority("Normal", 1);
        System.out.println("\nCambiando la prioridad de 'Normal' de 3 a 1:");
        System.out.println("\nElementos con prioridad actualizada: " + colaPrioridad.valuesAsList());
        System.out.println("\nDesencolando elementos por prioridad: ");
        while (!colaPrioridad.isEmpty()) {
            System.out.println("Desencolado: " + colaPrioridad.removeValue());
            System.out.println("Cola restante: " + colaPrioridad.valuesAsList());
        }
        System.out.println("\n¿Está vacía? " + colaPrioridad.isEmpty());
        
        System.out.println("\nPrueba con addAll:");
        colaPrioridad.addAllValues(Arrays.asList("Tarea A", "Tarea B", "Tarea C"), 2);
        System.out.println("\nElementos añadidos en lote con prioridad 2: " + colaPrioridad.valuesAsList());
        colaPrioridad.add("Tarea Urgente", 1);
        System.out.println("\nDespués de añadir 'Tarea Urgente' con prioridad 1: " + colaPrioridad.valuesAsList());
        
        System.out.println("\n===== TODAS LAS PRUEBAS COMPLETADAS =====");
    }
}
