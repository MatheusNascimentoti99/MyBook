
package util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * A classe <b>Grafo</b> � a estrutura de dados responvavel por manter as
 * rela��es entre um conjunto de objetos.
 *
 * Classe <b>Graph</b> . Classe que implementa a interface <b>IGraph</b> e a Serializable e armazena a estrutura de dado Grafo.
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class Graph implements IGraph, Serializable {

    private HashTable vertices;
    private HashSet<Edge> arestas;

    /**
     * Construtor da classe <b>Graph</b> .
     */
    public Graph() {
        vertices = new HashTable(5);
        arestas = new HashSet();
    }

    public HashTable getVertices() {
        return vertices;
    }

    /**
     * M�todo que designa um novo conjunto de v�rtices para o grafo.
     * @param vertices HashTable de v�rtices.
     */
    public void setVertices(HashTable vertices) {
        this.vertices = vertices;
    }

    /**
     * M�todo que retorna uma HashSet contendo as arestas.
     * @return HashSet de arestas.
     */
    public HashSet getArestas() {
        return arestas;
    }

    /**
     * M�todo que designa um novo conjunto de arestas.
     * @param arestas nova HashSet de arestas.
     */
    public void setArestas(HashSet arestas) {
        this.arestas = arestas;
    }

    public LinkedList percorrerLargura(Object data) {
        LinkedList visited = new LinkedList();
        Queue<Vertex> fila = new LinkedList<>();
        fila.add((Vertex) this.getVertex(data));
        visited.add((Vertex) this.getVertex(data));
        for (int i = 0; !fila.isEmpty(); i++) {
            Vertex v = fila.poll();
            Set<Integer> chaves = v.getArestas().keySet();
            for (Integer u : chaves) {
                
                if (!visited.contains(((Edge)v.getArestas().get(u)).getPre())) {
                    fila.add(((Edge)v.getArestas().get(u)).getPre());
                    visited.add(((Edge)v.getArestas().get(u)).getPre());
                }
            }
        }
        return visited;
    }

    @Override
    public void addVertex(Object obj) {
        vertices.put(obj.hashCode(), obj);
    }

    /**
     * M�todo que retorna um iterator dos v�rtices de um grafo.
     * @return iterator dos v�rtices.
     */
    @Override
    public Iterator vertices() {
        return vertices.iterator();

    }

    /**
     * M�todo que retorna o n�mero de v�rtices que o grafo cont�m.
     * @return n�mero de v�rtices.
     */
    @Override
    public int numVertices() {
        return vertices.size();
    }

    /**
     * M�todo que remove um v�rtice do grafo, a partir de uma chave.
     * @param key chave o objeto armazenado.
     */
    @Override
    public void removeVertex(Object key) {
        if (!vertices.removeKey(key.hashCode())) {
            throw new NullPointerException();
        }
    }

    /**
     * M�todo que adiciona uma nova aresta entre dois v�rtices.
     * @param u um v�rtice.
     * @param v um v�rtice.
     * @param data objeto que ser� o peso da aresta.
     */
    @Override
    public void addEdge(Vertex u, Vertex v, Object data) {
        if (vertices.contains(u) && vertices.contains(v)) {
            Edge novaAresta = new Edge(u, v, data);
            v.getArestas().put(novaAresta.hashCode(), novaAresta);
            arestas.add(novaAresta);
        } else {
            throw new NullPointerException();
        }
    }
    @Override
    public Edge getEdge(Vertex u, Vertex v) {
        return (Edge) u.getArestas().get(new Edge(u, v, null).hashCode());
    }
    
     /**
     * M�todo que retorna um iterator das arestas de um grafo.
     * @return iterator das arestas.
     */
    @Override
    public Iterator edges() {
        return arestas.iterator();
    }

    /**
     * M�todo que retorna o n�mero de arestas de um grafo.
     * @return n�mero de arestas.
     */
    @Override
    public int numEdges() {
        return arestas.size();
    }

    /**
     * M�todo que remove uma aresta.
     * @param e aresta a ser removida.
     */
    @Override
    public void removeEdge(Edge e) {
        arestas.remove(e);
    }

    /**
     * M�todo que busca e retorna um v�rtice, a partir de um valor passado.
     * @param value objeto valor.
     * @return v�rtice que cont�m o valor passado, e null caso n�o seja encontrado um v�rtice com o valor,
     */
    @Override
    public Vertex getVertex(Object value) {
        Iterator it = this.vertices();
        while (it.hasNext()) {
            Vertex aux = (Vertex) it.next();
            if (aux.getValue().equals(value)) {
                return aux;
            }
        }
        return null;
    }

}
