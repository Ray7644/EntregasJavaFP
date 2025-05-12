package entrega6.preguntas;

import java.util.*;
import java.util.stream.Collectors;

import us.lsi.biblioteca.*;

public class PreguntasBiblioteca {

    private Biblioteca biblioteca;

    public PreguntasBiblioteca() {
        this.biblioteca = Biblioteca.of("Biblioteca Central", "41092", "biblioteca@email.com");
    }

    // a. masVecesPrestado
    public Map.Entry<String, Long> masVecesPrestadoImperativo() {
        Map<String, Long> conteo = new HashMap<>();
        for (Prestamo p : biblioteca.prestamos().todos()) {
            String isbn = p.isbn();
            conteo.put(isbn, conteo.getOrDefault(isbn, 0L) + 1);
        }
        Map.Entry<String, Long> max = null;
        for (Map.Entry<String, Long> e : conteo.entrySet()) {
            if (max == null || e.getValue() > max.getValue()) {
                max = e;
            }
        }
        return max;
    }

    public Map.Entry<String, Long> masVecesPrestadoFuncional() {
        return biblioteca.prestamos().todos().stream()
                .collect(Collectors.groupingBy(Prestamo::isbn, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }

    // b. librosPorAutor
    public Map<String, Set<String>> librosPorAutorImperativo(Libros libros, List<String> nombres) {
        Map<String, Set<String>> resultado = new HashMap<>();
        for (Libro l : libros.todos()) {
            if (nombres == null || nombres.contains(l.autor())) {
                resultado.putIfAbsent(l.autor(), new HashSet<>());
                resultado.get(l.autor()).add(l.titulo());
            }
        }
        return resultado;
    }

    public Map<String, Set<String>> librosPorAutorFuncional(Libros libros, List<String> nombres) {
        return libros.todos().stream()
                .filter(l -> nombres == null || nombres.contains(l.autor()))
                .collect(Collectors.groupingBy(
                        Libro::autor,
                        Collectors.mapping(Libro::titulo, Collectors.toSet())
                ));
    }
}

