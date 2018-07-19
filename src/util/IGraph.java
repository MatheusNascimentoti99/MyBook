/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Iterator;


/**
 *
 * @author Matheus Nascimento
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
