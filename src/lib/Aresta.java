package lib;

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
}
