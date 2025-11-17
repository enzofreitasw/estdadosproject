/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed;

/**
 *
 * @author enzof
 */
class Rota {
    private Lugar origem;
    private Lugar destino;
    private double distancia;

    public Rota(Lugar origem, Lugar destino, double distancia) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "De " + origem.getNome() + " até " + destino.getNome() +
                " | Distância: " + String.format("%.2f km", distancia);
    }
}
