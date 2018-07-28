
package model;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

 /**
  * Classe de Testes que contém todos os testes do pacote Model.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({model.AmizadeTest.class, model.UserTest.class, model.PostagemTest.class})
public class ModelSuite {

    @Before
    public void setUp() throws Exception {
    }
    
}
