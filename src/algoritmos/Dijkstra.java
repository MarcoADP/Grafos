package algoritmos;

import grafos.Aresta;
import grafos.Grafo;
import static grafos.Grafo.INFINITO;
import grafos.Vertice;
import java.util.Comparator;
import java.util.PriorityQueue;

public final class Dijkstra extends ArvoreGeradora{

    public boolean visitado[];
    
    public Dijkstra(Grafo grafo) {
        this.grafo = grafo;
        this.pred = new Vertice[grafo.numVert()];
        this.distancia = new int[grafo.numVert()];
        this.visitado = new boolean[grafo.numVert()];
    }
    
    public void dijkstra(Vertice s) {
        inicializaVertices(grafo, s);        
        PriorityQueue<Vertice> filaP = new PriorityQueue<>(new ComparadorDist());
        
        filaP.add(s);

        while (!filaP.isEmpty()) {
            Vertice u = filaP.poll();
            if (!visitado[u.id]){
                visitado[u.id] = true;
                for (Aresta a : u.listaAdjacencia) {
                    Vertice v = a.destino;
                    if (relaxa(u, v, a.peso))
                        filaP.add(v);                    
                }
            }
        }
    }
    
    private void inicializaVertices(Grafo grafo, Vertice s) {
        for (Vertice v : grafo.listaVertice) {
            distancia[v.id] = INFINITO;
            pred[v.id] = null;
            visitado[v.id] = false;
        }
        distancia[s.id] = 0;
    }

    private boolean relaxa(Vertice u, Vertice v, int peso) {
        if (distancia[v.id] > (double)distancia[u.id] + peso) {
            distancia[v.id] = distancia[u.id] + peso;
            pred[v.id] = u;
            return true;
        }
        return false;
    }

    class ComparadorDist implements Comparator<Vertice>{
        @Override
        public int compare(Vertice v, Vertice u) {
            return distancia[v.id] - distancia[u.id];
        }        
    }
}
