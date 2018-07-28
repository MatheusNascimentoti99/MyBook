/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
 
/**
 * Classe de Testes da classe ControllerPostagem.
 * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class ControllerPostagemTest {
    private ControllerPostagem controlPost;
    private String urlVideo01, urlVideo02;
    private String urlFoto01, urlFoto02;
    
    @Before
    public void setUp() {
        urlVideo01 = "www.youtube.com/programandoEmJava";
        urlFoto01 = "d:/Usuario/foto.png";
        urlVideo02 = "c:/Usuario/java.mp4";
        urlFoto02 = "www.google.com/imagens/imagem.jpg";
        controlPost = new ControllerPostagem();
    }

    @Test
    public void test(){
        assertEquals(true, controlPost.getUrlsFoto().isEmpty());
        controlPost.getUrlsFoto().add(urlFoto01);
        assertEquals(false, controlPost.getUrlsFoto().isEmpty());
        assertEquals(true, controlPost.getUrlsFoto().contains(urlFoto01));
        controlPost.getUrlsFoto().add(urlFoto02);
        assertEquals(2, controlPost.getUrlsFoto().size());
        assertEquals(true, controlPost.getUrlsFoto().contains(urlFoto02));
        controlPost.getUrlsFoto().remove(urlFoto01);
        assertEquals(1, controlPost.getUrlsFoto().size());
        assertNotEquals(true, controlPost.getUrlsFoto().contains(urlFoto01));
        controlPost.getUrlsFoto().remove(urlFoto02);
        assertEquals(false, controlPost.getUrlsFoto().contains(urlFoto02));
        assertEquals(true, controlPost.getUrlsFoto().isEmpty());
        
        assertEquals(true, controlPost.getUrlsVideo().isEmpty());
        controlPost.getUrlsVideo().add(urlVideo01);
        assertEquals(false, controlPost.getUrlsVideo().isEmpty());
        assertEquals(true, controlPost.getUrlsVideo().contains(urlVideo01));
        controlPost.getUrlsVideo().add(urlVideo02);
        assertEquals(2, controlPost.getUrlsVideo().size());
        assertEquals(true, controlPost.getUrlsVideo().contains(urlVideo02));
        controlPost.getUrlsVideo().remove(urlVideo01);
        assertEquals(1, controlPost.getUrlsVideo().size());
        assertNotEquals(true, controlPost.getUrlsVideo().contains(urlVideo01));
        controlPost.getUrlsVideo().remove(urlVideo02);
        assertEquals(false, controlPost.getUrlsVideo().contains(urlVideo02));
        assertEquals(true, controlPost.getUrlsVideo().isEmpty());
        
    }
}
