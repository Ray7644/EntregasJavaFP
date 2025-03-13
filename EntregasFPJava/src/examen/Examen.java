package examen;

import java.io.*;
import java.util.List;

public class Examen {
    public static long productoImpares(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El valor de n debe ser mayor que 0");
        }
        
        long producto = 1;
        int contador = 0;
        int numero = 1;
        
        while (contador < n) {
            producto *= numero;
            numero += 2;
            contador++;
        }
        
        return producto;
    }
    
    public static double sumaGeometricaAlternada(double a1, double r, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k debe ser mayor que 0");
        }
        if (a1 <= 0 || r <= 0) {
            throw new IllegalArgumentException("a1 y r deben ser positivos");
        }
        
        double suma = 0;
        for (int n = 0; n < k; n++) {
            suma += Math.pow(-1, n) * a1 * Math.pow(r, n);
        }
        
        return suma;
    }
    
    public static long combinatorioSinMultiplosDeTres(int n, int k) {
        if (n < k || n < 0 || k < 0) {
            throw new IllegalArgumentException("n debe ser mayor o igual a k y ambos deben ser positivos");
        }
        
        long numerador = 1, denominador = 1;
        for (int i = 0; i < k; i++) {
            if ((n - i) % 3 != 0) {
                numerador *= (n - i);
            }
            if ((k - i) % 3 != 0) {
                denominador *= (k - i);
            }
        }
        
        return numerador / denominador;
    }
    
    public static void filtrarLineasConsecutivas(String nombreArchivo, List<String> palabrasClave) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                for (String palabra : palabrasClave) {
                    String doblePalabra = palabra + " " + palabra;
                    if (linea.contains(doblePalabra)) {
                        System.out.println(linea);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        int n = 5; 
        System.out.println("El producto de los primeros " + n + " números impares es: " + productoImpares(n));
        
        double a1 = 2, r = 3;
        int k = 3;
        System.out.println("Suma geométrica alternada: " + sumaGeometricaAlternada(a1, r, k));
        
        int nCombi = 5, kCombi = 3;
        System.out.println("Combinatorio sin múltiplos de 3: " + combinatorioSinMultiplosDeTres(nCombi, kCombi));
        
        filtrarLineasConsecutivas("resources/fichero.txt", List.of("ejemplo", "prueba"));
    }
}
