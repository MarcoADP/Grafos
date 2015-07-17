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
            vert.cor = BRANCO;
        }
    }

    public static void buscaEmLargura(Grafo grafo, Vertice vert) {
        FilaVert fila = new FilaVert();
        vert.cor = CINZA;
        fila.insere(vert);

        while (!fila.vazia()) {
            Vertice u = fila.remove();
            for (Aresta a : u.listaAdjacencia) {
                Vertice v = a.destino;
                if (v.cor == BRANCO) {
                    v.cor = CINZA;
                    v.distancia = u.distancia + 1;
                    v.pred = u;
                    fila.insere(v);
                }
            }
            u.cor = PRETO;
        }
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
    public static void buscaEmProfundidade(Grafo grafo) {
        ordemTopologica = new ArrayList<>();
        tempo = 0;
        for (Vertice vertice : grafo.listaVertice) {
            if (vertice.cor == BRANCO) {
                dfsVisit(vertice);
            }
        }
    }

    public static void dfsVisit(Vertice vertice) {
        tempo++;
        vertice.cor = CINZA;
        vertice.tempoDescoberto = tempo;
        int i;
        for (i = 0; i < vertice.listaAdjacencia.size(); i++) {
            Vertice vertAuxiliar = vertice.listaAdjacencia.get(i).destino;
            if (vertAuxiliar.cor == BRANCO) {
                vertAuxiliar.pred = vertice;
                dfsVisit(vertAuxiliar);
            }
        }
        vertice.cor = PRETO;
        tempo++;
        vertice.tempoTermino = tempo;
        ordemTopologica.add(0, vertice);
    }

    public static void mostraOrdemTopologica() {
        System.out.print("\nOrdem Topológica: ");
        for (Vertice v : ordemTopologica) {
            System.out.print(v.nome + " ");
        }
        System.out.println("\n");
    }
    
    public static void componentesConexos(Grafo grafo){
        for (Vertice u : grafo.listaVertice) {
            u.cor = BRANCO;
            u.pred = null;
        }
        int cc = 0;
        ordemTopologica = new ArrayList<>();
        for (Vertice v : grafo.listaVertice) {
            if (v.cor == BRANCO){
                cc++;
                dfsVisit(v);
            }
        }
        
        System.out.println("\nComponentes conexos: " + cc);
    }
    
    public static void componenteFortementeConexo(Grafo grafo){
        resetaVertices(grafo);
        Grafo transposto;
        
        buscaEmProfundidade(grafo);

        transposto = grafo.transposto();
        
        int posicaoTopologica[] = new int[ordemTopologica.size()];
        for (int i = 0; i < ordemTopologica.size(); i++) {
            Vertice v = ordemTopologica.get(i);
            posicaoTopologica[i] = v.numero;
        }
        
        ordemTopologica = new ArrayList<>();
        
        tempo = 0;
        for (int i = 0; i < posicaoTopologica.length; i++) {
            Vertice v = transposto.getVertice(posicaoTopologica[i]);
            if (v.cor == BRANCO) {
                dfsVisit(v);
            }
        }
    }
    
    public static void mostraSCC(){
        int comp = 1;
        for (int i = 0; i < ordemTopologica.size(); i++) {
            Vertice v = ordemTopologica.get(i);
            if (v.pred == null){
                System.out.print("\n"); 
                System.out.print("Componente " + (comp++) + ": ");
            }
            System.out.print(v.nome + " ");                       
        }
        System.out.println();
    }
        
    /*
    *   ALGORITMOS DE CAMINHOS MÍNIMOS
    */
        
    private static void inicializaVertices(Grafo grafo, Vertice s) {
        for (Vertice v : grafo.listaVertice) {
            v.distancia = INFINITO;
            v.pred = null;
            v.visitado = false;
        }
        s.distancia = 0;
    }

    private static boolean relaxa(Vertice u, Vertice v, int peso) {
        if (v.distancia > (double)u.distancia + peso) {
            v.distancia = u.distancia + peso;
            v.pred = u;
            return true;
        }
        return false;
    }
    
    /* Algoritmo de Dijkstra */
    public static void dijkstra(Grafo grafo, Vertice s) {
        inicializaVertices(grafo, s);
        PriorityQueue<Vertice> filaP = new PriorityQueue<>();
        filaP.add(s);

        //for (Vertice v : grafo.listaVertice)
        //    filaP.add(v);
        while (!filaP.isEmpty()) {
            Vertice u = filaP.poll();
            if (!u.visitado){
                u.visitado = true;
                for (Aresta a : u.listaAdjacencia) {
                    Vertice v = a.destino;
                    if (relaxa(u, v, a.peso)) {
                        filaP.add(v);
                    }
                }
            }
        }
    }

    public static boolean bellmanFord(Grafo grafo, Vertice s) {
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
            if (a.destino.distancia > a.origem.distancia + a.peso) {
                return false;
            }
        }
        return true;
    }
    
    public static int minimo(int a, double b){
        if(a < b){
            return a;
        }
        return (int) b;
    }
    
    public static int[][] floydWarshall(Grafo grafo){
        //olhar em  http://algs4.cs.princeton.edu/44sp/FloydWarshall.java.html
        int k, i, j;
        int n = grafo.listaVertice.size();
        int[][][] D = null;
        /*for(i = 0; i < n; i++){
            for(j = 0; j < n; j++){
                D[0][i][j] = grafo.matrizAdjacencia[i][j];
            }
        }*/
        D[0][0][0] = 2;
        for(k = 1; k <= n; k++){
            for(i = 1; i <= n; i++){
                for(j = 1; j <= n; j++){
                    D[k][i][j] = minimo(D[k-1][i][j],(double) D[k-1][i][k] + D[k-1][k][j]);
                }
            }
        }
        return D[n];
    }
}
