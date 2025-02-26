package entrega1;

public class Funciones {

    public static int calcularProducto(int n, int k) {
        int producto = 1;
        for (int i = 0; i < k; i++) {
            producto *= (n - i + 1);
        }
        return producto;
    }

    public static int productoGeometrico(int a1, int r, int k) {
        int producto = 1;
        for (int i = 0; i < k; i++) {
            producto *= a1 * Math.pow(r, i);
        }
        return producto;
    }

    public static double combinatorio(double n, double k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private static double factorial(double num) {
        if (num == 0 || num == 1) return 1;
        double fact = 1;
        for (double i = 2; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }

    public static double calcularS(int n, int k) {
        double suma = 0;
        for (int i = 0; i < k; i++) {
            suma += Math.pow(-1, i) * combinatorio(k + 1, i + 1) * Math.pow(k - i, n);
        }
        return suma / factorial(k);
    }

    public static double metodoNewton(java.util.function.Function<Double, Double> f,
                                      java.util.function.Function<Double, Double> df,
                                      double x0, double error) {
        double x = x0;
        while (Math.abs(f.apply(x)) > error) {
            x = x - f.apply(x) / df.apply(x);
        }
        return x;
    }

    public static void test1() {
        int n = 4;
        int k = 2;
        System.out.println("El producto de " + n + " y " + k + " es: " + calcularProducto(n, k));
    }

    public static void test2() {
    	int a1 = 3;
    	int r = 5;
    	int k = 2;
        System.out.println("El producto de la secuencia geométrica con a1 = " + a1 + ", r = " + r + " y k = " + k + " es: " + productoGeometrico(a1, r, k));
    }

    public static void test3() {
    	int j = 4;
    	int i = 2;
        System.out.println("El número combinatorio de " + j + " y " + i + " es: " + combinatorio(j, i));
    }

    public static void test4() {
    	int n = 4;
    	int k = 2;
        System.out.println("El número S(n,k) siendo n = " + n + " y k = " + k + " es: " + calcularS(n, k));
    }

    public static void test5() {
    	double a = 3;
        double e = 0.001;
        double raiz = metodoNewton(x -> 2 * x * x, x -> 4 * x, a, e);
        System.out.println("Resultado de la función 5 con a = " + a + " y e = " + e + ", f(x) = 2x^2 y f'(x) = 4x: " + raiz);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}