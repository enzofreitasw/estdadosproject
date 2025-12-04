/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed;

/**
 *
 * @author enzof
 */
import java.util.*;

public class Grafo {
    
    private Map<Lugar, List<Aresta>> adjacencias;

    public Grafo() {
        this.adjacencias = new HashMap<>();
    }

    // Adiciona um vértice (Lugar) ao grafo
    public void adicionarLugar(Lugar lugar) {
        adjacencias.putIfAbsent(lugar, new ArrayList<>());
    }

    // Adiciona uma aresta (bidirecional para ruas de mão dupla)
    public void adicionarRota(Lugar origem, Lugar destino, double distancia) {
        // Garante que os lugares existem no grafo
        adicionarLugar(origem);
        adicionarLugar(destino);

        // Adiciona conexão Origem -> Destino
        adjacencias.get(origem).add(new Aresta(destino, distancia));
        
        // Adiciona conexão Destino -> Origem (se o caminho for de mão dupla)
        adjacencias.get(destino).add(new Aresta(origem, distancia));
    }

    public List<Lugar> getLugares() {
        return new ArrayList<>(adjacencias.keySet());
    }

    /**
     * Algoritmo de Dijkstra
     * Retorna um Mapa com as distâncias mínimas do 'inicio' para todos os outros nós.
     * @param inicio O nó de onde parte a busca (ex: Local do Desastre)
     * @return Map<Lugar, Double> onde Double é a distância acumulada
     */
    public Map<Lugar, Double> executarDijkstra(Lugar inicio) {
        Map<Lugar, Double> distancias = new HashMap<>();
        PriorityQueue<Lugar> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        Set<Lugar> visitados = new HashSet<>();

        // Inicializa distâncias como infinito
        for (Lugar l : adjacencias.keySet()) {
            distancias.put(l, Double.MAX_VALUE);
        }
        distancias.put(inicio, 0.0);
        filaPrioridade.add(inicio);

        while (!filaPrioridade.isEmpty()) {
            Lugar atual = filaPrioridade.poll();

            if (visitados.contains(atual)) continue;
            visitados.add(atual);

            if (adjacencias.containsKey(atual)) {
                for (Aresta aresta : adjacencias.get(atual)) {
                    Lugar vizinho = aresta.getDestino();
                    if (!visitados.contains(vizinho)) {
                        double novaDistancia = distancias.get(atual) + aresta.getPeso();
                        if (novaDistancia < distancias.get(vizinho)) {
                            distancias.put(vizinho, novaDistancia);
                            
                            // Remove e readiciona para atualizar a prioridade na fila
                            filaPrioridade.remove(vizinho);
                            filaPrioridade.add(vizinho);
                        }
                    }
                }
            }
        }
        return distancias;
    }

    
    public List<Lugar> obterMenorCaminho(Lugar origem, Lugar destino) {
        Map<Lugar, Double> distancias = new HashMap<>();
        Map<Lugar, Lugar> antecessores = new HashMap<>(); // Para reconstruir o caminho
        PriorityQueue<Lugar> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        Set<Lugar> visitados = new HashSet<>();

        for (Lugar l : adjacencias.keySet()) {
            distancias.put(l, Double.MAX_VALUE);
        }
        distancias.put(origem, 0.0);
        filaPrioridade.add(origem);

        while (!filaPrioridade.isEmpty()) {
            Lugar atual = filaPrioridade.poll();

            if (atual.equals(destino)) break; 
            
            if (visitados.contains(atual)) continue;
            visitados.add(atual);

            if (adjacencias.containsKey(atual)) {
                for (Aresta aresta : adjacencias.get(atual)) {
                    Lugar vizinho = aresta.getDestino();
                    if (!visitados.contains(vizinho)) {
                        double novaDistancia = distancias.get(atual) + aresta.getPeso();
                        if (novaDistancia < distancias.get(vizinho)) {
                            distancias.put(vizinho, novaDistancia);
                            antecessores.put(vizinho, atual); // Guarda: "Para chegar no vizinho, vim do atual"
                            
                            filaPrioridade.remove(vizinho);
                            filaPrioridade.add(vizinho);
                        }
                    }
                }
            }
        }

        List<Lugar> caminho = new LinkedList<>();
        Lugar passo = destino;
        
        if (distancias.get(destino) == Double.MAX_VALUE) {
            return caminho; 
        }

        while (passo != null) {
            caminho.add(0, passo); 
            passo = antecessores.get(passo);
        }
        
        return caminho;
    }
}
