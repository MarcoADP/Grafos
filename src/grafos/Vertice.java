package grafos;

import java.util.ArrayList;

public class Vertice implements Comparable<Vertice>{
    public int numero;
    public String nome;
    public int cor;
    public int distancia;
    public int tempoDescoberto;
    public int tempoTermino;
    public boolean visitado;
    public Vertice pred;
    public ArrayList<Aresta> listaAdjacencia;
    
    public Vertice(int numero, String nome) {
        this.listaAdjacencia = new ArrayList<>();
        this.numero = numero;
        this.nome = nome;
        this.cor = 0;
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
