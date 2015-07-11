package grafos;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Algoritmos {

    public static final int BRANCO = 0;
    public static final int CINZA = 1;
    public static final int PRETO = -1;
    public static final int INFINITO = Integer.MAX_VALUE;

    public static int tempo;

    public static ArrayList<Vertice> ordemTopologica;

    public static void resetaVertices(Grafo grafo) {
        for (Vertice vert : grafo.listaVertice) {
            vert.tempoDescoberto = 0;
            vert.tempoTermino = 0;
            vert.distancia = 0;
            vert.pred = null;
            vert.estado = BRANCO;
        }
    }

    public static void busca_largura(Grafo grafo, Vertice vert) {
        //0 Não descoberto (Branco)
        //1 Descoberto e na fila (Cinza)
        //-1 Todos vizinhos descobertos (Preto)
        FilaVert fila = new FilaVert();
        vert.estado = CINZA;
        fila.insere(vert);

        while (!fila.vazia()) {
            Vertice u = fila.remove();
            for (Aresta a : u.listaAdjacencia) {
                Vertice v = a.destino;
                if (v.estado == BRANCO) {
                    v.estado = CINZA;
                    v.distancia = u.distancia + 1;
                    v.pred = u;
                    fila.insere(v);
                }
            }
            u.estado = PRETO;
        }
        /*
         Queue<Vertice> fila = new LinkedList<Vertice>();
         fila.add(vert);
         Vertice u;
         while(!fila.isEmpty()){
         u = fila.remove();
         for (Vertice v : u.listaAdjacencia) {
         if(v.estado == BRANCO){
         v.setEstado(CINZA);
         v.setDistancia(u.distancia + 1);
         v.setPred(u);
         fila.add(v);
         }
         }
         u.setEstado(PRETO);
         }
         */
    }

    public static void menorCaminho(Grafo grafo, Vertice origem, Vertice destino) {
        if (origem.nome.equals(destino.nome)) {
            System.out.println("\n" + destino.nome);
        } else {
            if (destino.pred == null) {
                System.out.println("\nNão existe caminho");
            } else {
                menorCaminho(grafo, origem, destino.pred);
                System.out.println(destino.nome);
            }
        }
    }

    /* Algoritmo de busca em profundidade e algoritmo ordenação Topológica */
    public static void inicializa_busca_profundidade(Grafo grafo) {
        ordemTopologica = new ArrayList<>();
        tempo = 0;
        for (Vertice vertice : grafo.listaVertice) {
            if (vertice.estado == BRANCO) {
                busca_profundidade(vertice);
            }
        }
    }

    public static void busca_profundidade(Vertice vertice) {
        tempo++;
        vertice.estado = CINZA;
        vertice.tempoDescoberto = tempo;
        int i;
        for (i = 0; i < vertice.listaAdjacencia.size(); i++) {
            Vertice vertAuxiliar = vertice.listaAdjacencia.get(i).destino;
            if (vertAuxiliar.estado == BRANCO) {
                vertAuxiliar.pred = vertice;
                busca_profundidade(vertAuxiliar);
            }
        }
        vertice.estado = PRETO;
        tempo++;
        vertice.tempoTermino = tempo;
        ordemTopologica.add(0, vertice);
    }

    public static void mostraOrdemTopologica() {
        System.out.print("\nOrdem Topológica: ");
        for (Vertice v : ordemTopologica) {
            System.out.print(v.nome + " ");
        }
    }

    /* Algoritmo de Dijkstra */
    private static void inicializaVertices(Grafo grafo, Vertice s) {
        for (Vertice v : grafo.listaVertice) {
            v.distancia = INFINITO;
            v.pred = null;
        }
        s.distancia = 0;
    }

    private static void relaxa(Vertice u, Vertice v, int peso) {
        if (v.distancia > u.distancia + peso) {
            v.distancia = u.distancia + peso;
            v.pred = u;
        }
    }

    public static void dijkstra(Grafo grafo, Vertice s) {
        inicializaVertices(grafo, s);
        PriorityQueue<Vertice> filaP = new PriorityQueue<>();
        filaP.add(s);

        //for (Vertice v : grafo.listaVertice)
        //    filaP.add(v);
        while (!filaP.isEmpty()) {
            Vertice u = filaP.poll();
            for (Aresta a : u.listaAdjacencia) {
                Vertice v = a.destino;
                if (v.distancia > u.distancia + a.peso) {
                    filaP.remove(v);
                    v.distancia = u.distancia + a.peso;
                    v.pred = u;
                    filaP.add(v);
                }
            }
        }
    }



    public static boolean bellmanFord(Grafo grafo, Vertice s) {
        inicializaVertices(grafo, s);
        int i;
        for (i = 0; i < grafo.listaVertice.size() - 1; i++) {
            for (Aresta a : grafo.listaAresta) {
                Vertice u = a.origem;
                Vertice v = a.destino;
                int peso = a.peso;

                //relaxa(u, v, peso);
                if (v.distancia > (double) u.distancia + peso) {
                    v.distancia = u.distancia + peso;
                    v.pred = u;
                }
            }
        }
        for (Aresta a : grafo.listaAresta) {
            if (a.destino.distancia > a.origem.distancia + a.peso) {
                return false;
            }
        }
        return true;
    }
    
}
