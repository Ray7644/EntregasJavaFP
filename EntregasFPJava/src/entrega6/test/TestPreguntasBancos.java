package entrega6.test;

import java.time.LocalDate;
import java.util.Map;

import entrega6.preguntas.PreguntasBancos;

public class TestPreguntasBancos {

    public static void main(String[] args) {
        PreguntasBancos pb = new PreguntasBancos();

        System.out.println("--- Test valorTotalPrestamosImperativo ---");
        Map<String, Double> r1 = pb.valorTotalPrestamosImperativo(40, 1000.0, 50000.0, LocalDate.of(2015, 1, 1));
        r1.forEach((dni, total) -> System.out.printf("%s -> %.2f\n", dni, total));

        System.out.println("\n--- Test valorTotalPrestamosFuncional ---");
        Map<String, Double> r2 = pb.valorTotalPrestamosFuncional(40, 1000.0, 50000.0, LocalDate.of(2015, 1, 1));
        r2.forEach((dni, total) -> System.out.printf("%s -> %.2f\n", dni, total));
    }
}
