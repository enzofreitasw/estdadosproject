/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed;

/**
 *
 * @author enzof
 */
public class Aresta {
    private Lugar destino;
    private double peso; 

    public Aresta(Lugar destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Lugar getDestino() { return destino; }
    public double getPeso() { return peso; }
}
