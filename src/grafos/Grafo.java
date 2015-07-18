package grafos;

import java.util.ArrayList;
import java.util.Arrays;

public class Grafo {
    
    public ArrayList<Vertice> listaVertice;
    public ArrayList<Aresta> listaAresta;
    public int[][] matrizAdjacencia;
    
    public static final int INFINITO = Integer.MAX_VALUE;
    
    public Grafo() {
        this.listaAresta = new ArrayList<>();
        this.listaVertice = new ArrayList<>();
    }
    
    public Vertice getVertice(int id){
        return this.listaVertice.get(id);
    }
    
    public Vertice getVertice(String nome){
        int i;
        for(i = 0; i < this.listaVertice.size(); i++){
           if(this.listaVertice.get(i).nome.equals(nome))
               return getVertice(i);           
        }
        return null;
    }
    
    public void addAresta(Aresta a){
        this.listaAresta.add(a);
        this.getVertice(a.origem.id).listaAdjacencia.add(a);
    }
    
    public int numVert(){
        return this.listaVertice.size();
    }    
    
    public void mostraVertice(){
        System.out.println("\nVertices:");
        for(Vertice vert : this.listaVertice){
           System.out.println(vert.id + " " + vert.nome);
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
            int linha = aresta.origem.id;
            int coluna = aresta.destino.id;

            this.matrizAdjacencia[linha][coluna] = aresta.peso;
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
    
    public Grafo transposto(){        
        Grafo transposto = new Grafo();
        for (Vertice v : this.listaVertice)
            transposto.listaVertice.add(new Vertice(v.id, v.nome));
        for (Aresta a : this.listaAresta){
            Vertice origem = transposto.getVertice(a.destino.id);
            Vertice destino = transposto.getVertice(a.origem.id); 
            transposto.addAresta(new Aresta(origem, destino, a.peso));
        }
        
        transposto.criaMatrizAdjacencia();
        return transposto;  
    }
}
