package grafos;

public class Algoritmos {
    public static void busca_largura(Grafos grafo, Vertice vert){
        //0 Não descoberto (Branco)
        //1 Descoberto e na fila (Cinza)
        //-1 Todos vizinhos descobertos (Preto)
        FilaVert fila = new FilaVert();
        fila.insere(vert);
        while(!fila.vazia()){
            Vertice u = fila.remove();
            for(Aresta aresta : grafo.aresta){
                //System.out.println(aresta.origem.nome + " " + aresta.destino.nome);
                if(aresta.origem.nome.equals(u.nome)){
                    //System.out.println("\nAlvo: " + u.nome);
                    //System.out.println(aresta.origem.nome + " " + aresta.destino.nome);
                    if(aresta.destino.estado == 0){
                        aresta.destino.estado = 1;
                        aresta.destino.distancia = u.distancia + 1;
                        aresta.destino.pred = u;
                        fila.insere(aresta.destino);
                    }
                }
            }
            u.estado = -1;
        }
    }
    
    public static void mostraVertLargura(Grafos grafo){
        for(Vertice vertice : grafo.vertice){
            System.out.println("\n\nVertice: " + vertice.nome);
            if(vertice.pred != null){
                System.out.println("Predecessor: " + vertice.pred.nome);
            } else{
                System.out.println("Predecessor: NULL");
            }
            System.out.println("Distancia: " + vertice.distancia);
        }
    }
}
