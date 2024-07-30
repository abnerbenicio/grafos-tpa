package lib;

import java.util.ArrayList;

//Classe Grafo
public class Grafo<T> {
    private final ArrayList<Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    public Vertice<T> addVertice(T valor) {
        if (findVertice(valor) == null) {
            Vertice<T> vertice = new Vertice<>(valor);
            vertices.add(vertice);

            return vertice;
        }

        return null;
    }

    public Vertice<T> findVertice(T valor) {
        for (Vertice<T> vertice : vertices) {
            if (vertice.getValor().equals(valor)) {
                return vertice;
            }
        }
        return null;
    }

    public ArrayList<Vertice<T>> getVertices() {
        return vertices;
    }
}
