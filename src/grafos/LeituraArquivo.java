package grafos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class LeituraArquivo {
    
    public static Grafo lerArquivo(String nomeArquivo) throws FileNotFoundException, IOException{
        Grafo grafo = new Grafo();
        
        FileInputStream stream = new FileInputStream(nomeArquivo);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        
        String linha = br.readLine();
        while(linha != null && !linha.equals("#")) {
           String strNumero = linha.substring(0, linha.indexOf(' '));
           String nome = linha.substring(linha.lastIndexOf(' ') + 1, linha.length());
           int numero = Integer.parseInt(strNumero);
           
           Vertice vert = new Vertice(numero, nome);
           grafo.listaVertice.add(vert);
           linha = br.readLine();
        }

        linha = br.readLine();
        while(linha != null) {
            int peso;
            String strOrigem = linha.substring(0, linha.indexOf(' '));
            String strDestino;
            try{
                strDestino = linha.substring(linha.indexOf(' ') + 1, linha.lastIndexOf(' '));
                String strPeso = linha.substring(linha.lastIndexOf(' ') + 1, linha.length()); 
                peso = Integer.parseInt(strPeso);
            } catch (Exception e){
                strDestino = linha.substring(linha.lastIndexOf(' ') + 1, linha.length());
                peso = 1;
            }
            
            int origem = Integer.parseInt(strOrigem);
            int destino = Integer.parseInt(strDestino);
            Vertice vertOrigem = grafo.getVertice(origem);
            Vertice vertDestino = grafo.getVertice(destino);
            if(vertOrigem != null && vertDestino != null){
                Aresta aresta = new Aresta(vertOrigem, vertDestino, peso);
                grafo.addAresta(aresta);
            }
            linha = br.readLine();
        }

        grafo.criaMatrizAdjacencia();
        return grafo;
    }
}
