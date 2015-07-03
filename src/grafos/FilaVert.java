package grafos;

import java.util.LinkedList;
import java.util.List;

public class FilaVert {

    List<Vertice> vertDescobertos = new LinkedList<>();
    
    public boolean insere(Vertice vert){
        return this.vertDescobertos.add(vert);
    }
    
    public Vertice remove(){
        return this.vertDescobertos.remove(0);
    }
    
    public boolean vazia(){
        return this.vertDescobertos.size() == 0;
    }
}
