package app;

import lib.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//Classe Aplicativo
public class Aplicativo {

    //Grafo de cidades
    Grafo<Cidade> cidades = new Grafo<Cidade>();

    //garante que o arquivo entrada.txt seja lido apenas uma vez, será true após a leitura
    boolean arquivoCarregado = false;

    //Método para adicionar cidade
    public void AcrescentarCidade () {
        //Recebendo nome da cidade
        System.out.println("Digite o nome da Cidade: ");
        Scanner cidade = new Scanner(System.in);
        String cidadeNome = cidade.nextLine();

        try {
            //Tentando adicionar a cidade
            Cidade cidadeAdd = cidades.addVertice(new Cidade(cidadeNome)).getValor();
            //Informando que cidade foi criada
            System.out.println("A cidade " + cidadeNome + " foi adicionada");
        } catch (NullPointerException e) {
            //Informando que cidade já existe
            System.out.println("A cidade " + cidadeNome + " já foi adicionada no sistema.");
        }
    }

    //Método para adicionar cidade usando os dados vindos arquivo entrada.txt
    public void AcrescentarCidade (String cidadeNome) {
        try {
            //Tentando adicionar a cidade
            Cidade cidadeAdd = cidades.addVertice(new Cidade(cidadeNome)).getValor();
            //Informando que cidade foi criada
            System.out.println("A cidade " + cidadeNome + " foi adicionada");
        } catch (NullPointerException e) {
            //Informando que cidade já existe
            System.out.println("A cidade " + cidadeNome + " já foi adicionada no sistema.");
        }
    }

    public void AcrescentarRota () {
        System.out.println("Digite o nome da cidade de origem: ");
        Scanner ScanCidadeOrigem = new Scanner(System.in);
        String nomeCidadeOrigem = ScanCidadeOrigem.nextLine();
        Cidade cidadeOrigem = cidades.findVertice(new Cidade(nomeCidadeOrigem)).getValor();

        System.out.println("Digite o nome da cidade de destino: ");
        Scanner ScanCidadeDestino = new Scanner(System.in);
        String nomeCidadeDestino = ScanCidadeDestino.nextLine();
        Cidade cidadeDestino = cidades.findVertice(new Cidade(nomeCidadeDestino)).getValor();

        System.out.println("Digite a distância entre as duas cidades: ");
        Scanner ScanDistancia = new Scanner(System.in);
        float distancia = ScanDistancia.nextFloat();

        cidades.addAresta(cidadeOrigem, cidadeDestino, distancia);

    }

    //Método para adicionar rota usando os dados vindos do arquivo entrada.txt
    public void AcrescentarRota (Cidade cidadeOrigem, Cidade cidadeDestino, float distancia) {
        cidades.addAresta(cidadeOrigem, cidadeDestino, distancia);
    }

    public void CalcAGM () {
        Grafo<Cidade> agm = cidades.CalcAgmPrim();
        double somaTotalPesos = 0.0;
        Set<Aresta> arestasConsideradas = new HashSet<>();
        System.out.println("Arestas da Árvore Geradora Mínima:");
        for (Vertice<Cidade> vertice : agm.getVertices()) {
            for (Aresta aresta : vertice.getDestinos()) {
                Aresta arestaInvertida = new Aresta(vertice, aresta.getPeso());
                if(arestasConsideradas.contains(arestaInvertida)){
                    continue;
                }
                System.out.println(vertice.getValor().toString() + " - " + aresta.getDestino().getValor().toString() + " : " + aresta.getPeso());
                somaTotalPesos += aresta.getPeso();

                arestasConsideradas.add(aresta);
            }
        }
        System.out.println("Soma total dos pesos: " + somaTotalPesos);
    }

    public void CalcCaminhoMinimo() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar nome da cidade de origem
        System.out.println("Digite o nome da cidade de origem: ");
        String nomeCidadeOrigem = scanner.nextLine();
        Cidade cidadeOrigem = cidades.findVertice(new Cidade(nomeCidadeOrigem)).getValor();

        // Solicitar nome da cidade de destino
        System.out.println("Digite o nome da cidade de destino: ");
        String nomeCidadeDestino = scanner.nextLine();
        Cidade cidadeDestino = cidades.findVertice(new Cidade(nomeCidadeDestino)).getValor();

        // Verificar se as cidades existem
        if (cidadeOrigem == null || cidadeDestino == null) {
            System.out.println("Uma ou ambas as cidades não foram encontradas no grafo.");
            return;
        }

        // Chamar o método de cálculo do caminho mínimo
        cidades.calcCaminhoMinimo(cidadeOrigem, cidadeDestino);
    }

    public void CalcCaminhoMinimoAGM () {
        Scanner scanner = new Scanner(System.in);

        // Solicitar nome da cidade de origem
        System.out.println("Digite o nome da cidade de origem: ");
        String nomeCidadeOrigem = scanner.nextLine();
        Cidade cidadeOrigem = cidades.findVertice(new Cidade(nomeCidadeOrigem)).getValor();

        // Solicitar nome da cidade de destino
        System.out.println("Digite o nome da cidade de destino: ");
        String nomeCidadeDestino = scanner.nextLine();
        Cidade cidadeDestino = cidades.findVertice(new Cidade(nomeCidadeDestino)).getValor();

        // Verificar se as cidades existem
        if (cidadeOrigem == null || cidadeDestino == null) {
            System.out.println("Uma ou ambas as cidades não foram encontradas no grafo.");
            return;
        }

        // Gerar AGM
        Grafo<Cidade> agm = this.cidades.CalcAgmPrim();

        // Chamar o método de cálculo do caminho mínimo
        agm.calcCaminhoMinimo(cidadeOrigem, cidadeDestino);
    }

    public void LerArquivoEntrada(){
        try {
            //Se houver um arquivo entrada.txt, o grafo será inicializado a partir dele.
            Scanner ScanArquivoEntrada = new Scanner(new FileInputStream("entrada.txt"));

            //Lendo número de cidades
            int numeroCidades = Integer.parseInt(ScanArquivoEntrada.nextLine());

            //Lendo nome das cidades e adicionando Cidades ao grafo
            for(int i = 0; i < numeroCidades; i++){
                String cidadeNome = ScanArquivoEntrada.nextLine();
                AcrescentarCidade(cidadeNome);
            }

            //Lendo lista de adjacências de cada Nó(Cidade) e criando Rota
            for (Vertice<Cidade> cidade : this.cidades.getVertices()){
                String[] listaAdjacencias = ScanArquivoEntrada.nextLine().split(",");

                //lê a distância entre uma Cidade e cada uma das outras
                for (int i = 0; i < listaAdjacencias.length; i++){
                    float distancia = Float.parseFloat(listaAdjacencias[i]);
                    //Se há um caminho
                    if(distancia != 0.0){

                        String nomeCidadeOrigem = cidade.toString();
                        String nomeCidadeDestino = this.cidades.getVertices().get(i).toString();

                        Cidade cidadeOrigem = cidades.findVertice(new Cidade(nomeCidadeOrigem)).getValor();
                        Cidade cidadeDestino = cidades.findVertice(new Cidade(nomeCidadeDestino)).getValor();
                        AcrescentarRota(cidadeOrigem, cidadeDestino, distancia);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            System.out.println("Não existe um arquivo de entrada.txt");
        }
    }

    public void EscreverGrafoEmArquivo(Grafo<Cidade> grafoImprimir, String caminhoNomeArquivoGrafo){
        try{

            FileWriter f = new FileWriter(caminhoNomeArquivoGrafo);
            BufferedWriter b = new BufferedWriter(f);

            //Ecrever número de Cidades
            int numeroCidades = grafoImprimir.getVertices().size();
            b.write(numeroCidades + "\n");

            //Escrever Nome das Cidades
            for (Vertice vertice : grafoImprimir.getVertices()){
                b.write(vertice.toString() + "\n");
            }

            //Escrever Matriz de Adjacências
            boolean caminhoEncontrado = false;
            for (Vertice vertice : grafoImprimir.getVertices()) {
                StringBuilder linha = new StringBuilder();
                for (Vertice verticeVerificarAdjacencia : grafoImprimir.getVertices()) {
                    for (Aresta arestaDestinoEPeso : (ArrayList<Aresta>) vertice.getDestinos()) {
                        if (arestaDestinoEPeso.getDestino().equals(verticeVerificarAdjacencia)) {
                            linha.append(arestaDestinoEPeso.getPeso()).append(",");
                            caminhoEncontrado = true;
                            break;
                        }
                    }
                    if (!caminhoEncontrado) {
                        linha.append("0.0,");
                    }
                    caminhoEncontrado = false;
                }
                // Remover a última vírgula
                linha.setLength(linha.length() - 1);
                b.write(linha.toString() + "\n");
            }

            b.close();
        }
        catch (IOException e) {
            System.out.println("ERRO! Não foi possível escrever o arquivo.");
        }

    }

    public void GravarSair () {
        //Escrever Arquivo com Grafo Completo
        EscreverGrafoEmArquivo(this.cidades, "grafocompleto.txt");
        //Escrever Arquivo com Árvore Geradora Mínima
        EscreverGrafoEmArquivo(this.cidades.CalcAgmPrim(), "agm.txt");
    }


    //Método para exibir menu
    public void menu() {

        if(!this.arquivoCarregado){
            //Lendo arquivo entrada.txt, se existir
            LerArquivoEntrada();
            this.arquivoCarregado = true; //não será lido novamente
        }

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

