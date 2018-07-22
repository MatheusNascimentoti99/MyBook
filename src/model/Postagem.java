/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Matheus Nascimento
 */

public class Postagem implements Serializable{
    private String textoPostagem;
    private LinkedList urlImagem;
    private LinkedList urlVideo;

    public String getTextoPostagem() {
        return textoPostagem;
    }

    public void setTextoPostagem(String textoPostagem) {
        this.textoPostagem = textoPostagem;
    }

    public LinkedList getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(LinkedList urlImagem) {
        this.urlImagem = urlImagem;
    }

    public LinkedList getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(LinkedList urlVideo) {
        this.urlVideo = urlVideo;
    }

 
}
