package grafos;

import java.util.ArrayList;
import java.util.Arrays;

public class Grafo {
    
    public ArrayList<Vertice> listaVertice;
    public ArrayList<Aresta> listaAresta;
    public int[][] matrizAdjacencia;
    
    public Grafo() {
        this.listaAresta = new ArrayList<>();
        this.listaVertice = new ArrayList<>();
    }
    
    public Vertice temVertice(String nome){
        for(Vertice vert : this.listaVertice){
            if(vert.nome.equals(nome)){
                return vert;
            }
        }
        return null;
    }
    
    public int posicaoVertice(String nome){
        int i;
        for(i = 0; i < this.listaVertice.size(); i++){
           if(this.listaVertice.get(i).nome.equals(nome)){
               return i;
           }
        }
        return -1;
    }
    
    public void mostraVertice(){
        System.out.println("\nVertices:");
        for(Vertice vert : this.listaVertice){
           System.out.println(vert.nome + " " + vert.rotulo);
        }
    }

    public void mostraAresta(){
        System.out.println("\nArestas:");
        for(Aresta aresta : this.listaAresta){
           System.out.println(aresta.origem.nome + " " + aresta.destino.nome + " " + aresta.peso);
        }
    }
    
    public void criaMatrizAdjacencia(){
        this.matrizAdjacencia = new int[this.listaVertice.size()][this.listaVertice.size()];
        for(Aresta aresta : this.listaAresta){
            int linha = Integer.parseInt(aresta.origem.nome);
            int coluna = Integer.parseInt(aresta.destino.nome);

            this.matrizAdjacencia[linha - 1][coluna - 1] = aresta.peso;
        }
    }
    
    public void mostraMatrizAdjacencia(){
        int i;
        System.out.println("\nMatriz de Adjacencia");
        for(i = 0; i < this.listaVertice.size(); i++){
            System.out.println(Arrays.toString(this.matrizAdjacencia[i]));
        }
    }

    public void criaListaAdjacencia(){
        for(Aresta aresta : this.listaAresta){
            aresta.origem.listaAdjacencia.add(aresta);
        }
    }
    
    public void mostraListaAdjacencia(){
        System.out.println("\nLista de Adjacência");
        for(Vertice vertice : this.listaVertice){
            vertice.mostraAdjacentes();
            System.out.println();
        }
    }
}
