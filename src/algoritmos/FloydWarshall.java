package algoritmos;

import grafos.Grafo;

public final class FloydWarshall {
    
    public int dist[][];
    public boolean temCicloNegativo;
    public Grafo grafo;
    
    public FloydWarshall(Grafo grafo) {
        this.dist = new int[grafo.numVert()][grafo.numVert()];
        this.grafo = grafo;
        this.temCicloNegativo = false;
        
    }
    
    public boolean floydWarshall(){
        inicializarMatrizDist(grafo);
        
        
        return true;
    }
    
    private void inicializarMatrizDist(Grafo grafo){
        
    }
    
    public void mostrarMatrizDist(){
        
    }
}
