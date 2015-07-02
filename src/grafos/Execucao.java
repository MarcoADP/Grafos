
package grafos;

import java.io.IOException;

public class Execucao {
    public static void main(String[] args) throws IOException {
        Grafos grafo = new Grafos();
        grafo = Leitura.leitura();
        grafo.mostraVertice();
        grafo.mostraAresta();
    }
}
