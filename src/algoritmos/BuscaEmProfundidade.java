package algoritmos;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;

public final class BuscaEmProfundidade extends ArvoreGeradora{
    
    public static final int BRANCO = 0;
    public static final int CINZA = 1;
    public static final int PRETO = -1;
    
    ArrayList<Vertice> ordemTopologica;
    public int tempo;
    public int tempoDescoberto[];
    public int tempoTermino[];
    public int cor[];

    public BuscaEmProfundidade(Grafo grafo) {
        this.grafo = grafo;
        this.pred = new Vertice[grafo.numVert()];
        this.distancia = new int[grafo.numVert()];
        this.cor = new int[grafo.numVert()];
        this.tempoDescoberto = new int[grafo.numVert()];
        this.tempoTermino = new int[grafo.numVert()];
        ordemTopologica = new ArrayList<>();        
    }
    
    public void buscaEmProfundidade() {
        inicializaVertices(grafo);
        tempo = 0;
        for (Vertice u : grafo.listaVertice) {
            if (cor[u.id] == BRANCO)
                dfsVisit(u);
        }
    }
    
    public void buscaEmProfundidade(ArrayList<Vertice> ordem) {
        inicializaVertices(grafo);
        tempo = 0;
        for (Vertice v : ordem) {
            Vertice u = grafo.getVertice(v.id);
            if (cor[u.id] == BRANCO)
                dfsVisit(u);
        }
    }
    
    public void dfsVisit(Vertice u) {
        tempo++;
        cor[u.id] = CINZA;
        tempoDescoberto[u.id] = tempo;

        for (Aresta a : u.listaAdjacencia) {
            Vertice v = a.destino;
            if (cor[v.id] == BRANCO) {
                pred[v.id] = u;
                dfsVisit(v);
            }
        }            
        
        cor[u.id] = PRETO;
        tempo++;
        tempoTermino[u.id] = tempo;
        ordemTopologica.add(0, u);
    }
    
    private void inicializaVertices(Grafo grafo) {
        for (Vertice v : grafo.listaVertice) {
            pred[v.id] = null;
            cor[v.id] = BRANCO;            
        }
    }
    
    @Override
    public void mostraInfo() {
        for (Vertice v : grafo.listaVertice) {
            System.out.println("\nVertice: " + v.nome);
            System.out.println("Tempo de descoberta: " + tempoDescoberto[v.id]);
            System.out.println("Tempo de término: " + tempoTermino[v.id]);
            if (pred[v.id] != null) {
                System.out.println("Predecessor: " + pred[v.id].nome);
            } else {
                System.out.println("Predecessor: NULL");
            }
        }
    }
    
    public void mostraOrdemTopologica() {
        System.out.print("\nOrdem Topológica: ");
        for (Vertice v : this.ordemTopologica) {
            System.out.print(v.nome + " ");
        }
        System.out.println("\n");
    }    
}
