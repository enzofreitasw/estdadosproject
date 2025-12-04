/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed;

import org.openstreetmap.gui.jmapviewer.Coordinate;
/**
 *
 * @author enzof
 */
class Lugar {
    private String nome;
    private String tipo;
    private Coordinate coord;

    public Lugar(String nome, String tipo, Coordinate coord) {
        this.nome = nome;
        this.tipo = tipo;
        this.coord = coord;
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public Coordinate getCoord() { return coord; }

    @Override
    public String toString() {
        return nome + " (" + tipo + ") [" + coord.getLat() + "," + coord.getLon() + "]";
    }
    
    private boolean disponivel = true; 

    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
