package lib;

import java.util.ArrayList;

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
        if (findVertice(valor) == null) {
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
        //Percorre vertices do grafo
        for (Vertice<T> vertice : vertices) {
            //Verifica se vertice existe
            if (vertice.getValor().equals(valor)) {
                //Se existir, retorna o vertice
                return vertice;
            }
        }
        //Se o vertice não existir, retorna null
        return null;
    }

    //Método get para buscar os vertices
    public ArrayList<Vertice<T>> getVertices() {
        return vertices;
    }
}
