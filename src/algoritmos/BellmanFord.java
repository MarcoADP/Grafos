package algoritmos;

import grafos.Aresta;
import grafos.Grafo;
import static grafos.Grafo.INFINITO;
import grafos.Vertice;

public final class BellmanFord extends ArvoreGeradora{
    
    public boolean temCicloNegativo;
    
    public BellmanFord(Grafo grafo) {
        this.grafo = grafo;
        this.pred = new Vertice[grafo.numVert()];
        this.distancia = new int[grafo.numVert()];
        this.temCicloNegativo = false;
    }
    
    public boolean bellmanFord(Vertice s){
        inicializaVertices(grafo, s);
        for (int i = 0; i < grafo.listaVertice.size() - 1; i++) {
            for (Aresta a : grafo.listaAresta) {
                Vertice u = a.origem;
                Vertice v = a.destino;
                int peso = a.peso;

                relaxa(u, v, peso);
            }
        }
        for (Aresta a : grafo.listaAresta) {
            if (distancia[a.destino.id] > distancia[a.origem.id] + a.peso){
                temCicloNegativo = true;
                return false;
            }
        }
        return true;        
    }
    
    private void inicializaVertices(Grafo grafo, Vertice s) {
        for (Vertice v : grafo.listaVertice) {
            distancia[v.id] = INFINITO;
            pred[v.id] = null;
        }
        distancia[s.id] = 0;
    }

    private void relaxa(Vertice u, Vertice v, int peso) {
        if (distancia[v.id] > (double)distancia[u.id] + peso) {
            distancia[v.id] = distancia[u.id] + peso;
            pred[v.id] = u;
        }
    }    
}
