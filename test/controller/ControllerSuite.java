package controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Matheus Nascimento
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({controller.ControllerFileTest.class, controller.ControllerPostagemTest.class, controller.ControllerUserTest.class})
public class ControllerSuite {

    @Before
    public void setUp() throws Exception {
    }
    
}
