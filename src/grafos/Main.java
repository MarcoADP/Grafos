
package grafos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    
    public static class ComponentesFortementeConexos {
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

            int comp = -1;
            for (Vertice v : dfs.ordemTopologica) {
                if (dfsTransposto.pred[v.id] == null){
                    comp++;
                    componentes.add(new ArrayList<>());
                }
                componentes.get(comp).add(v);
            }
        }
    
    
    }
    
    public static class BuscaEmProfundidade{
        public static final int BRANCO = 0;
        public static final int CINZA = 1;
        public static final int PRETO = -1;
        public Vertice pred[];
        public int distancia[];
        public Grafo grafo;
        ArrayList<Vertice> ordemTopologica;
        public int tempo;
        public int tempoDescoberto[];
        public int tempoTermino[];
        public int cor[];

        public BuscaEmProfundidade(Grafo grafo) {
            this.grafo = grafo;
            this.pred = new Vertice[grafo.numVert()];
            this.distancia = new int[grafo.numVert()];
            this.cor = new int[grafo.numVert()];
            this.tempoDescoberto = new int[grafo.numVert()];
            this.tempoTermino = new int[grafo.numVert()];
            ordemTopologica = new ArrayList<>();        
        }

        public void buscaEmProfundidade() {
            inicializaVertices(grafo);
            tempo = 0;
            for (Vertice u : grafo.listaVertice) {
                if (cor[u.id] == BRANCO)
                    dfsVisit(u);
            }
        }

        public void buscaEmProfundidade(ArrayList<Vertice> ordem) {
            inicializaVertices(grafo);
            tempo = 0;
            for (Vertice v : ordem) {
                Vertice u = grafo.getVertice(v.id);
                if (cor[u.id] == BRANCO)
                    dfsVisit(u);
            }
        }

        public void dfsVisit(Vertice u) {
            tempo++;
            cor[u.id] = CINZA;
            tempoDescoberto[u.id] = tempo;

            for (Aresta a : u.listaAdjacencia) {
                Vertice v = a.destino;
                if (cor[v.id] == BRANCO) {
                    pred[v.id] = u;
                    dfsVisit(v);
                }
            }            

            cor[u.id] = PRETO;
            tempo++;
            tempoTermino[u.id] = tempo;
            ordemTopologica.add(0, u);
        }

        private void inicializaVertices(Grafo grafo) {
            for (Vertice v : grafo.listaVertice) {
                pred[v.id] = null;
                cor[v.id] = BRANCO;            
            }
        }
    
    }
    
    public static class Vertice{
        public int id;
        public String nome;
        public ArrayList<Aresta> listaAdjacencia;

        public Vertice(int id, String nome) {
            this.listaAdjacencia = new ArrayList<>();
            this.id = id;
            this.nome = nome;
        }
    }
    
    public static class Aresta {
        public Vertice origem;
        public Vertice destino;
        public int peso;

        public Aresta(Vertice origem, Vertice destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static class Grafo {
        public ArrayList<Vertice> listaVertice;
        public ArrayList<Aresta> listaAresta;
        public static final int INFINITO = Integer.MAX_VALUE;

        public Grafo() {
            this.listaAresta = new ArrayList<>();
            this.listaVertice = new ArrayList<>();
        }

        public Vertice getVertice(int id){
            return this.listaVertice.get(id);
        }
        
        public void addAresta(Aresta a){   
            this.listaAresta.add(a);            
            getVertice(a.origem.id).listaAdjacencia.add(a);
        }
        
        public int numVert(){
            return this.listaVertice.size();
        } 
        
        public Grafo transposto(){        
            Grafo transposto = new Grafo();
            for (Vertice v : this.listaVertice)
                transposto.listaVertice.add(new Vertice(v.id, v.nome));
            for (Aresta a : this.listaAresta){
                Vertice origem = transposto.getVertice(a.destino.id);
                Vertice destino = transposto.getVertice(a.origem.id); 
                transposto.addAresta(new Aresta(origem, destino, a.peso));
            }

            return transposto;  
        }
    }
    
    public static void leitura() throws IOException{
        Grafo grafo = null;
        
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(entrada);
        
        String linha = buffer.readLine();
        int nVert = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
        int nAresta = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
        while(nVert != 0 && nAresta != 0){
            grafo = new Grafo();
            for (int i = 0; i < nVert; i++)
                grafo.listaVertice.add(new Vertice(i, String.valueOf(i+1)));
            
            for (int i = 0; i < nAresta; i++) {
                linha = buffer.readLine();
                int origem = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
                int destino = Integer.parseInt(linha.substring(linha.indexOf(' ') + 1, linha.lastIndexOf(' ')));
                int tipo = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
                Vertice vertOrigem = grafo.getVertice(origem - 1);
                Vertice vertDestino = grafo.getVertice(destino - 1);
                
                grafo.addAresta(new Aresta(vertOrigem, vertDestino, 1));
                if (tipo == 2)
                    grafo.addAresta(new Aresta(vertDestino, vertOrigem, 1));
            }
            
            ComponentesFortementeConexos scc = new ComponentesFortementeConexos(grafo);
            scc.componenteFortementeConexo();
            if (scc.componentes.size() == 1)
                System.out.println("1");
            else
                System.out.println("0");
            
            linha = buffer.readLine();
            nVert = Integer.parseInt(linha.substring(0, linha.indexOf(' ')));
            nAresta = Integer.parseInt(linha.substring(linha.lastIndexOf(' ') + 1, linha.length()));
        }
    }   
    
    public static void main(String[] args) throws IOException {
        Main.leitura();
    }
}
