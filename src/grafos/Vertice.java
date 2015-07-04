package grafos;

import java.util.ArrayList;


public class Vertice {
    String nome;
    String rotulo;
    int estado;
    int distancia;
    int tempoDescoberto;
    int tempoTermino;
    Vertice pred;
    ArrayList<Vertice> listaAdjacencia;
    
    public Vertice(String nome, String rotulo) {
        this.listaAdjacencia = new ArrayList<>();
        this.nome = nome;
        this.rotulo = rotulo;
        estado = 0;
        distancia = 0;
        pred = null;
    }
    
    public void mostraListaAdjacencia(){
        int i;
        System.out.println("Vertices Adjacentes de " + this.nome + ": ");
        for(i = 0; i < this.listaAdjacencia.size(); i++){
            System.out.println(this.listaAdjacencia.get(i).nome);
        }
    }
}
