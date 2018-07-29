
package controller;

import java.util.Iterator;
import model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Edge;
import util.Graph;
import util.Vertex;

 /**
  * Classe de Testes da classe ControllerUser.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class ControllerUserTest {
    private ControllerUser controllerUser;
    private Graph grafo;
    private User usuario01;
    
    @Before
    public void setUp() {
        controllerUser = new ControllerUser();
        grafo = new Graph();
        usuario01 = new User("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");

    }

    @Test
    public void test(){
        
        assertEquals(controllerUser.getLoginCorrent(), null);
        controllerUser.setLoginCorrent(usuario01);
        assertEquals(controllerUser.getLoginCorrent(), usuario01);
        
        assertEquals(controllerUser.getPerfilCorrent(), null);
        controllerUser.setPerfilCorrent(usuario01);
        assertEquals(controllerUser.getPerfilCorrent(), usuario01);
        
        assertFalse(controllerUser.getGrafoUsers().equals(grafo));
        controllerUser.setGrafoUsers(grafo);
        assertEquals(controllerUser.getGrafoUsers(), grafo);
        
        assertEquals(false, controllerUser.checkRegistred(usuario01));
        controllerUser.addUser("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");
        assertEquals(1, controllerUser.getGrafoUsers().numVertices());
        assertEquals(true, controllerUser.checkRegistred(usuario01));
        
        
        controllerUser.addUser("antonio", "antonio@gmail", "antonio123", "22113344", "10122000", "Santana", "antonioFerreira");
        User novoUser = new User("antonio", "antonio@gmail", "antonio123", "22113344", "10122000", "Santana", "antonioFerreira");
        assertEquals(2, controllerUser.getGrafoUsers().numVertices());
        assertEquals(true, controllerUser.checkRegistred(novoUser));
        
        assertEquals(false, controllerUser.checkLogin("Samuel", "Sam"));
        assertEquals(false, controllerUser.checkLogin("Jose", "JoseAntonio"));
        assertEquals(true, controllerUser.checkLogin("antonioFerreira", "antonio123"));
        
        assertNotEquals(controllerUser.getLoginCorrent(), usuario01);
        assertEquals(controllerUser.getLoginCorrent(), novoUser);
        
        String local = "c:/usuario01/fotoPerfil.pnj";
        controllerUser.alterarFoto(local);
        assertEquals(controllerUser.getLoginCorrent().getDirFoto(), local);
        
        Iterator iterador = controllerUser.showUsers();
        assertEquals(true, iterador.hasNext());
        assertNotEquals(null, iterador.next());
        
        assertEquals(null, controllerUser.getGrafoUsers().getEdge(controllerUser.getGrafoUsers().getVertex(usuario01), controllerUser.getGrafoUsers().getVertex(novoUser)));
        controllerUser.addAmizade(novoUser, usuario01);
        assertEquals(1,controllerUser.getGrafoUsers().numEdges());
        
        Vertex vertice01 = controllerUser.getGrafoUsers().getVertex(usuario01);
        Vertex vertice02 =controllerUser.getGrafoUsers().getVertex(novoUser);
        Iterator it = controllerUser.getGrafoUsers().edges();
        Edge aresta =(Edge) it.next();
        assertEquals(vertice01, aresta.getCorrent());
        assertEquals(vertice02, aresta.getPre());
    }
}
