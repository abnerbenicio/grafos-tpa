package lib;

import java.util.ArrayList;

//Classe Vértice
public class Vertice<T> {
    //Inicializando variáveis
    private final T valor;
    private final ArrayList<Aresta> destinos;

    //Constructor da classe Vertice
    public Vertice(T valor) {
        this.valor = valor;
        this.destinos = new ArrayList<>();
    }

    public void addDestino(Aresta a){
        this.destinos.add(a);
    }

    //Método getValor para buscar valor do vértice
    public T getValor() {
        return valor;
    }

    //Método getDestinos para buscar o destino
    public ArrayList<Aresta> getDestinos() {
        return destinos;
    }

    @Override
    public String toString() {
        return valor.toString(); //valor é uma Cidade, retorna o toString() da Cidade
    }
}
