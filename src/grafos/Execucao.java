
package grafos;

import java.io.IOException;

public class Execucao {
    public static void main(String[] args) throws IOException {
        Grafo grafo = LeituraArquivo.lerArquivo("exemplo1.tgf"); //exemplo do slide 15 do PDF 2_aula3.pdf
        //Grafo grafo = LeituraArquivo.lerArquivo("exemplo2.tgf");
        //Grafo grafo = LeituraArquivo.lerArquivo("exemplo3.tgf"); //exemplo do slide 28 do PDF 4_aula5.pdf
        //Grafo grafo = LeituraArquivo.lerArquivo("exemplo4.tgf");
        
        grafo.mostraListaAdjacencia();
        grafo.mostraMatrizAdjacencia();
        
        int indice = grafo.posicaoVertice("5"); //função para retornar a posicao do vertice 
        
        //Algoritmos.buscaEmLargura(grafo, grafo.listaVertice.get(0));
        //grafo.mostraVertLargura();
        //Algoritmos.menorCaminho(grafo, grafo.listaVertice.get(0), grafo.listaVertice.get(5));
        
        //Algoritmos.resetaVertices(grafo);
        //Algoritmos.buscaEmProfundidade(grafo);
        //grafo.mostraVertProfundidade();
        //Algoritmos.mostraOrdemTopologica();
        
        /*Algoritmos.dijkstra(grafo, grafo.listaVertice.get(0));
        grafo.mostraGrafo();
        System.out.println("\n\n\n");
        
        Algoritmos.bellmanFord(grafo, grafo.listaVertice.get(0));
        grafo.mostraGrafo();*/
        
        //Algoritmos.componentesConexos(grafo);
        //Algoritmos.componenteFortementeConexo(grafo);
        //Algoritmos.mostraSCC();
        
        int mat[][] = Algoritmos.floydWarshall(grafo);
        /*System.out.println("Matriz Dn: \n");
        for(int i = 0; i < grafo.listaVertice.size(); i++){
            System.out.println(" ");
            for(int j = 0; j < grafo.listaVertice.size(); j++){
                System.out.println(mat[i][j] + " ");
            }
        }*/
        
    }
}
