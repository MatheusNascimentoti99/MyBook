/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matheus Nascimento
 */
public class AmizadeTest {
    private Amizade amizade;
   
    @Before
    public void setUp() {
        amizade = new Amizade(10);
    }
    
    @Test
    public void test(){
        assertEquals(10,amizade.getAfinidade());
        amizade.setAfinidade(20);
        assertNotEquals(10, amizade.getAfinidade());
        assertEquals(20, amizade.getAfinidade());
    }
}
