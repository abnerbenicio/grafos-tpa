package app;

import java.util.Objects;

//Classe cidade
public class Cidade {
    //Inicialização de variáveis
    private final String nome;

    //Construtor da classe Cidade
    public Cidade(String nome) {
        this.nome = nome;
    }

    //Método get do nome da cidade
    public String getNome() {
        return nome;
    }

    //Sobrescrevendo método equals
    @Override
    public boolean equals(Object o) {
        //Verificando se objeto passado é igual
        if (this == o) return true;
        //Verificando se objeto passado é nulo
        if (o == null || getClass() != o.getClass()) return false;
        //Verificando se objeto passado tem o mesmo nome da instância cidade
        Cidade cidade = (Cidade) o;
        return Objects.equals(nome, cidade.nome);
    }

    //Sobrescrevendo método hashCode
    @Override
    public int hashCode() {
        //Definindo hashCode como igual para cidades com o mesmo nome
        return Objects.hash(nome);
    }
}

