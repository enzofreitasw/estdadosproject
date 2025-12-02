/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed;
/**
 *
 * @author guilh
 */

import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.util.UUID;

public class Solicitacao {
    private int tipoRecursoNecessario; // 1=Água, 2=Alimento, etc.
    private UUID ID;
    private Coordinate coordenadas;
    private String tipoDeEmergencia;
    private int grauDeEmergencia;
    private int posicaoNaFila;
    
    public Solicitacao(UUID ID, Coordinate coordenadas, String tipoDeEmergencia, int grauDeEmergencia, int tipoRecursoNecessario) {
        
        this.ID = ID;
        this.coordenadas = coordenadas;
        this.tipoDeEmergencia = tipoDeEmergencia;
        this.grauDeEmergencia = grauDeEmergencia;
        this.tipoRecursoNecessario = tipoRecursoNecessario;
    }

    /**
     * @return the ID
     */
    public UUID getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(UUID ID) {
        this.ID = ID;
    }

    /**
     * @return the coordenadas
     */
    public Coordinate getCoordenadas() {
        return coordenadas;
    }

    /**
     * @param coordenadas the coordenadas to set
     */
    public void setCoordenadas(Coordinate coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     * @return the tipoDeEmergencia
     */
    public String getTipoDeEmergencia() {
        return tipoDeEmergencia;
    }

    /**
     * @param tipoDeEmergencia the tipoDeEmergencia to set
     */
    public void setTipoDeEmergencia(String tipoDeEmergencia) {
        this.tipoDeEmergencia = tipoDeEmergencia;
    }

    /**
     * @return the grauDeEmergencia
     */
    public int getGrauDeEmergencia() {
        return grauDeEmergencia;
    }

    /**
     * @param grauDeEmergencia the grauDeEmergencia to set
     */
    public void setGrauDeEmergencia(int grauDeEmergencia) {
        this.grauDeEmergencia = grauDeEmergencia;
    }
    
    /**
     * @return the posicaoNaFila
     */
    public int getPosicaoNaFila() {
        return posicaoNaFila;
    }

    /**
     * @param posicaoNaFila the posicaoNaFila to set
     */
    public void setPosicaoNaFila(int posicaoNaFila) {
        this.posicaoNaFila = posicaoNaFila;
    }
    public int getTipoRecursoNecessario() { return tipoRecursoNecessario; }
    
}
