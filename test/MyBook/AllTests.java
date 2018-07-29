/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBook;

import controller.ControllerFileTest;
import controller.ControllerPostagemTest;
import controller.ControllerUserTest;
import model.AmizadeTest;
import model.PostagemTest;
import model.UserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import util.EdgeTest;
import util.GraphTest;
import util.VertexTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ControllerPostagemTest.class,
    ControllerUserTest.class,
    AmizadeTest.class,
    PostagemTest.class,
    UserTest.class,
    EdgeTest.class,
    ControllerFileTest.class,
    GraphTest.class,
    VertexTest.class

})
public class AllTests {
}