package lib;

import java.util.ArrayList;

//Classe Vértice
public class Vertice<T> {
    //Inicializando variáveis
    private T valor;
    private ArrayList<Aresta> destinos;

    //Constructor da classe Vertice
    public Vertice(T valor) {
        this.valor = valor;
        this.destinos = new ArrayList<>();
    }

    //Método getValor para buscar valor do vértice
    public T getValor() {
        return valor;
    }

    //Método getDestinos para buscar o destino
    public ArrayList<Aresta> getDestinos() {
        return destinos;
    }
}
