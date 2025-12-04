/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed;

public class NodoAVL {
    Lugar equipe;      // O dado armazenado
    NodoAVL esquerda;
    NodoAVL direita;
    int altura;

    public NodoAVL(Lugar equipe) {
        this.equipe = equipe;
        this.altura = 1;
    }
}
