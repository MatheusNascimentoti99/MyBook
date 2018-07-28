/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Amizade;
import model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

 /**
  * Classe de Testes da classe Edge.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class EdgeTest {
    
    private User usuario01,usuario02, usuario03, usuario04;
    private Vertex vertex01, vertex02;
    private Vertex vertex03, vertex04;
    private Amizade amizade, amizade02;
    private Edge aresta, aresta02;
    
    @Before
    public void setUp() {
        usuario01 = new User("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");
        usuario02 = new User("Antonio", "antonio@gmail", "antonio123", "99223344", "23041999", "Feira de santana", "AntonioFerreira");
        usuario03 = new User("Gabriel", "gabriel@gmail", "gabriel123", "12122333", "15041998", "Salvador", "GabrielSantos");
        usuario04 = new User("Fernanda", "fernanda@gmail", "fernanda123", "34456758", "13071995", "São Paulo", "FernandaSouza");
        vertex01 = new Vertex(usuario01.hashCode(), usuario01);
        vertex02 = new Vertex(usuario02.hashCode(), usuario02);
        vertex03 = new Vertex(usuario03.hashCode(), usuario03);
        vertex04 = new Vertex(usuario04.hashCode(), usuario04);
        amizade = new Amizade(100);
        amizade02 = new Amizade(60);
        aresta = new Edge(vertex01, vertex02, amizade);
        aresta02 = new Edge(vertex03, vertex04, amizade02);
    }
    
    @Test
    public void test(){
        assertNotEquals(vertex02, aresta.getPre());
        assertEquals(vertex01, aresta.getPre());
        aresta.setPre(vertex02);
        assertEquals(vertex02, aresta.getPre());
        aresta.setPre(vertex01);
        assertEquals(vertex01, aresta.getPre());
        
        assertNotEquals(vertex01, aresta.getCorrent());
        assertEquals(vertex02, aresta.getCorrent());
        aresta.setCorrent(vertex01);
        assertEquals(vertex01, aresta.getCorrent());
        aresta.setCorrent(vertex02);
        assertEquals(vertex02, aresta.getCorrent());
        
        assertNotEquals(amizade02, aresta.getPeso());
        assertEquals(amizade, aresta.getPeso());
        aresta.setPeso(amizade02);
        assertEquals(amizade02, aresta.getPeso());
        aresta.setPeso(amizade);
        
        assertNotEquals(aresta.hashCode(), aresta02.hashCode());
        aresta02.setPre(vertex01);
        aresta02.setCorrent(vertex02);
        aresta.setPeso(amizade02);
        assertEquals(aresta.hashCode(), aresta02.hashCode());
        assertTrue(aresta.equals(aresta02));
    }
}
