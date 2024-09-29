package tarea01;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.Scanner;

/**
 * Ejercicio 1. Cálculos aritméticos.
 *
 * @author David Llopis Laguna
 */
public class Ejercicio01 {

    // Definición del enum
    enum Operations {
        OPERACION_1("f(x) = x / 3 + 8"),
        OPERACION_2("f(x, y, z) = x² / y² + y² / z²"),
        OPERACION_3("f(x, y) = x² + 3xy + y² / (1 / x²)");

        private final String description;

        Operations(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        final double FIRST_TEST_X = 2, SECOND_TEST_X = 0.5, THIRD_TEST_X = 1.3;
        final double FIRST_TEST_Y = 3, SECOND_TEST_Y = 10, THIRD_TEST_Y = 4.2;
        final double FIRST_TEST_Z = 4, SECOND_TEST_Z = 5, THIRD_TEST_Z = 8.9;

        // Variables de entrada
        double userX, userY, userZ;

        // Variables de salida
        Function<Double, Double> firstOperation = (Double x) -> x / 3.0 + 8.0;
        TriFunction<Double, Double, Double, Double> secondOperation = (Double x, Double y, Double z) -> Math.pow(x, 2) / Math.pow(y, 2) + Math.pow(y, 2) / Math.pow(z, 2);
        BiFunction<Double, Double, Double> thirdOperation = (Double x, Double y) -> (Math.pow(x, 2) + 3 * x * y + Math.pow(y, 2)) / Math.pow(x, -2);

        // Variables auxiliares
        // Clase Scanner para petición de datos de entrada
        Scanner console = new Scanner(System.in);
        // Plantilla de salida de datos
        TriFunction<Double, Double, Double, Void> printResults = (Double x, Double y, Double z) -> {
            System.out.println("CÁLCULOS ARITMÉTICOS");
            System.out.println("--------------------");
            System.out.println("Valor para la x: " + x);
            System.out.println("Valor para la y: " + y);
            System.out.println("Valor para la z: " + z);

            System.out.println("\nRESULTADO");
            System.out.println("---------");
            System.out.println(Operations.OPERACION_1 + ": " + Operations.OPERACION_1.getDescription() + " => " + firstOperation.apply(x));
            System.out.println(Operations.OPERACION_2 + ": " + Operations.OPERACION_2.getDescription() + " => " + secondOperation.apply(x, y, z));
            System.out.println(Operations.OPERACION_3 + ": " + Operations.OPERACION_3.getDescription() + " => " + thirdOperation.apply(x, y));
            return null;
        };

        //----------------------------------------------
        //                Entrada de datos
        //----------------------------------------------
        System.out.print("Introduce un valor para 'x': ");
        userX = console.nextDouble();
        System.out.print("Introduce un valor para 'y': ");
        userY = console.nextDouble();
        System.out.print("Introduce un valor para 'z': ");
        userZ = console.nextDouble();

        //----------------------------------------------
        //                 Procesamiento
        //----------------------------------------------

        /*
         * En mi caso la ejecución está encapsulada en las propias variables
         * de salida, ya que estas son expresiones lambda.
         */

        //----------------------------------------------
        //              Salida de resultados
        //----------------------------------------------
        System.out.println("\n······························\n");
        printResults.apply(FIRST_TEST_X, FIRST_TEST_Y, FIRST_TEST_Z);
        System.out.println("\n······························\n");
        printResults.apply(SECOND_TEST_X, SECOND_TEST_Y, SECOND_TEST_Z);
        System.out.println("\n······························\n");
        printResults.apply(THIRD_TEST_X, THIRD_TEST_Y, THIRD_TEST_Z);
        System.out.println("\n······························\n");
        printResults.apply(userX, userY, userZ);
        System.out.println("\n······························\n");

        System.out.println();
        System.out.println("Fin del programa.");
    }
}
