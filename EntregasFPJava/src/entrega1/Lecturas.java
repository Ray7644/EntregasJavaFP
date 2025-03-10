package entrega1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Lecturas {

	public static int contarPalabra(String fichero, String sep, String cad) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(sep);
                for (String palabra : palabras) {
                    if (palabra.equalsIgnoreCase(cad)) {
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contador;
    }

    public static List<String> lineasQueContienen(String fichero, String cad) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.toLowerCase().contains(cad.toLowerCase())) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    public static Set<String> palabrasUnicas(String fichero) {
        Set<String> palabrasUnicas = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(" "); 
                palabrasUnicas.addAll(Arrays.asList(palabras));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabrasUnicas;
    }

    public static double longitudMediaLineasCSV(String fichero, String sep) {
        int totalCaracteres = 0;
        int totalLineas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalCaracteres += linea.split(sep).length;
                totalLineas++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalLineas > 0 ? (double) totalCaracteres / totalLineas : 0;
    }

    public static void testcontarpalabra() {
        String fichero = "resources/lin_quijote.txt";
        String palabra = "quijote";
        System.out.println("El número de veces que aparece la palabra " + palabra + " en el fichero " + fichero + " es: " + contarPalabra(fichero, " ", palabra));
    }

    public static void testlineasquecontienen() {
        String fichero = "resources/lin_quijote.txt";
        String palabra = "quijote";
        System.out.println("Las líneas en las que aparece la palabra " + palabra + " son: " + lineasQueContienen(fichero, palabra));
    }

    public static void testpalabrasunicas() {
        String fichero = "resources/archivo_palabras.txt";
        System.out.println("Las palabras únicas en el fichero " + fichero + " son: " + palabrasUnicas(fichero));
    }

    public static void testlongitudmedialineascsv() {
        String fichero = "resources/palabras_random.csv";
        System.out.println("La longitud promedio de las líneas del fichero " + fichero + " es: " + longitudMediaLineasCSV(fichero, ","));
    }

    public static void main(String[] args) {
        testcontarpalabra();
        testlineasquecontienen();
        testpalabrasunicas();
        testlongitudmedialineascsv();
    }
}
