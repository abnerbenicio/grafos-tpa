package app;

import lib.*;

import java.util.Scanner;

//Classe Aplicativo
public class Aplicativo {

    //Grafo de cidades
    Grafo<Cidade> cidades = new Grafo<Cidade>();

    //Método para adicionar cidade
    public void AcrescentarCidade () {
        //Recebendo nome da cidade
        System.out.println("Digite o nome da Cidade: ");
        Scanner cidade = new Scanner(System.in);
        String cidadeNome = cidade.nextLine();

        try {
            //Tentando adicionar a cidade
            Cidade cidadeAdd = cidades.addVertice(new Cidade(cidadeNome.toLowerCase())).getValor();
            //Informando que cidade foi criada
            System.out.println("A cidade " + cidadeNome + " foi adicionada");
        } catch (NullPointerException e) {
            //Informando que cidade já existe
            System.out.println("A cidade " + cidadeNome + " já foi adicionada no sistema.");
        }
    }

    public void AcrescentarRota () {
        //Escrever código
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Grafo<Cidade> CalcAGM () {
        //Escrever código
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void CalcCaminhoMinimo () {
        //Escrever código
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void CalcCaminhoMinimoAGM () {
        //Escrever código
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void GravarSair () {
        //Escrever código
        throw new UnsupportedOperationException("Not supported yet.");
    }


    //Método para exibir menu
    public void menu() {
        //Exibindo opções
        System.out.println("1 - Acrescentar Cidade");
        System.out.println("2 - Acrescentar Rota");
        System.out.println("3 - Calcular árvore geradora mínima (AGM)");
        System.out.println("4 - Calcular caminho mínimo entre duas cidades");
        System.out.println("5 - Calcular caminho mínimo entre duas cidades considerando apenas a AGM");
        System.out.println("6 - Gravar e Sair");
        System.out.print(">> ");


        //Inicializando input
        Scanner s = new Scanner(System.in);

        //Verificando opção selecionada
        switch (s.nextLine()) {
            //Acrescentar Cidade
            case "1":
                AcrescentarCidade();
                menu();
                break;

            //Acrescentar Rota
            case "2":
                AcrescentarRota();
                menu();
                break;

            //Calcular árvore geradora mínima (AGM)
            case "3":
                CalcAGM();
                menu();
                break;

            //Calcular caminho mínimo entre duas cidades
            case "4":
                CalcCaminhoMinimo();
                menu();
                break;

            //Calcular caminho mínimo entre duas cidades considerando apenas a AGM
            case "5":
                CalcCaminhoMinimoAGM();
                menu();
                break;

            //Gravar e Sair
            case "6":
                GravarSair();
                break;

            //Opção digitada inválida
            default:
                System.out.println("Opção inválida, por favor digite novamente.");
                System.out.print(">> ");
                menu();
        }
    }
}
