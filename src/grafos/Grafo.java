package grafos;

import static grafos.Algoritmos.INFINITO;
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
    
    public Vertice getVertice(int numero){
        return this.listaVertice.get(numero - 1);
    }
    
    public void addAresta(Aresta a){
        this.listaAresta.add(a);
        this.getVertice(a.origem.numero).listaAdjacencia.add(a);
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
           System.out.println(vert.numero + " " + vert.nome);
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
            int linha = aresta.origem.numero;
            int coluna = aresta.destino.numero;

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
    
    public Grafo transposto(){        
        Grafo transposto = new Grafo();
        for (Vertice v : this.listaVertice)
            transposto.listaVertice.add(new Vertice(v.numero, v.nome));
        for (Aresta a : this.listaAresta){
            Vertice origem = transposto.getVertice(a.destino.numero);
            Vertice destino = transposto.getVertice(a.origem.numero); 
            transposto.addAresta(new Aresta(origem, destino, a.peso));
        }
        
        transposto.criaMatrizAdjacencia();
        return transposto;  
    }
    
    public void mostraGrafo() {
        for (Vertice vertice : this.listaVertice) {
            System.out.println("\nVertice: " + vertice.nome);
            if (vertice.pred != null) {
                System.out.println("Predecessor: " + vertice.pred.nome);
            } else {
                System.out.println("Predecessor: NULL");
            }

            if (vertice.distancia == INFINITO) {
                System.out.println("Distancia: INFINITO");
            } else {
                System.out.println("Distancia: " + vertice.distancia);
            }
        }
    }
    
    public void mostraVertLargura() {
        for (Vertice vertice : this.listaVertice) {
            System.out.println("\n\nVertice: " + vertice.nome);
            if (vertice.pred != null) {
                System.out.println("Predecessor: " + vertice.pred.nome);
            } else {
                System.out.println("Predecessor: NULL");
            }
            System.out.println("Distancia: " + vertice.distancia);
        }
    }
    
    public void mostraVertProfundidade() {
        for (Vertice vertice : this.listaVertice) {
            System.out.println("\n\nVertice: " + vertice.nome);
            System.out.println("Tempo de descoberta: " + vertice.tempoDescoberto);
            System.out.println("Tempo de término: " + vertice.tempoTermino);
            try {
                System.out.println("Predecessor: " + vertice.pred.nome);
            } catch (Exception e) {
                System.out.println("Predecessor: NULL");
            }
        }
    }
}
