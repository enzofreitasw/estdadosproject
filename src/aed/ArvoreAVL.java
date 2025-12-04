/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed;

public class ArvoreAVL {
    private NodoAVL raiz;

    
    public void inserir(Lugar equipe) {
        raiz = inserirNo(raiz, equipe);
    }

    public void remover(String nomeEquipe) {
        raiz = removerNo(raiz, nomeEquipe);
    }

    public void imprimirDisponiveis() {
        System.out.println("\n--- EQUIPES DISPONÍVEIS (AVL) ---");
        emOrdem(raiz);
        System.out.println("---------------------------------");
    }

    private void emOrdem(NodoAVL no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.println(" > " + no.equipe.getNome() + " [" + no.equipe.getTipo() + "]");
            emOrdem(no.direita);
        }
    }

    // --- LÓGICA INTERNA DA AVL (Balanceamento) ---

    private int altura(NodoAVL N) {
        if (N == null) return 0;
        return N.altura;
    }

    private int getBalanceamento(NodoAVL N) {
        if (N == null) return 0;
        return altura(N.esquerda) - altura(N.direita);
    }

    private NodoAVL rotacaoDireita(NodoAVL y) {
        NodoAVL x = y.esquerda;
        NodoAVL T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        return x;
    }

    private NodoAVL rotacaoEsquerda(NodoAVL x) {
        NodoAVL y = x.direita;
        NodoAVL T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        return y;
    }

    // --- INSERÇÃO ---
    private NodoAVL inserirNo(NodoAVL no, Lugar equipe) {
        if (no == null) return new NodoAVL(equipe);

        // Compara nomes para decidir esquerda ou direita
        if (equipe.getNome().compareTo(no.equipe.getNome()) < 0)
            no.esquerda = inserirNo(no.esquerda, equipe);
        else if (equipe.getNome().compareTo(no.equipe.getNome()) > 0)
            no.direita = inserirNo(no.direita, equipe);
        else
            return no; // Nomes iguais não permitidos/necessários

        // Balancear
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int balance = getBalanceamento(no);

        // Casos de Rotação
        if (balance > 1 && equipe.getNome().compareTo(no.esquerda.equipe.getNome()) < 0)
            return rotacaoDireita(no);
        if (balance < -1 && equipe.getNome().compareTo(no.direita.equipe.getNome()) > 0)
            return rotacaoEsquerda(no);
        if (balance > 1 && equipe.getNome().compareTo(no.esquerda.equipe.getNome()) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balance < -1 && equipe.getNome().compareTo(no.direita.equipe.getNome()) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    // --- REMOÇÃO ---
    private NodoAVL removerNo(NodoAVL root, String nome) {
        if (root == null) return root;

        if (nome.compareTo(root.equipe.getNome()) < 0)
            root.esquerda = removerNo(root.esquerda, nome);
        else if (nome.compareTo(root.equipe.getNome()) > 0)
            root.direita = removerNo(root.direita, nome);
        else {
            // Nó encontrado
            if ((root.esquerda == null) || (root.direita == null)) {
                NodoAVL temp = (root.esquerda != null) ? root.esquerda : root.direita;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                NodoAVL temp = menorNo(root.direita);
                root.equipe = temp.equipe;
                root.direita = removerNo(root.direita, temp.equipe.getNome());
            }
        }

        if (root == null) return root;

        // Rebalancear após remoção
        root.altura = Math.max(altura(root.esquerda), altura(root.direita)) + 1;
        int balance = getBalanceamento(root);

        if (balance > 1 && getBalanceamento(root.esquerda) >= 0)
            return rotacaoDireita(root);
        if (balance > 1 && getBalanceamento(root.esquerda) < 0) {
            root.esquerda = rotacaoEsquerda(root.esquerda);
            return rotacaoDireita(root);
        }
        if (balance < -1 && getBalanceamento(root.direita) <= 0)
            return rotacaoEsquerda(root);
        if (balance < -1 && getBalanceamento(root.direita) > 0) {
            root.direita = rotacaoDireita(root.direita);
            return rotacaoEsquerda(root);
        }
        return root;
    }

    private NodoAVL menorNo(NodoAVL no) {
        NodoAVL atual = no;
        while (atual.esquerda != null) atual = atual.esquerda;
        return atual;
    }
    
    // --- NOVO MÉTODO PARA VISUALIZAR A ESTRUTURA ---
    
    public void imprimirArvoreVisual() {
        System.out.println("\n--- ESTRUTURA VISUAL DA AVL ---");
        imprimirVisual(raiz, 0);
        System.out.println("-------------------------------\n");
    }

    private void imprimirVisual(NodoAVL no, int nivel) {
        if (no == null) return;
        
        // Imprime primeiro o lado DIREITO (para ficar no topo visualmente)
        imprimirVisual(no.direita, nivel + 1);
        
        // Dá o recuo baseado no nível (altura)
        for (int i = 0; i < nivel; i++) {
            System.out.print("        "); // 8 espaços
        }
        
        // Imprime o dado
        System.out.println(no.equipe.getNome());
        
        // Imprime o lado ESQUERDO
        imprimirVisual(no.esquerda, nivel + 1);
    }
    
}
