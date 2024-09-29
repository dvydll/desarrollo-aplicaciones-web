/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tarea01;

/**
 *
 * @author David Llopis Laguna
 * @param <A> Tipo del primer parámetro de la función
 * @param <B> Tipo del segundo parámetro de la función
 * @param <C> Tipo del tercer parámetro de la función
 * @param <R> Tipo del retorno de la función
 */
@FunctionalInterface
public interface TriFunction<A, B, C, R> {

    R apply(A a, B b, C c);
}
