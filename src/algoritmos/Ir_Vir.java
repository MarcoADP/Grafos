package algoritmos;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Ir_Vir {
    public Grafo leitura() throws IOException{
        System.out.println("\n\nProblema Ir e Vir\n");
        Grafo grafo = null;
        
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(entrada);
        
        String linha = buffer.readLine();
        int nVert = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
        int nAresta = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
        while(nVert != 0 && nAresta != 0){
            grafo = new Grafo();
            for (int i = 0; i < nVert; i++)
                grafo.listaVertice.add(new Vertice(i, String.valueOf(i+1)));
            
            for (int i = 0; i < nAresta; i++) {
                linha = buffer.readLine();
                int origem = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
                int destino = Integer.parseInt(linha.substring(linha.indexOf(' ') + 1, linha.lastIndexOf(' ')));
                int tipo = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
                Vertice vertOrigem = grafo.getVertice(origem - 1);
                Vertice vertDestino = grafo.getVertice(destino - 1);
                
                grafo.addAresta(new Aresta(vertOrigem, vertDestino, 1));
                if (tipo == 2)
                    grafo.addAresta(new Aresta(vertDestino, vertOrigem, 1));
            }
            
            ComponentesFortementeConexos scc = new ComponentesFortementeConexos(grafo);
            scc.componenteFortementeConexo();
            if (scc.componentes.size() == 1)
                System.out.println("1");
            else
                System.out.println("0");
            
            linha = buffer.readLine();
            nVert = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
            nAresta = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
        }
        
        
        return grafo;
    }   
}
