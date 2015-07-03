package grafos;

import java.util.ArrayList;
import java.util.Arrays;

public class Grafos {
    
    ArrayList<Vertice> vertice;
    ArrayList<Aresta> aresta;
    int[][] matrizAdjacencia;
    
    
    public Grafos() {
        this.aresta = new ArrayList<>();
        this.vertice = new ArrayList<>();
    
    }
    
    public Vertice temVertice(String nome){
        for(Vertice vert : this.vertice){
            if(vert.nome.equals(nome)){
                return vert;
            }
        }
        return null;
    }
    
    public int posicaoVertice(String nome){
        int i;
        for(i = 0; i < this.vertice.size(); i++){
           if(this.vertice.get(i).nome.equals(nome)){
               return i;
           }
        }
        return -1;
    }
    
    public void mostraVertice(){
        System.out.println("\nVertices:");
        for(Vertice vert : this.vertice){
           System.out.println(vert.nome + " " + vert.rotulo);

        }
    }

    public void mostraAresta(){
        System.out.println("\nArestas:");
        for(Aresta aresta : this.aresta){
           System.out.println(aresta.origem.nome + " " + aresta.destino.nome + " " + aresta.peso);
        }
    }
    
    public int[][] criaMatrizAdjacencia(){
        int[][] matriz = new int[this.vertice.size()][this.vertice.size()];
        for(Aresta aresta : this.aresta){
            int linha = Integer.parseInt(aresta.origem.nome);
            int coluna = Integer.parseInt(aresta.destino.nome);

            matriz[linha - 1][coluna - 1] = 1;
        }
        return matriz;
    }
    
    public void mostraMatriz(){
        int i;
        System.out.println("\nMatriz de Adjacencia");
        for(i = 0; i < this.vertice.size(); i++){
            System.out.println(Arrays.toString(this.matrizAdjacencia[i]));
        }
    }
}
