/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashSet;
import model.Amizade;
import model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

 /**
  * Classe de Testes da classe Graph.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class GraphTest {
    private Graph grafo;
    private HashTable vertice;
    private HashSet aresta;
    private User usuario01, usuario02, usuario03, usuario04;
    private Vertex vertex, vertex02,vertex03,vertex04;
    private Amizade amizade, amizade02;
    
    @Before
    public void setUp() {
        grafo = new Graph();
        vertice = new HashTable(6);
        aresta = new HashSet();
        usuario01 = new User("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");
        usuario02 = new User("Antonio", "antonio@gmail", "antonio123", "99223344", "23041999", "Feira de santana", "AntonioFerreira");
        usuario03 = new User("Gabriel", "gabriel@gmail", "gabriel123", "12122333", "15041998", "Salvador", "GabrielSantos");
        usuario04 = new User("Fernanda", "fernanda@gmail", "fernanda123", "34456758", "13071995", "São Paulo", "FernandaSouza");
        vertex = new Vertex(usuario01.hashCode(), usuario01);
        vertex02 = new Vertex(usuario02.hashCode(), usuario02);
        vertex03 = new Vertex(usuario03.hashCode(), usuario03);
        vertex04 = new Vertex(usuario04.hashCode(), usuario04);
    }
    
    @Test
    public void test(){
        assertNotEquals(vertice, grafo.getVertices());
        grafo.setVertices(vertice);
        assertEquals(vertice, grafo.getVertices());
        
        grafo.setArestas(aresta);
        assertEquals(aresta, grafo.getArestas());
        
        assertEquals(0, grafo.numVertices());
        grafo.addVertex(usuario01);
        assertEquals(1, grafo.numVertices());
        
        assertNotEquals(null, grafo.vertices());
        grafo.addVertex(usuario02);
        assertEquals(2, grafo.numVertices());
        
        assertEquals(vertex, grafo.getVertex(usuario01));
        assertNotEquals(vertex, grafo.getVertex(usuario02));
        vertex.setKey(usuario02.hashCode());
        vertex.setValue(usuario02);
        assertEquals(vertex, grafo.getVertex(usuario02));
        
        grafo.removeVertex(usuario02);
        assertEquals(1, grafo.numVertices());
        grafo.removeVertex(usuario01);
        assertEquals(0, grafo.numVertices());
        
        grafo.addVertex(usuario01);
        grafo.addVertex(usuario02);
        
        assertEquals(0, grafo.numEdges());
        Edge edge = new Edge(vertex, vertex02, amizade);
        grafo.addEdge(vertex, vertex02, amizade);
        assertEquals(1, grafo.numEdges());
        
        assertNotEquals(null, grafo.edges());
        grafo.addVertex(usuario03);
        grafo.addVertex(usuario04);
        grafo.addEdge(vertex03, vertex04, amizade02);
        assertEquals(2, grafo.numEdges());
        
        Edge edge2 = new Edge(vertex03, vertex04, amizade02);        
        grafo.removeEdge(edge);
        assertEquals(1, grafo.numEdges());
        grafo.removeEdge(edge2);
        assertEquals(0, grafo.numEdges());
        
    }
    
}
