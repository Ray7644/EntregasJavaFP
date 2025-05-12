package entrega6.preguntas;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import us.lsi.centro.*;
import us.lsi.tools.Preconditions;

public class PreguntasCentro {

    private Centro centro;

    public PreguntasCentro() {
        this.centro = Centro.of();
    }

    // a. promedioEdadProfesores
    public Double promedioEdadProfesoresImperativo(String dniAlumno) {
        List<Integer> edades = new ArrayList<>();
        for (Matricula m : centro.matriculas().todas()) {
            if (m.dni().equals(dniAlumno)) {
                for (Asignacion a : centro.asignaciones().todas()) {
                    if (a.ida().equals(m.ida()) && a.idg().equals(m.idg())) {
                        Profesor p = centro.profesores().profesor(a.dni());
                        if (p != null) {
                            int edad = Period.between(p.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                            edades.add(edad);
                        }
                    }
                }
            }
        }
        return edades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public Double promedioEdadProfesoresFuncional(String dniAlumno) {
        return centro.matriculas().todas().stream()
                .filter(m -> m.dni().equals(dniAlumno))
                .flatMap(m -> centro.asignaciones().todas().stream()
                        .filter(a -> a.ida().equals(m.ida()) && a.idg().equals(m.idg()))
                        .map(Asignacion::dni))
                .distinct()
                .map(centro.profesores()::profesor)
                .filter(Objects::nonNull)
                .mapToInt(p -> Period.between(p.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears())
                .average().orElse(0.0);
    }

    // b. grupoMayorDiversidadEdad
    public String grupoMayorDiversidadEdadImperativo() {
        Map<String, List<Integer>> grupoEdades = new HashMap<>();
        for (Matricula m : centro.matriculas().todas()) {
            Alumno a = centro.alumnos().alumno(m.dni());
            if (a != null) {
                String clave = m.ida() + "-" + m.idg();
                grupoEdades.putIfAbsent(clave, new ArrayList<>());
                int edad = Period.between(a.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                grupoEdades.get(clave).add(edad);
            }
        }
        String maxGrupo = null;
        int maxDif = -1;
        for (var e : grupoEdades.entrySet()) {
            List<Integer> edades = e.getValue();
            int dif = Collections.max(edades) - Collections.min(edades);
            if (dif > maxDif) {
                maxDif = dif;
                maxGrupo = e.getKey();
            }
        }
        return maxGrupo;
    }

    public String grupoMayorDiversidadEdadFuncional() {
        return centro.matriculas().todas().stream()
                .collect(Collectors.groupingBy(
                        m -> m.ida() + "-" + m.idg(),
                        Collectors.mapping(m -> {
                            Alumno a = centro.alumnos().alumno(m.dni());
                            return a == null ? 0 : Period.between(a.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                        }, Collectors.toList())))
                .entrySet().stream()
                .max(Comparator.comparing(e -> Collections.max(e.getValue()) - Collections.min(e.getValue())))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // c. alumnoMasMatriculas
    public String alumnoMasMatriculasImperativo() {
        Map<String, Integer> count = new HashMap<>();
        for (Matricula m : centro.matriculas().todas()) {
            count.put(m.dni(), count.getOrDefault(m.dni(), 0) + 1);
        }
        return count.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public String alumnoMasMatriculasFuncional() {
        return centro.matriculas().todas().stream()
                .collect(Collectors.groupingBy(Matricula::dni, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

 // d. rangosEdadPorAlumno
    public Map<String, String> rangosEdadPorAlumnoImperativo(String rangos) {
        Preconditions.checkArgument(!rangos.isBlank(), "Cadena de rangos vacía");
        List<int[]> listaRangos = Arrays.stream(rangos.split(","))
                .map(s -> {
                    String[] p = s.split("-");
                    return new int[]{Integer.parseInt(p[0].trim()), Integer.parseInt(p[1].trim())};
                }).toList();
        Map<String, String> res = new HashMap<>();
        for (Alumno a : centro.alumnos().todos()) {
            int edad = Period.between(a.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
            String rango = listaRangos.stream()
                    .filter(r -> edad >= r[0] && edad <= r[1])
                    .map(r -> r[0] + "-" + r[1])
                    .findFirst().orElse("Sin rango");
            res.put(a.nombre() + " (" + edad + ")", rango);
        }
        return res;
    }

    public Map<String, String> rangosEdadPorAlumnoFuncional(String rangos) {
        Preconditions.checkArgument(!rangos.isBlank(), "Cadena de rangos vacía");
        List<int[]> listaRangos = Arrays.stream(rangos.split(","))
                .map(s -> {
                    String[] p = s.split("-");
                    return new int[]{Integer.parseInt(p[0].trim()), Integer.parseInt(p[1].trim())};
                }).toList();
        return centro.alumnos().todos().stream().collect(Collectors.toMap(
                a -> {
                    int edad = Period.between(a.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                    return a.nombre() + " (" + edad + ")";
                },
                a -> {
                    int edad = Period.between(a.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                    return listaRangos.stream()
                            .filter(r -> edad >= r[0] && edad <= r[1])
                            .map(r -> r[0] + "-" + r[1])
                            .findFirst().orElse("Sin rango");
                }
        ));
    }

    // e. nombreProfesorMasGrupos
    public String nombreProfesorMasGruposImperativo(Integer edadMin, Integer edadMax) {
        Preconditions.checkArgument(edadMin < edadMax, "Edad mínima debe ser menor que máxima");
        Map<String, Integer> count = new HashMap<>();
        for (Asignacion a : centro.asignaciones().todas()) {
            Profesor p = centro.profesores().profesor(a.dni());
            if (p != null) {
                int edad = Period.between(p.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                if (edad >= edadMin && edad <= edadMax) {
                    count.put(p.nombre(), count.getOrDefault(p.nombre(), 0) + 1);
                }
            }
        }
        return count.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public String nombreProfesorMasGruposFuncional(Integer edadMin, Integer edadMax) {
        Preconditions.checkArgument(edadMin < edadMax, "Edad mínima debe ser menor que máxima");
        return centro.asignaciones().todas().stream()
                .map(a -> centro.profesores().profesor(a.dni()))
                .filter(Objects::nonNull)
                .filter(p -> {
                    int edad = Period.between(p.fechaDeNacimiento().toLocalDate(), LocalDate.now()).getYears();
                    return edad >= edadMin && edad <= edadMax;
                })
                .collect(Collectors.groupingBy(Profesor::nombre, Collectors.reducing(0, e -> 1, Integer::sum)))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    // f. nombresAlumnosMayorNota
    public List<String> nombresAlumnosMayorNotaImperativo(Integer anio) {
        List<Alumno> lista = new ArrayList<>();
        for (Alumno a : centro.alumnos().todos()) {
            if (a.fechaDeNacimiento().getYear() > anio) {
                lista.add(a);
            }
        }
        lista.sort(Comparator.comparing(Alumno::nota).reversed());
        return lista.stream().limit(5).map(Alumno::nombre).toList();
    }

    public List<String> nombresAlumnosMayorNotaFuncional(Integer anio) {
        return centro.alumnos().todos().stream()
                .filter(a -> a.fechaDeNacimiento().getYear() > anio)
                .sorted(Comparator.comparing(Alumno::nota).reversed())
                .limit(5)
                .map(Alumno::nombre)
                .toList();
    }
}