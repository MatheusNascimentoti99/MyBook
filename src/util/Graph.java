/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Matheus Nascimento
 */
public class Graph implements IGraph, Serializable {
    private HashTable vertices;
    private HashSet<Edge> arestas;

    public Graph() {
        vertices = new HashTable(5);
        arestas = new HashSet();
    }

    
    public HashTable getVertices() {
        return vertices;
    }

    public void setVertices(HashTable vertices) {
        this.vertices = vertices;
    }

    public HashSet getArestas() {
        return arestas;
    }

    public void setArestas(HashSet arestas) {
        this.arestas = arestas;
    }
    
    
    @Override
    public void addVertex(Object obj) {
        vertices.put(obj.hashCode(), obj);
    }

    @Override
    public Iterator vertices() {
        return vertices.iterator();
       
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public void removeVertex(Object key) {
        if(!vertices.removeKey(key.hashCode())){
           throw new  NullPointerException();
        }
    }

    @Override
    public void addEdge(Vertex u, Vertex v, Object data) {
        if(vertices.contains(u) && vertices.contains(v)){
            Edge novaAresta = new Edge(u, v, data);
            v.getArestas().put(novaAresta.hashCode(), novaAresta);
            arestas.add(novaAresta);
        }else{
            throw new  NullPointerException();
        }
    }

    @Override
    public Edge getEdge(Vertex u, Vertex v) { 
        return (Edge) u.getArestas().get(new Edge(u,v, null).hashCode());
    }

    @Override
    public Iterator edges() {
        return arestas.iterator();
    }

    @Override
    public int numEdges() {
        return arestas.size();
    }

    @Override
    public void removeEdge(Edge e) {
        arestas.remove(e);
    }

    @Override
    public Vertex getVertex(Object value) {
        this.vertices.get(value.hashCode());
        Iterator it = this.vertices();
        while(it.hasNext()){
            Vertex aux = (Vertex) it.next();
            if(aux.getValue().equals(value))
                return aux;
        }
        return null;        
    }

   



}
