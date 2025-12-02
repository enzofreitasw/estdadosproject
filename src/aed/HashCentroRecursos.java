package aed;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.util.HashSet;
import java.util.Set;

public class HashCentroRecursos {
    
    // Grid 11x11
    private CentroRecursos[] hashRoot = new CentroRecursos[121];

    // --- LIMITES DE CRUZ DAS ALMAS (Ajustado para o seu mapa) ---
    private final double MIN_LON = -39.1300;
    private final double MAX_LON = -39.0800;
    private final double MIN_LAT = -12.6800;
    private final double MAX_LAT = -12.6400;

    public HashCentroRecursos(){ }

    // Calcula índice i (0 a 10) baseado na Longitude
    private int getIndiceI(double lon) {
        double pct = (lon - MIN_LON) / (MAX_LON - MIN_LON);
        int i = (int) (pct * 10);
        if (i < 0) i = 0;
        if (i > 10) i = 10;
        return i;
    }

    // Calcula índice j (0 a 10) baseado na Latitude
    private int getIndiceJ(double lat) {
        double pct = (lat - MIN_LAT) / (MAX_LAT - MIN_LAT);
        int j = (int) (pct * 10);
        if (j < 0) j = 0;
        if (j > 10) j = 10;
        return j;
    }

    public void addCentroRec(CentroRecursos centro) {
        int i = getIndiceI(centro.getCoord().getLon());
        int j = getIndiceJ(centro.getCoord().getLat());
        
        int pos = 11 * i + j; 

        if (this.hashRoot[pos] == null)
            this.hashRoot[pos] = centro;
        else
            this.hashRoot[pos].addCentroColisao(centro);
    }
    
    // Verifica se um centro específico (ou sua lista de colisão) tem o recurso
    private CentroRecursos verificarCentro(CentroRecursos centro, int tipoRecurso) {
        // Verifica o cabeça
        int idx = tipoRecurso - 1;
        if (centro.getListaRecursos()[idx] != null && centro.getListaRecursos()[idx].getQtd() > 0) {
            return centro;
        }
        // Verifica a lista de colisão
        for (CentroRecursos c : centro.getListaColisao()) {
            if (c.getListaRecursos()[idx] != null && c.getListaRecursos()[idx].getQtd() > 0) {
                return c;
            }
        }
        return null;
    }

    // Busca exata pelo índice da matriz
    public CentroRecursos searchCentro(int tipoRecurso, int i, int j){
        if (i < 0 || i > 10 || j < 0 || j > 10) return null;
        
        int pos = 11 * i + j;
        if(this.hashRoot[pos] != null){
            return verificarCentro(this.hashRoot[pos], tipoRecurso);
        }
        return null;
    }
        
    public CentroRecursos buscarRecursoProximo(Coordinate localDesastre, int tipoRecurso) {
        int iInicial = getIndiceI(localDesastre.getLon());
        int jInicial = getIndiceJ(localDesastre.getLat());
        
        System.out.println("Buscando recurso " + tipoRecurso + " perto de Grid["+iInicial+","+jInicial+"]");

        Set<Integer> visitados = new HashSet<>();

        // 1. Tenta no próprio quadrante
        CentroRecursos res = searchCentro(tipoRecurso, iInicial, jInicial);
        if (res != null) return res;
        visitados.add(11 * iInicial + jInicial);

        // 2. Busca espiral/vizinhos (Cima, Baixo, Esq, Dir)
        int[][] vizinhos = {
            {0,1}, {0,-1}, {1,0}, {-1,0}, 
            {1,1}, {1,-1}, {-1,1}, {-1,-1} // Inclui diagonais pra garantir
        };

        for (int[] v : vizinhos) {
            int ni = iInicial + v[0];
            int nj = jInicial + v[1];
            res = searchCentro(tipoRecurso, ni, nj);
            if (res != null) return res;
            
            if (ni >=0 && ni<=10 && nj>=0 && nj<=10)
                visitados.add(11 * ni + nj);
        }

        // 3. Varredura completa (Fallback) se não achou perto
        for (int pos = 0; pos < 121; pos++) {
            if (!visitados.contains(pos) && hashRoot[pos] != null) {
                res = verificarCentro(hashRoot[pos], tipoRecurso);
                if (res != null) return res;
            }
        }

        return null; // Não achou recurso em lugar nenhum do mapa
    }
}