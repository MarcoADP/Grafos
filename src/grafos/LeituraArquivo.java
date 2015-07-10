package grafos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class LeituraArquivo {
    
    public static Grafo lerArquivo(String nomeArquivo) throws FileNotFoundException, IOException{
        Grafo grafo = new Grafo();
        
        //System.out.println("Digite o nome do arquivo: ");
        //Scanner entrada = new Scanner(System.in);
        
        //String arquivo;
        //arquivo = entrada.nextLine();
        
        FileInputStream stream = new FileInputStream(nomeArquivo);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        //System.out.println("\nVertices: ");
        while(linha != null) {
           String nome = linha.substring(0, linha.indexOf(' '));
           String rotulo = linha.substring(linha.lastIndexOf(' ') + 1, linha.length());
           //System.out.println(nome + " " + rotulo);
           
           Vertice vert = new Vertice(nome, rotulo);           
           grafo.listaVertice.add(vert);
           linha = br.readLine();
           if(linha.equals("#")){
               break;
           }
        }
        //System.out.println("\nArestas:");
        linha = br.readLine();
        while(linha != null) {
            int peso;
            String origem = linha.substring(0, linha.indexOf(' '));
            String destino;
            try{
                destino = linha.substring(linha.indexOf(' ') + 1, linha.lastIndexOf(' '));
                String strPeso = linha.substring(linha.lastIndexOf(' ') + 1, linha.length()); 
                peso = Integer.parseInt(strPeso);
            } catch (Exception e){
                destino = linha.substring(linha.lastIndexOf(' ') + 1, linha.length());
                peso = 1;
            }
            //System.out.println(origem + " " + destino + " " + peso);
            Vertice vertOrigem = grafo.temVertice(origem);
            Vertice vertDestino = grafo.temVertice(destino);
            if(vertOrigem != null && vertDestino != null){
                Aresta aresta = new Aresta(vertOrigem, vertDestino, peso);
                grafo.listaAresta.add(aresta);
            }
            linha = br.readLine();
        }
        grafo.criaListaAdjacencia();
        grafo.criaMatrizAdjacencia();
        return grafo;
    }
}
