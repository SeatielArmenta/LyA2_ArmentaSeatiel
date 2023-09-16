/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lya2;

/**
 *
 * @author Seatiel
 */
public class LyA2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String expresionInfija = "((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))";
        P1 Arbol = new P1(expresionInfija);
        String postfix = Arbol.recorridoPostorden();
        System.out.println("Expresión infijo: " + expresionInfija);
        System.out.println("Expresión postfijo: " + postfix);
    }
    
}
