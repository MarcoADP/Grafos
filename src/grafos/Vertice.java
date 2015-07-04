package grafos;

import java.util.ArrayList;


public class Vertice implements Comparable<Vertice>{
    public String nome;
    public String rotulo;
    public int estado;
    public int distancia;
    public int tempoDescoberto;
    public int tempoTermino;
    public Vertice pred;
    public ArrayList<Aresta> listaAdjacencia;
    
    public Vertice(String nome, String rotulo) {
        this.listaAdjacencia = new ArrayList<>();
        this.nome = nome;
        this.rotulo = rotulo;
        this.estado = 0;
        this.distancia = 0;
        this.pred = null;
    }
    
    public void mostraAdjacentes(){
            System.out.print("[" + this.nome + "]");
            for (Aresta u : this.listaAdjacencia) 
                System.out.print(" -> [" + u.destino.nome + "]"); 
    }
    
    @Override
    public int compareTo(Vertice outro){
        return this.distancia - outro.distancia;
    }
}
