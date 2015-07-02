package grafos;

import java.util.ArrayList;

public class Grafos {
    
    ArrayList<Vertice> vertice;
    ArrayList<Aresta> aresta;

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
    
    
}
