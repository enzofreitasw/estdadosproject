package aed;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.util.ArrayList;

// IMPORTANTE: Estende Lugar para funcionar no seu Dijkstra
public class CentroRecursos extends Lugar {

    private Recurso[] listaRecursos = new Recurso[6];
    // Lista para tratar colisões do Hash
    private ArrayList<CentroRecursos> listaColisao = new ArrayList<>(); 
    
    public CentroRecursos(String nome, Coordinate coord){
        super(nome, "Logística", coord); // Define como tipo 'Logística' ou crie um novo se quiser
    }
    
    public void addRecurso(int tipo, int qtd){
        // Ajuste de índice (1 virar 0)
        int index = tipo - 1;
        if(index < 0 || index >= 6) return;

        if(this.listaRecursos[index] == null){
            Recurso recurso = new Recurso(tipo, qtd);
            this.listaRecursos[index] = recurso;
        } else {
             this.listaRecursos[index].setQtd(this.listaRecursos[index].getQtd() + qtd);
        }
    }
    
    public void addCentroColisao(CentroRecursos centro){
        this.listaColisao.add(centro);
    }
    
    public ArrayList<CentroRecursos> getListaColisao() { return listaColisao; }
    public Recurso[] getListaRecursos() { return listaRecursos; }
}