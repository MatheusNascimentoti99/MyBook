
package util;

import java.io.Serializable;

/**
 *
 * A classe <b>Edge</b> � responsavel para criar uma adjac�ncia entre dois vertices.
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
     * Construtor da classe <b>Edge</b> . No construtor � passado o v�rtice de origem, o v�rtice de destino e o peso da aresta, do tipo Object.
     * @param pre v�rtice de origem.
     * @param corrent v�rtice de destino
     * @param peso peso da aresta.
     */
    public Edge(Vertex pre, Vertex corrent, Object peso) {
        this.pre = pre;
        this.corrent = corrent;
        this.peso = peso;
    }
    
    /**
     * M�todo que retorna o v�rtice de origem de uma aresta.
     * @return v�rtice origem.
     */
    public Vertex getPre() {
        return pre;
    }

    /**
     * M�todo que designa um novo v�rtice origem para uma aresta.
     * @param pre novo v�rtice.
     */
    public void setPre(Vertex pre) {
        this.pre = pre;
    }

    /**
     * M�todo que retorna o v�rtice de destino de uma aresta.
     * @return v�rtice destino.
     */
    public Vertex getCorrent() {
        return corrent;
    }

    /**
     * M�todo que designa um novo v�rtice de destino de uma aresta.
     * @param corrent novo v�rtice.
     */
    public void setCorrent(Vertex corrent) {
        this.corrent = corrent;
    }

    /**
     * M�todo que retorna o peso de uma aresta.
     * @return peso da aresta.
     */
    public Object getPeso() {
        return peso;
    }

    /**
     * M�todo que designa um novo peso para uma aresta.
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
