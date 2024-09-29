
package tarea01;

/**
 * Ejercicio 2. Operaciones con constantes y variables.
 *
 * @author  David Llopis Laguna
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        //---------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        final double CONSTANTE1 = 80.3;
        final int CONSTANTE2 = 3;

        // Variables de entrada
        
        // Variables de salida
        
        // Variables auxiliares
        boolean valorBool;
        byte enteroByte;
        short enteroShort;
        int enteroInt, producto;
        long enteroLong;
        double decimalDoble;
        float decimalSimple;

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        // No hay entrada de datos
        System.out.println("OPERACIONES CON CONSTANTES Y VARIABLES.");
        System.out.println("---------------------------------------");
        System.out.println(" ");

        //----------------------------------------------
        //     Procesamiento 
        //----------------------------------------------
        
        //----------------------------------------------
        // Ejemplos que se proporcionan como modelo:
        
        // Las variables booleanas solo pueden tener los valores true o false
        // valorBool = 0;
        
        // decimalSimple = 9.9 * 4.6;       
        // SOLUCIÓN: CASTING EXPLÍCITO
        decimalSimple = (float) (9.9 * 4.6);

        // CASTING IMPLÍCITO: de tipo char a tipo int
        enteroInt = 'a';
        //----------------------------------------------

        decimalDoble = 5,17;
        
        enteroLong = 25_369L;

        producto = enteroLong * enteroInt;

        valorBool = (97 == 'a' == 97);

        CONSTANTE1 = 100.3;

        decimalSimple = 'c';

        decimalDoble = 3.2 * 4.7;

        decimalSimple = 9.9 * 4.6;

        producto = CONSTANTE1 * CONSTANTE2;

        decimalDoble = 5.67F;

        decimalDoble = 8;

        enteroInt = 1 / 2;
    }
}
