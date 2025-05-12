package entrega6.test;

import entrega6.preguntas.PreguntasAeropuertos;

import java.time.LocalDateTime;

public class TestPreguntasAeropuertos {

    public static void main(String[] args) {

        PreguntasAeropuertos preguntas = new PreguntasAeropuertos();

        LocalDateTime a = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime b = LocalDateTime.of(2020, 12, 31, 23, 59);

        System.out.println("--- Test ciudadAeropuertoMayorFacturacion ---");

        try {
            String resultadoImp = preguntas.ciudadAeropuertoMayorFacturacionImperativo(a, b);
            System.out.println("Imperativo → Ciudad con mayor facturación: " + resultadoImp);

            String resultadoFunc = preguntas.ciudadAeropuertoMayorFacturacionFuncional(a, b);
            System.out.println("Funcional → Ciudad con mayor facturación: " + resultadoFunc);

        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}

