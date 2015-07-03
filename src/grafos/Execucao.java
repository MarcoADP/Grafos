
package grafos;

import java.io.IOException;

public class Execucao {
    public static void main(String[] args) throws IOException {
        Grafos grafo = Leitura.leitura();
        //grafo.mostraVertice();
        //grafo.mostraAresta();
        //grafo.matrizAdjacencia = grafo.criaMatrizAdjacencia();
        //grafo.mostraMatriz();
        int indice = grafo.posicaoVertice("5"); //função para retornar a posicao do vertice 
        //Algoritmos.busca_largura(grafo, grafo.vertice.get(0));
        //Algoritmos.mostraVertLargura(grafo);
        //Algoritmos.menorCaminho(grafo, grafo.vertice.get(0), grafo.vertice.get(0));
        //Algoritmos.resetaVertices(grafo);
        Algoritmos.inicializa_busca_profundidade(grafo);
        Algoritmos.mostraVertProfundidade(grafo);
    }
}
