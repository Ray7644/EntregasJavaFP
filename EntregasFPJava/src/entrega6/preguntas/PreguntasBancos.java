package entrega6.preguntas;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import us.lsi.bancos.*;
import us.lsi.ejemplos_b1_tipos.Persona;
import us.lsi.tools.Preconditions;

public class PreguntasBancos {

    private Banco banco;

    public PreguntasBancos() {
        this.banco = Banco.of();
    }

    public Map<String, Double> valorTotalPrestamosImperativo(int edadLimite, double a, double b, LocalDate f) {
        Preconditions.checkArgument(edadLimite > 18, "La edad debe ser mayor que 18");
        Preconditions.checkArgument(a > 0 && b > 0 && a < b, "Valores de a y b inválidos");

        Map<String, Double> resultado = new HashMap<>();

        for (Prestamo p : banco.prestamos().todos()) {
            Optional<Persona> personaOpt = banco.personas().personaDni(p.dniCliente());
            if (personaOpt.isEmpty()) continue;
            Persona persona = personaOpt.get();

            int edad = Period.between(persona.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
            if (edad < edadLimite && p.fechaComienzo().isAfter(f) && p.cantidad() >= a && p.cantidad() <= b) {
                resultado.put(persona.dni(), resultado.getOrDefault(persona.dni(), 0.0) + p.cantidad());
            }
        }

        return resultado;
    }

    public Map<String, Double> valorTotalPrestamosFuncional(int edadLimite, double a, double b, LocalDate f) {
        Preconditions.checkArgument(edadLimite > 18, "La edad debe ser mayor que 18");
        Preconditions.checkArgument(a > 0 && b > 0 && a < b, "Valores de a y b inválidos");

        return banco.prestamos().todos().stream()
                .filter(p -> banco.personas().personaDni(p.dniCliente()).isPresent())
                .filter(p -> {
                    Persona persona = banco.personas().personaDni(p.dniCliente()).get();
                    int edad = Period.between(persona.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                    return edad < edadLimite && p.fechaComienzo().isAfter(f) && p.cantidad() >= a && p.cantidad() <= b;
                })
                .collect(Collectors.groupingBy(
                        Prestamo::dniCliente,
                        Collectors.summingDouble(Prestamo::cantidad)
                ));
    }
}
