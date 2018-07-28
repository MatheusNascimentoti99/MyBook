
package util;

import java.io.Serializable;

/**
 *
 * A classe <b>Edge</b> é responsavel para criar uma adjacência entre dois vertices.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class Edge implements Serializable {

    private Vertex pre;
    private Vertex corrent;
    private Object peso;
    
    /**
     * Construtor da classe <b>Edge</b> . No construtor é passado o vértice de origem, o vértice de destino e o peso da aresta, do tipo Object.
     * @param pre vértice de origem.
     * @param corrent vértice de destino
     * @param peso peso da aresta.
     */
    public Edge(Vertex pre, Vertex corrent, Object peso) {
        this.pre = pre;
        this.corrent = corrent;
        this.peso = peso;
    }
    
    /**
     * Método que retorna o vértice de origem de uma aresta.
     * @return vértice origem.
     */
    public Vertex getPre() {
        return pre;
    }

    /**
     * Método que designa um novo vértice origem para uma aresta.
     * @param pre novo vértice.
     */
    public void setPre(Vertex pre) {
        this.pre = pre;
    }

    /**
     * Método que retorna o vértice de destino de uma aresta.
     * @return vértice destino.
     */
    public Vertex getCorrent() {
        return corrent;
    }

    /**
     * Método que designa um novo vértice de destino de uma aresta.
     * @param corrent novo vértice.
     */
    public void setCorrent(Vertex corrent) {
        this.corrent = corrent;
    }

    /**
     * Método que retorna o peso de uma aresta.
     * @return peso da aresta.
     */
    public Object getPeso() {
        return peso;
    }

    /**
     * Método que designa um novo peso para uma aresta.
     * @param peso novo peso da aresta.
     */
    public void setPeso(Object peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = hash + pre.hashCode();
        hash = hash + corrent.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        return true;
    }

}
