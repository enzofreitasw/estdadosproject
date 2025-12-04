package aed;

public class FilaDeSolicitacoes {
    
    public Nodo primeiro;
    public Nodo ultimo;
    public int totalDeSolicitacoes;
    
    public FilaDeSolicitacoes(){
        primeiro = null;
        ultimo = null;
        totalDeSolicitacoes = 0;
    }
    
    private void atualizarPosicoes() {
        Nodo aux = primeiro;
        int pos = 1;
        while (aux != null) {
            aux.solicitacao.setPosicaoNaFila(pos);
            aux = aux.posterior;
            pos++;
        }
    }
    
    public void CadastrarSolicitacao(Solicitacao solicitacao) {
        Nodo novo = new Nodo(solicitacao);

        // Caso 1: Fila vazia
        if (primeiro == null) {
            primeiro = ultimo = novo;
            totalDeSolicitacoes = 1;
            atualizarPosicoes();
            return;
        }

        int grauNovo = solicitacao.getGrauDeEmergencia();

        // Caso 2: Inserir no início (Mais urgente que o atual primeiro)
        if (grauNovo > primeiro.solicitacao.getGrauDeEmergencia()) {
            novo.posterior = primeiro;
            primeiro = novo;
            totalDeSolicitacoes++;
            atualizarPosicoes();
            return;
        }

        // Caso 3: Procurar posição no meio ou fim
        Nodo anterior = primeiro;
        Nodo atual = primeiro.posterior;

        // Avança enquanto houver item e o novo for MENOR ou IGUAL ao atual
        while (atual != null && grauNovo <= atual.solicitacao.getGrauDeEmergencia()) {
            anterior = atual;
            atual = atual.posterior;
        }

        // Inserção
        anterior.posterior = novo;
        novo.posterior = atual;

        // Se inseriu no fim, atualiza o último
        if (atual == null) {
            ultimo = novo;
        }

        totalDeSolicitacoes++;
        atualizarPosicoes();
    }

    public void atenderSolicitacao() {
        if (totalDeSolicitacoes == 0) {
            System.out.println("Fila vazia!");
            return;
        } 
        
        // Lógica de remoção segura
        Nodo removido = primeiro; // Guarda quem sai
        
        if (primeiro == ultimo) {
            // Se só tinha 1 item, zera tudo
            primeiro = null;
            ultimo = null;
        } else {
            // Se tinha mais itens, o segundo vira o primeiro
            primeiro = primeiro.posterior;
        }
        
        // Limpa a referência do removido 
        removido.posterior = null;
        
        totalDeSolicitacoes--;
        
        // Atualiza as posições de quem ficou
        atualizarPosicoes(); 
        
        System.out.println("Solicitação " + removido.solicitacao.getID() + " atendida.");
    }
}