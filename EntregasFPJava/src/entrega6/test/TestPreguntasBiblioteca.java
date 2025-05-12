package entrega6.test;

import entrega6.preguntas.PreguntasBiblioteca;
import us.lsi.biblioteca.Libros;

import java.util.*;

public class TestPreguntasBiblioteca {

    public static void main(String[] args) {
        PreguntasBiblioteca pb = new PreguntasBiblioteca();
        Libros libros = Libros.of();

        System.out.println("--- masVecesPrestado ---");
        var r1 = pb.masVecesPrestadoImperativo();
        System.out.printf("Imperativo: %s -> %d\n", r1.getKey(), r1.getValue());
        var r2 = pb.masVecesPrestadoFuncional();
        System.out.printf("Funcional : %s -> %d\n", r2.getKey(), r2.getValue());

        System.out.println("\n--- librosPorAutor ---");
        Map<String, Set<String>> m1 = pb.librosPorAutorImperativo(libros, null);
        Map<String, Set<String>> m2 = pb.librosPorAutorFuncional(libros, null);

        System.out.println("Imperativo:");
        m1.forEach((autor, titulos) -> System.out.println(autor + " -> " + titulos));

        System.out.println("\nFuncional:");
        m2.forEach((autor, titulos) -> System.out.println(autor + " -> " + titulos));
    }
}

