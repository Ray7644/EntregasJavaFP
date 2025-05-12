package entrega6.preguntas;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import us.lsi.aeropuerto.*;

public class PreguntasAeropuertos {

    private EspacioAereo espacio;

    public PreguntasAeropuertos() {
        this.espacio = EspacioAereo.of();
    }

    public String ciudadAeropuertoMayorFacturacionImperativo(LocalDateTime a, LocalDateTime b) {
        validarFechas(a, b);

        Map<String, Double> facturacionPorCiudad = new HashMap<>();

        for (OcupacionVuelo ocupacion : espacio.ocupacionesVuelos().todas()) {
            LocalDateTime fecha = ocupacion.fecha();
            if (!fecha.isBefore(a) && !fecha.isAfter(b)) {
                String codigoVuelo = ocupacion.codigoVuelo();
                Vuelo vuelo = espacio.vuelos().vuelo(codigoVuelo).orElse(null);
                if (vuelo != null) {
                    String ciudad = vuelo.ciudadOrigen();
                    double precio = vuelo.precio();
                    facturacionPorCiudad.put(ciudad,
                        facturacionPorCiudad.getOrDefault(ciudad, 0.0) + precio);
                }
            }
        }

        String maxCiudad = null;
        double maxFacturacion = -1;
        for (Map.Entry<String, Double> entry : facturacionPorCiudad.entrySet()) {
            if (entry.getValue() > maxFacturacion) {
                maxCiudad = entry.getKey();
                maxFacturacion = entry.getValue();
            }
        }

        return maxCiudad;
    }

    public String ciudadAeropuertoMayorFacturacionFuncional(LocalDateTime a, LocalDateTime b) {
        validarFechas(a, b);

        return espacio.ocupacionesVuelos().todas().stream()
                .filter(oc -> !oc.fecha().isBefore(a) && !oc.fecha().isAfter(b))
                .map(oc -> {
                    Vuelo vuelo = espacio.vuelos().vuelo(oc.codigoVuelo()).orElse(null);
                    return vuelo == null ? null : Map.entry(vuelo.ciudadOrigen(), vuelo.precio());
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingDouble(Map.Entry::getValue)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private void validarFechas(LocalDateTime a, LocalDateTime b) {
        if (a == null || b == null || !a.isBefore(b))
            throw new IllegalArgumentException("La fecha 'a' debe ser anterior a 'b'");
        if (Duration.between(a, b).toDays() <= 1)
            throw new IllegalArgumentException("Debe haber más de un día de diferencia entre 'a' y 'b'");
    }
}

