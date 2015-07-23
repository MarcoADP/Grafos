package algoritmos;

import grafos.Grafo;
import grafos.Vertice;
import java.util.ArrayList;

public class ComponentesFortementeConexos {
    
    public ArrayList<ArrayList<Vertice>> componentes;
    public Grafo grafo;
    
    public ComponentesFortementeConexos(Grafo grafo) {
        this.componentes = new ArrayList<>();
        this.grafo = grafo;
    }
    
    public void componenteFortementeConexo(){
        BuscaEmProfundidade dfs = new BuscaEmProfundidade(grafo);
        dfs.buscaEmProfundidade();
        
        Grafo transposto = grafo.transposto();
        
        BuscaEmProfundidade dfsTransposto = new BuscaEmProfundidade(transposto);
        dfsTransposto.buscaEmProfundidade(dfs.ordemTopologica);
        
        //Criando uma lista de componentes
        int comp = -1;
        for (Vertice v : dfsTransposto.ordemTopologica) {
            if (dfsTransposto.pred[v.id] == null){
                comp++;
                componentes.add(new ArrayList<>());
            }
            componentes.get(comp).add(v);
        }
    }
    
    public void mostraSCC(){
        int comp = 1;
        for (ArrayList<Vertice> componente : componentes) {
            System.out.print("\nComponente " + (comp++) + ": ");
            for (Vertice v : componente) 
                System.out.print(v.nome + " ");
        }
        System.out.println();        
    }
}
