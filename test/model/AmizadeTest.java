
package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

 /**
  * Classe de Testes da classe Amizade.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
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
