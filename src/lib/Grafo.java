package lib;

import java.util.*;

//Classe Grafo
public class Grafo<T> {
    //Inicialização de variáveis
    private final ArrayList<Vertice<T>> vertices;

    //Construtor da classe Grafo
    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    //Método para adicionar vértices
    public Vertice<T> addVertice(T valor) {
        //Verificando se vertice já existe
        if (findVertice(valor).getValor() == null) {
            //Se vertice não existir, cria, adiciona e retorna o vertice
            Vertice<T> vertice = new Vertice<>(valor);
            vertices.add(vertice);
            return vertice;
        }
        //Se vertice existir, retorna null
        return null;
    }

    //Método para buscar o vertice
    public Vertice<T> findVertice(T valor) {
        Vertice<T> verticeNulo = new Vertice<>(null);
        //Percorre vertices do grafo
        for (Vertice<T> vertice : vertices) {
            //Verifica se vertice existe
            if (vertice.getValor().equals(valor)) {
                //Se existir, retorna o vertice
                return vertice;
            }
        }
        //Se o vertice não existir, retorna null

        return verticeNulo;
    }

    public Aresta<T> addAresta(T origem, T destino, float peso){
        Vertice<T> verticeOrigem, verticeDestino;

        verticeOrigem = this.findVertice(origem);
        if (verticeOrigem.getValor()==null){
            verticeOrigem = this.addVertice(origem);
        }

        verticeDestino = this.findVertice(destino);
        if (verticeDestino.getValor()==null){
            verticeDestino = this.addVertice(destino);
        }

        Aresta<T> novaAresta = new Aresta<>(verticeDestino, peso);
        verticeOrigem.addDestino(novaAresta);
        return novaAresta;
    }

    //Método get para buscar os vertices
    public ArrayList<Vertice<T>> getVertices() {
        return vertices;
    }

    public Grafo<T> CalcAgmPrim() {
        if (this.vertices.isEmpty()) {
            System.out.println("O grafo está vazio.");
            return null;
        }

        Grafo<T> agm = new Grafo<>();
        PriorityQueue<Aresta<T>> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(Aresta::getPeso));
        Map<Vertice<T>, Vertice<T>> origemMap = new HashMap<>();
        Set<Vertice<T>> visitados = new HashSet<>();

        // Escolhe o primeiro vértice como origem
        Vertice<T> verticeOrigem = vertices.getFirst();
        visitados.add(verticeOrigem);

        // Adiciona as arestas do vértice de origem na fila de prioridade e armazena a origem
        for (Aresta<T> aresta : verticeOrigem.getDestinos()) {
            filaPrioridade.add(aresta);
            origemMap.put(aresta.getDestino(), verticeOrigem);
        }

        while (!filaPrioridade.isEmpty()) {
            Aresta<T> arestaAtual = filaPrioridade.poll();
            Vertice<T> destino = arestaAtual.getDestino();

            // Verifica se o destino já foi visitado
            if (visitados.contains(destino)) {
                continue; // Pula para a próxima aresta
            }

            // Recupera a origem correta usando o mapa
            Vertice<T> origem = origemMap.get(destino);

            // Adiciona a aresta atual na AGM e marca o destino como visitado
            agm.addAresta(origem.getValor(), destino.getValor(), arestaAtual.getPeso());
            visitados.add(destino);

            // Adiciona as arestas do vértice destino na fila de prioridade e armazena a origem
            for (Aresta<T> novaAresta : destino.getDestinos()) {
                if (!visitados.contains(novaAresta.getDestino())) {
                    filaPrioridade.add(novaAresta);
                    origemMap.put(novaAresta.getDestino(), destino);
                }
            }
        }

        return agm;
    }

    // Método auxiliar para encontrar o vértice de origem dado uma aresta
    private Vertice<T> findVerticeComAresta(Aresta<T> aresta) {
        for (Vertice<T> vertice : vertices) {
            if (vertice.getDestinos().contains(aresta)) {
                return vertice;
            }
        }
        return null;
    }
}
