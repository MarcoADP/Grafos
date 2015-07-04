
package grafos;

import java.io.IOException;

public class Execucao {
    public static void main(String[] args) throws IOException {
        Grafo grafo = LeituraArquivo.lerArquivo("exemplo3.tgf");
        grafo.mostraListaAdjacencia();
        grafo.mostraMatrizAdjacencia();
        //grafo.mostraVertice();
        //grafo.mostraAresta();
        
        int indice = grafo.posicaoVertice("5"); //função para retornar a posicao do vertice 
        
        //Algoritmos.busca_largura(grafo, grafo.listaVertice.get(0));
        //Algoritmos.mostraVertLargura(grafo);
        //Algoritmos.menorCaminho(grafo, grafo.listaVertice.get(0), grafo.listaVertice.get(5));
        
        Algoritmos.resetaVertices(grafo);
        Algoritmos.inicializa_busca_profundidade(grafo);
        Algoritmos.mostraVertProfundidade(grafo);
        Algoritmos.mostraOrdemTopologica();
        
        //Algoritmos.dijkstra(grafo, grafo.listaVertice.get(0));
        //Algoritmos.mostraDijkstra(grafo);
    }
}
