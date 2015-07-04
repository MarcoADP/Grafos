package grafos;

public class Aresta {
    public Vertice origem;
    public Vertice destino;
    public int peso;

    public Aresta(Vertice origem, Vertice destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }
}
