package grafos;

import java.util.ArrayList;

public class Vertice{
    public int id;
    public String nome;
    public ArrayList<Aresta> listaAdjacencia;
    
    public Vertice(int id, String nome) {
        this.listaAdjacencia = new ArrayList<>();
        this.id = id;
        this.nome = nome;
    }
    
    public void mostraAdjacentes(){
        System.out.print("[" + this.nome + "]");
        for (Aresta u : this.listaAdjacencia) 
            System.out.print(" -> [" + u.destino.nome + "]"); 
    }    
}
