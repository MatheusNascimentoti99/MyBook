/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matheus Nascimento
 */
public class VertexTest {
    

        private Vertex vertex01, vertex02;
        private User usuario01, usuario02;
        
    
    @Before
    public void setUp() {
        usuario01 = new User("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");
        usuario02 = new User("Antonio", "antonio@gmail", "antonio123", "99223344", "23041999", "Feira de santana", "AntonioFerreira");
        vertex01 = new Vertex(usuario01.hashCode(), usuario01);
        vertex02 = new Vertex(usuario02.hashCode(), usuario02);
    }

    @Test
    public void test(){
        
    }
}
