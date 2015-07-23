package algoritmos;

import grafos.Grafo;
import static grafos.Grafo.INFINITO;
import grafos.Vertice;

public class ArvoreGeradora {
    public Vertice pred[];
    public int distancia[];
    public Grafo grafo;
    
    public void mostraInfo(){
        System.out.println("\n\nEXECUÇÃO DO ALGORITMO:");
        for (Vertice v : this.grafo.listaVertice) {
            System.out.println("\nVertice: " + v.nome);
            if (pred[v.id] != null) {
                System.out.println("Predecessor: " + pred[v.id].nome);
            } else {
                System.out.println("Predecessor: NULL");
            }

            if (distancia[v.id] == INFINITO) {
                System.out.println("Distancia: INFINITO");
            } else {
                System.out.println("Distancia: " + distancia[v.id]);
            }
        }
    }
    
    private void inicializaVertices(Grafo grafo, Vertice s){        
        for (Vertice v : grafo.listaVertice) {
            distancia[v.id] = INFINITO;
            pred[v.id] = null;
        }
        distancia[s.id] = 0;
    }
    
    public void menorCaminho(Grafo grafo, Vertice origem, Vertice destino) {
        if (origem.nome.equals(destino.nome)) {
            System.out.print(destino.nome);
        } else {
            if (pred[destino.id] == null) {
                System.out.println("\nNão existe caminho");
            } else {
                menorCaminho(grafo, origem, pred[destino.id]);
                System.out.print(" -> " + destino.nome);
            }
        }
    }
}
