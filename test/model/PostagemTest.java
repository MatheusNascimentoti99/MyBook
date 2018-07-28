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
  * Classe de Testes da classe Postagem.
  * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class PostagemTest {
    private Postagem postagem;
    private String urlVideo01, urlVideo02;
    private String urlFoto01, urlFoto02;
    private String textoPost;
    
    @Before
    public void setUp() {
        postagem = new Postagem();
        urlVideo01 = "www.youtube.com/programandoEmJava";
        urlFoto01 = "d:/Usuario/foto.png";
        urlVideo02 = "c:/Usuario/java.mp4";
        urlFoto02 = "www.google.com/imagens/imagem.jpg";
        textoPost = "PostagemTeste";
    }

   @Test
   public void test(){
       assertEquals(null, postagem.getTextoPostagem());
       postagem.setTextoPostagem(textoPost);
       assertEquals(textoPost, postagem.getTextoPostagem());
       
       postagem.getUrlImagem().add(urlFoto02);
       assertEquals(false, postagem.getUrlImagem().isEmpty());
       assertNotEquals(true,postagem.getUrlImagem().contains(urlFoto01));
       postagem.getUrlImagem().add(urlFoto01);
       assertEquals(true, postagem.getUrlImagem().contains(urlFoto01));
       assertEquals(2, postagem.getUrlImagem().size());
       postagem.getUrlImagem().remove(urlFoto02);
       assertEquals(true, postagem.getUrlImagem().contains(urlFoto01));
       postagem.getUrlImagem().remove(urlFoto01);
       assertEquals(true, postagem.getUrlImagem().isEmpty());
       
       
       postagem.getUrlVideo().add(urlVideo02);
       assertEquals(false, postagem.getUrlVideo().isEmpty());
       assertNotEquals(true,postagem.getUrlVideo().contains(urlVideo01));
       postagem.getUrlVideo().add(urlVideo01);
       assertEquals(true, postagem.getUrlVideo().contains(urlVideo01));
       assertEquals(2, postagem.getUrlVideo().size());
       postagem.getUrlVideo().remove(urlVideo02);
       assertEquals(true, postagem.getUrlVideo().contains(urlVideo01));
       postagem.getUrlVideo().remove(urlVideo01);
       assertEquals(true, postagem.getUrlVideo().isEmpty());
       
   }
}
