package grafos;


public class Vertice {
    String nome;
    String rotulo;
    int estado;
    int distancia;
    int tempoDescoberto;
    int tempoTermino;
    Vertice pred;
    
    public Vertice(String nome, String rotulo) {
        this.nome = nome;
        this.rotulo = rotulo;
        estado = 0;
        distancia = 0;
        pred = null;
    }
    
}
