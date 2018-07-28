/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;
import model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

 /**
  * Classe de Testes da classe Vertex.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class VertexTest {
    
    private Vertex vertex01, vertex02;
    private User usuario01, usuario02;
    private HashMap aresta;
        
    @Before
    public void setUp() {
        usuario01 = new User("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");
        usuario02 = new User("Antonio", "antonio@gmail", "antonio123", "99223344", "23041999", "Feira de santana", "AntonioFerreira");
        vertex01 = new Vertex(usuario01.hashCode(), usuario01);
        vertex02 = new Vertex(usuario02.hashCode(), usuario02);
        aresta = new HashMap();
    }

    @Test
    public void test(){
       assertNotEquals(usuario02, vertex01.getValue());
       assertEquals(usuario01, vertex01.getValue());
       vertex01.setValue(usuario02);
       assertEquals(usuario02, vertex01.getValue());
       vertex01.setValue(usuario01);
       assertEquals(usuario01, vertex01.getValue());
       
       assertEquals(usuario01.hashCode(), vertex01.getKey());
       vertex01.setValue(usuario02);
       vertex01.setKey(usuario02.hashCode());
       assertNotEquals(usuario01.hashCode(), vertex01.getKey());
       assertEquals(usuario02.hashCode(), vertex01.getKey());
       vertex01.setValue(usuario01);
       vertex01.setKey(usuario01.hashCode());
       
       vertex01.setArestas(aresta);
       assertEquals(aresta, vertex01.getArestas());
       
       assertFalse(vertex01.equals(vertex02));
       assertNotEquals(vertex01.hashCode(), vertex02.hashCode());
       vertex02.setKey(usuario01.hashCode());
       vertex02.setValue(usuario01);
       assertTrue(vertex01.equals(vertex02));
       assertEquals(vertex01.hashCode(), vertex02.hashCode());
    }
}
