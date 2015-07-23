package algoritmos;

import grafos.Grafo;
import static grafos.Grafo.INFINITO;

public final class FloydWarshall {
    
    public int dist[][];
    public boolean temCicloNegativo;
    public int nVert;
    public Grafo grafo;
    
    public FloydWarshall(Grafo grafo) {
        this.nVert = grafo.numVert();
        this.dist = new int[nVert][nVert];
        this.grafo = grafo;
        this.temCicloNegativo = false;        
    }
    
    public boolean floydWarshall(){
        inicializarMatrizDist(grafo);
        
        for (int k = 0; k < nVert; k++) {
            for (int i = 0; i < nVert; i++) {
                for (int j = 0; j < nVert; j++) {
                    if (dist[i][j] > (double)dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
                if (dist[i][i] < 0){
                    temCicloNegativo = true;
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private void inicializarMatrizDist(Grafo grafo){
        for (int i = 0; i < nVert; i++) {
            for (int j = 0; j < nVert; j++) {
                if (i == j)
                    dist[i][j] = 0;
                else if (grafo.matrizAdjacencia[i][j] == 0)
                    dist[i][j] = INFINITO;
                else
                    dist[i][j] = grafo.matrizAdjacencia[i][j];
            }
        }
    }
    
    public void mostrarMatrizDist(){
        System.out.println("\n\nMatriz de distâncias:");
        for(int i = 0; i < nVert; i++){
            System.out.print("[");
            for (int j = 0; j < nVert-1; j++) {
                if (dist[i][j] == INFINITO)
                    System.out.print("I, ");
                else
                    System.out.print(dist[i][j] + ", ");
            }
            if (dist[i][nVert-1] == INFINITO)
                    System.out.println("I]");
                else
                    System.out.println(dist[i][nVert-1] + "]");
            
        }
    }
}
