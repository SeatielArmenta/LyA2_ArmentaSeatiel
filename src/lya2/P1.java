/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lya2;

import java.util.Stack;

/**
 *
 * @author Seatiel
 */
class Nodo {
    char caracter;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(char val) {
        this.caracter = val;
        this.izquierdo = null;
        this.derecho = null;
    }
}
public class P1 {
    private Nodo raiz;

    public P1(String expresionInfija) {
        String postfixExpression = infijoAPosfijo(expresionInfija);
        raiz = construirArbol(postfixExpression);
    }

    private String infijoAPosfijo(String expresionInfija) {
        System.out.println("Expresión infija: " + expresionInfija);
        StringBuilder posfijo = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : expresionInfija.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                posfijo.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    posfijo.append(stack.pop());
                }
                stack.pop(); // Sacamos el '('
            } else {
                while (!stack.isEmpty() && precedente(c) <= precedente(stack.peek())) {
                    posfijo.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            posfijo.append(stack.pop());
        }

        return posfijo.toString();
    }

    private int precedente(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        }
        return 0;
    }

    private Nodo construirArbol(String expresionposfijo) {
        Stack<Nodo> stack = new Stack<>();
        System.out.println("Expresión postfijo: " + expresionposfijo);

        for (char c : expresionposfijo.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                Nodo node = new Nodo(c);
                stack.push(node);
            } else {
                Nodo operandoDerecho = stack.pop();
                Nodo operandoIzquierdo = stack.pop();
                Nodo nodoOperador = new Nodo(c);
                nodoOperador.izquierdo = operandoIzquierdo;
                nodoOperador.derecho = operandoDerecho;
                stack.push(nodoOperador);
            }
        }

        return stack.pop();
    }

    public String recorridoPostorden() {
        StringBuilder resultado = new StringBuilder();
        recorridoPostorden(raiz, resultado);
        return resultado.toString();
    }

    private void recorridoPostorden (Nodo node, StringBuilder resultado) {
        if (node != null) {
            recorridoPostorden(node.izquierdo, resultado);
            recorridoPostorden(node.derecho, resultado);
            resultado.append(node.caracter);
        }
    }
}
