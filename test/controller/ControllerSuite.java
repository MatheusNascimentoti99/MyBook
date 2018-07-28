package controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

 /**
  * Classe de Testes que contém todos os testes do pacote Controller.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({controller.ControllerFileTest.class, controller.ControllerPostagemTest.class, controller.ControllerUserTest.class})
public class ControllerSuite {

    @Before
    public void setUp() throws Exception {
    }
    
}
