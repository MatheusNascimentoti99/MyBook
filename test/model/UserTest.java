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
  * Classe de Testes da classe User.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class UserTest {
    private User usuario01;
    private Postagem postagem, postagem02;
    
    
    @Before
    public void setUp() {
        usuario01 = new User("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");
        postagem = new Postagem();
        postagem02 = new Postagem();
    }
    
    @Test
   public void test(){
      
      assertEquals("Jose", usuario01.getNome());
      usuario01.setNome("Fagner");
      assertNotEquals("Jose", usuario01.getNome());
      assertEquals("jose@gmail", usuario01.getEmail());
      usuario01.setEmail("fagner@uefs");
      assertNotEquals("jose@gmail", usuario01.getEmail());
      
      assertNotEquals("jose1234", usuario01.getPassword());
      usuario01.setPassword("jose1234");
      assertEquals("jose1234", usuario01.getPassword());
      
      assertEquals("88223344", usuario01.getTelefone());
      usuario01.setTelefone("99112233");
      assertNotEquals("88223344", usuario01.getTelefone());
      
      assertNotEquals("Salvador", usuario01.getEndereco());
      usuario01.setEndereco("Salvador");
      assertEquals("Salvador", usuario01.getEndereco());
      
      assertEquals("10122000", usuario01.getDataNasc());
      usuario01.setDataNasc("02031994");
      assertNotEquals("10122000", usuario01.getDataNasc());
      
      assertNotEquals("JoseFerreira", usuario01.getLogin());
      usuario01.setLogin("JoseFerreira");
      assertEquals("JoseFerreira", usuario01.getLogin());
      
      postagem.setTextoPostagem("Postagem");
      postagem02.setTextoPostagem("Postagem 02");
      usuario01.getPostagens().add(postagem);
      assertEquals(false, usuario01.getPostagens().isEmpty());
      assertEquals(true, usuario01.getPostagens().contains(postagem));
      usuario01.getPostagens().add(postagem02);
      assertEquals(2, usuario01.getPostagens().size());
      usuario01.getPostagens().remove(postagem);
      assertEquals(false, usuario01.getPostagens().contains(postagem));
      assertEquals(true, usuario01.getPostagens().contains(postagem02));
   }
}
