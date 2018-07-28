
package util;

import java.util.Iterator;


/**
 * Interface <b>IGraph</b> .
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public interface IGraph {

    public void addVertex(Object key);

    public Iterator vertices();

    public int numVertices();

    public void removeVertex(Object key);

    public void addEdge(Vertex u, Vertex v, Object data);

    public Edge getEdge(Vertex u, Vertex v);
    
    public Vertex getVertex(Object value);

    public Iterator edges();

    public int numEdges();

    public void removeEdge(Edge e);

}
