package grafos;

public class Algoritmos {
    
    static int tempo;
    
    public static void resetaVertices(Grafos grafo){
        for(Vertice vert : grafo.vertice){
            vert.tempoDescoberto = 0;
            vert.tempoTermino = 0;
            vert.distancia = 0;
            vert.pred = null;
            vert.estado = 0;
        }
    }
    
    public static void busca_largura(Grafos grafo, Vertice vert){
        //0 Não descoberto (Branco)
        //1 Descoberto e na fila (Cinza)
        //-1 Todos vizinhos descobertos (Preto)
        FilaVert fila = new FilaVert();
        fila.insere(vert);
        while(!fila.vazia()){
            Vertice u = fila.remove();
            int i;
            for(i = 0; i < u.listaAdjacencia.size(); i++){
                Vertice vertAuxiliar = u.listaAdjacencia.get(i);
                if(vertAuxiliar.estado == 0){
                    vertAuxiliar.estado = 1;
                    vertAuxiliar.distancia = u.distancia + 1;
                    vertAuxiliar.pred = u;
                    fila.insere(vertAuxiliar);
                }
            }
            u.estado = -1;
            //for(Aresta aresta : grafo.aresta){
                //System.out.println(aresta.origem.nome + " " + aresta.destino.nome);
                //if(aresta.origem.nome.equals(u.nome)){
                    //System.out.println("\nAlvo: " + u.nome);
                    //System.out.println(aresta.origem.nome + " " + aresta.destino.nome);
                    //if(aresta.destino.estado == 0){
                        //aresta.destino.estado = 1;
                        //aresta.destino.distancia = u.distancia + 1;
                        //aresta.destino.pred = u;
                        //fila.insere(aresta.destino);
                    //}
                //}
            //}
            //u.estado = -1;
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

    public static void menorCaminho(Grafos grafo, Vertice origem, Vertice destino){
        if (origem.nome.equals(destino.nome)){
            System.out.println("\n" + destino.nome);
        } else {
            if(destino.pred == null){
                System.out.println("\nNão existe caminho");
            } else {
                menorCaminho(grafo, origem, destino.pred);
                System.out.println(destino.nome);
            }
        }
    }

    public static void inicializa_busca_profundidade(Grafos grafo){
        tempo = 0;
        for(Vertice vertice : grafo.vertice){
            if(vertice.estado == 0){
                 busca_profundidade(vertice);
            }
        }
    }
    
    public static void busca_profundidade(Vertice vertice){
        tempo++;
        vertice.estado = 1;
        vertice.tempoDescoberto = tempo;
        int i;
        for(i = 0; i < vertice.listaAdjacencia.size(); i++){
            Vertice vertAuxiliar = vertice.listaAdjacencia.get(i);
            if(vertAuxiliar.estado == 0){
                vertAuxiliar.pred = vertice;
                busca_profundidade(vertAuxiliar);
            }
        }
        /*for(Aresta aresta : grafo.aresta){
            if(aresta.origem.nome.equals(vertice.nome)){
                if(aresta.destino.estado == 0){
                    aresta.destino.pred = vertice;
                    busca_profundidade(grafo, aresta.destino);
                }
            }
        }*/
        vertice.estado = -1;
        tempo++;
        vertice.tempoTermino = tempo;
    } 
    
    public static void mostraVertProfundidade(Grafos grafo){
        for(Vertice vertice : grafo.vertice){
            System.out.println("\n\nVertice: " + vertice.nome);
            System.out.println("Tempo de descoberta: " + vertice.tempoDescoberto);
            System.out.println("Tempo de término: " + vertice.tempoTermino);
            try{
                System.out.println("Predecessor: " + vertice.pred.nome);
            } catch(Exception e){
                System.out.println("Predecessor: NULL");
            }
        }
    }
}
