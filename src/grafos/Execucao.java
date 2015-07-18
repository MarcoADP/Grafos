package grafos;

import algoritmos.*;
import java.io.IOException;

public class Execucao {
    public static void main(String[] args) throws IOException {
        Grafo grafo;
        
        System.out.println("\n\n\nBUSCA EM LARGURA: ");
        grafo = LeituraArquivo.lerArquivo("exemplo1.tgf"); //exemplo do slide 15 do PDF 2_aula3.pdf
        grafo.mostraListaAdjacencia();
        grafo.mostraMatrizAdjacencia();
                
        BuscaEmLargura bfs = new BuscaEmLargura(grafo);
        bfs.buscaEmLargura(grafo.getVertice(0));
        bfs.mostraInfo();
        
        
        System.out.println("-----------------------------------------");
        System.out.println("\n\n\nBUSCA EM PROFUNDIDADE: ");
        grafo = LeituraArquivo.lerArquivo("exemplo2.tgf"); //exemplo do slide 28 do PDF 4_aula5.pdf
        grafo.mostraListaAdjacencia();
        grafo.mostraMatrizAdjacencia();
                
        BuscaEmProfundidade dfs = new BuscaEmProfundidade(grafo);
        dfs.buscaEmProfundidade();
        dfs.mostraInfo();
        
        dfs.mostraOrdemTopologica();
        
        
        System.out.println("\nCOMPONENTES FORTEMENTE CONEXOS: ");        
        ComponentesFortementeConexos scc = new ComponentesFortementeConexos(grafo);
        scc.componenteFortementeConexo();
        scc.mostraSCC();
        
        
        System.out.println("-----------------------------------------");
        System.out.println("\n\n\nDIJKSTRA: "); 
        grafo = LeituraArquivo.lerArquivo("exemplo3.tgf"); //exemplo do slide 11 do PDF aula10.pdf
        grafo.mostraListaAdjacencia();
        grafo.mostraMatrizAdjacencia();
                       
        Dijkstra dij = new Dijkstra(grafo);
        dij.dijkstra(grafo.getVertice("s"));
        dij.mostraInfo();
        
        
        System.out.println("-----------------------------------------");
        System.out.println("\n\n\nBELLMAN-FORD: "); 
        grafo = LeituraArquivo.lerArquivo("exemplo4.tgf"); //exemplo do slide 14 do PDF aula11.pdf
        grafo.mostraListaAdjacencia();
        grafo.mostraMatrizAdjacencia();
              
        BellmanFord bFord = new BellmanFord(grafo);
        bFord.bellmanFord(grafo.getVertice("s"));
        bFord.mostraInfo();
    }
}
