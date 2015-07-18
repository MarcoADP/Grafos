package algoritmos;

import grafos.Aresta;
import grafos.Grafo;
import static grafos.Grafo.INFINITO;
import grafos.Vertice;
import java.util.LinkedList;
import java.util.Queue;

public final class BuscaEmLargura extends ArvoreGeradora{
    
    public static final int BRANCO = 0;
    public static final int CINZA = 1;
    public static final int PRETO = -1;
    
    public int cor[];

    public BuscaEmLargura(Grafo grafo) {
        this.grafo = grafo;
        this.pred = new Vertice[grafo.numVert()];
        this.distancia = new int[grafo.numVert()];
        this.cor = new int[grafo.numVert()];
    }
    
    public void buscaEmLargura(Vertice s) {
        inicializaVertices(grafo, s);        
        Queue<Vertice> fila = new LinkedList<>();  
        
        fila.add(s);

        while (!fila.isEmpty()) {
            Vertice u = fila.remove();
            for (Aresta a : u.listaAdjacencia) {
                Vertice v = a.destino;
                if (cor[v.id] == BRANCO) {
                    cor[v.id] = CINZA;
                    distancia[v.id] = distancia[u.id] + 1;
                    pred[v.id] = u;
                    fila.add(v);
                }
            }
            cor[u.id] = PRETO;
        }
    }
    
    private void inicializaVertices(Grafo grafo, Vertice s) {
        for (Vertice v : grafo.listaVertice) {
            distancia[v.id] = INFINITO;
            pred[v.id] = null;
            cor[v.id] = BRANCO;
        }
        distancia[s.id] = 0;
        cor[s.id] = CINZA;
        pred[s.id] = null;
    }
}
