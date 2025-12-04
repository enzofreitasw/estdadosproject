package aed;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.util.ArrayList;

public class CentroRecursos extends Lugar {

    private Recurso[] listaRecursos = new Recurso[6];
    private ArrayList<CentroRecursos> listaColisao = new ArrayList<>(); 
    
    public CentroRecursos(String nome, Coordinate coord){
        super(nome, "Logística", coord); 
    }
    
    public void addRecurso(int tipo, int qtd){
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