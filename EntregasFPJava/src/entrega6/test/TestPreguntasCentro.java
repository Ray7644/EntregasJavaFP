package entrega6.test;

import entrega6.preguntas.PreguntasCentro;

import java.util.List;

public class TestPreguntasCentro {

    public static void main(String[] args) {
        PreguntasCentro pc = new PreguntasCentro();

        System.out.println("--- a. promedioEdadProfesores ---");
        System.out.println("Imperativo: " + pc.promedioEdadProfesoresImperativo("72842943B"));
        System.out.println("Funcional : " + pc.promedioEdadProfesoresFuncional("72842943B"));

        System.out.println("\n--- b. grupoMayorDiversidadEdad ---");
        System.out.println("Imperativo: " + pc.grupoMayorDiversidadEdadImperativo());
        System.out.println("Funcional : " + pc.grupoMayorDiversidadEdadFuncional());

        System.out.println("\n--- c. alumnoMasMatriculas ---");
        System.out.println("Imperativo: " + pc.alumnoMasMatriculasImperativo());
        System.out.println("Funcional : " + pc.alumnoMasMatriculasFuncional());

        System.out.println("\n--- e. nombreProfesorMasGrupos ---");
        System.out.println("Imperativo: " + pc.nombreProfesorMasGruposImperativo(20, 60));
        System.out.println("Funcional : " + pc.nombreProfesorMasGruposFuncional(20, 60));

        System.out.println("\n--- f. nombresAlumnosMayorNota ---");
        List<String> top1 = pc.nombresAlumnosMayorNotaImperativo(1995);
        List<String> top2 = pc.nombresAlumnosMayorNotaFuncional(1995);
        System.out.println("Imperativo:"); top1.forEach(System.out::println);
        System.out.println("Funcional:"); top2.forEach(System.out::println);
    }
}
