package tarea01;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Ejercicio 3. Validación de contraseñas.
 *
 * @author David Llopis Laguna
 */
public class Ejercicio03 {

    private enum PasswordRequirements {
        LONGITUD_MINIMA("La longitud de la contraseña debe ser, como mínimo, de 12 caracteres."),
        COMENZAR_VOCAL("Tiene que comenzar con una vocal (mayúscula o minúscula)."),
        TERMINAR_CONSONANTE("Debe terminar con una consonante (mayúscula o minúscula)."),
        CARACTERES_ESPECIALES("Tiene que contener, al menos, uno de los siguientes caracteres especiales: arroba ('@'), almohadilla ('#'), barra baja ('_'), punto ('.') o punto y coma (';').");

        private final String description;

        PasswordRequirements(String description) {
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
        final int MIN_LENGTH = 12;

        // Variables de entrada
        String userPassword;

        // Variables de salida
        String result;
        List<String> errors = new ArrayList();

        // Variables auxiliares
        Scanner console = new Scanner(System.in); // Clase Scanner para petición de datos de entrada
        Predicate<String> hasMinLength = (String password) -> password.length() >= MIN_LENGTH;
        Predicate<String> beginWithVocal = (String password)
                -> String.valueOf(password.charAt(0)).matches("[aeiouAEIOU]");
        Predicate<String> endWithConsonant = (String password)
                -> String.valueOf(password.charAt(password.length() - 1))
                        .matches("[bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ]");
        Predicate<String> hasSpecialChar = (String password) -> password.matches(".*[@#_.;].*");
        Predicate<String> isValidPassword = (String password) -> {
            // Comprobamos si tiene como mínimo 12 caracteres
            final boolean testResultMinLength = hasMinLength.test(password);
            
            // Comprobamos si comienza por una vocal (mayúscula o minúscula)
            final boolean testResultBeginWithVocal = beginWithVocal.test(password);
            
            // Comprobamos si termina con una consonante (mayúscula o minúscula)
            final boolean testResultEndWithConsonant = endWithConsonant.test(password);
            
            // Comprobamos si contiene al menos uno de los caracteres especiales requeridos
            final boolean testResultHasSpecialChar = hasSpecialChar.test(password);

            // Añadir mensaje de error si no se cumple la condición
            errors.add(!testResultMinLength ? PasswordRequirements.LONGITUD_MINIMA.getDescription() : null);
            errors.add(!testResultBeginWithVocal ? PasswordRequirements.COMENZAR_VOCAL.getDescription() : null);
            errors.add(!testResultEndWithConsonant ? PasswordRequirements.TERMINAR_CONSONANTE.getDescription() : null);
            errors.add(!testResultHasSpecialChar ? PasswordRequirements.CARACTERES_ESPECIALES.getDescription() : null);

            errors.removeIf(Objects::isNull); // Eliminar cualquier valor nulo de la lista de errores

            return testResultMinLength
                    && testResultBeginWithVocal
                    && testResultEndWithConsonant
                    && testResultHasSpecialChar;
        };

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("VALIDACIÓN DE CONTRASEÑAS");
        System.out.println("-------------------------");
        System.out.println("Introduzca la contraseña: ");
        userPassword = console.next();
        console.close();

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        // Cálculo de información auxiliar previa
        // Comprobamos si tiene como mínimo 12 caracteres
        // Comprobamos si comienza por una vocal (mayúscula o minúscula)
        // Comprobamos si termina con una consonante (mayúscula o minúscula)
        // Comprobamos si contiene al menos uno de los caracteres especiales requeridos
        result = isValidPassword.test(userPassword) ? "VÁLIDA" : "NO VÁLIDA";

        //----------------------------------------------
        //              Salida de resultados
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println("La contraseña es " + result);
        System.out.println(!errors.isEmpty() ? String.join("\n", errors) : "");

        System.out.println();
        System.out.println("Fin del programa.");
    }
}
