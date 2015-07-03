
package grafos;

import java.io.IOException;

public class Execucao {
    public static void main(String[] args) throws IOException {
        Grafos grafo = Leitura.leitura();
        //grafo.mostraVertice();
        //grafo.mostraAresta();
        //grafo.matrizAdjacencia = grafo.criaMatrizAdjacencia();
        //grafo.mostraMatriz();
        Algoritmos.busca_largura(grafo, grafo.vertice.get(grafo.posicaoVertice("5")));
        Algoritmos.mostraVertLargura(grafo);
    }
}
