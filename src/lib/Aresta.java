package lib;

import java.util.Objects;

//Classe Aresta
public class Aresta<T> {
    //Inicialização de variáveis
    private final Vertice<T> destino;
    private final float peso;

    //Construtor da classe Aresta
    public Aresta(Vertice<T> destino, float peso) {
        this.destino = destino;
        this.peso = peso;
    }

    //Método getPeso para retornar o peso da aresta
    public float getPeso() {
        return peso;
    }

    //Método getDestino para retornar destino
    public Vertice<T> getDestino() {
        return destino;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aresta aresta = (Aresta) obj;
        return Double.compare(aresta.peso, peso) == 0 &&
                Objects.equals(destino, aresta.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash( destino, peso);
    }
}
