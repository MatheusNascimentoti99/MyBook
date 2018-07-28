/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

/**
     *
     * A classe <b>Postagem</b> Contém todos os dados necessários para a realização de uma postagem.
     *
     * @author Matheus Nascimento e Elvis Serafim
     * @since Jul 2018
     * @version 1.0
     */

public class Postagem implements Serializable{
    private String textoPostagem;
    private LinkedList urlImagem;
    private LinkedList urlVideo;
    private Date dataPostagem;
    private Date horaPostagem;

    /**
     *
     */
    public Postagem() {
        this.urlImagem = new LinkedList();
        this.urlVideo = new LinkedList();
    }

    /**
     *
     * @return Retorna o horário da postagem.
     */
    public Date getHoraPostagem() {
        return horaPostagem;
    }

    /**
     *
     * @param horaPostagem Altera o horário da postagem.
     */
    public void setHoraPostagem(Date horaPostagem) {
        this.horaPostagem = horaPostagem;
    }
    
    /**
     *
     * @return Retorna a data da postagem.
     */
    public Date getDataPostagem() {
        return dataPostagem;
    }

    /**
     *
     * @param dataPostagem Altera a data da postagem.
     */
    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
    }
    
    /**
     *
     * @return Retorna o texto da postagem.
     */
    public String getTextoPostagem() {
        return textoPostagem;
    }

    /**
     *
     * @param textoPostagem Altera o texto da postagem.
     */
    public void setTextoPostagem(String textoPostagem) {
        this.textoPostagem = textoPostagem;
    }

    /**
     *
     * @return Retorna a lista com os caminhos das imagens utilizadas na postagem.
     */
    public LinkedList getUrlImagem() {
        return urlImagem;
    }

    /**
     *
     * @param urlImagem Altera a lista de URL's de imagens.
     */
    public void setUrlImagem(LinkedList urlImagem) {
        this.urlImagem = urlImagem;
    }

    /**
     *
     * @return Retorna a lista com os caminhos dos vídeos utilizadas na postagem.
     */
    public LinkedList getUrlVideo() {
        return urlVideo;
    }

    /**
     *
     * @param urlVideo Altera a lista de URL's de vídeos.
     */
    public void setUrlVideo(LinkedList urlVideo) {
        this.urlVideo = urlVideo;
    }

    @Override
    public int hashCode() {
        return urlVideo.hashCode() + urlImagem.hashCode() + textoPostagem.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Postagem other = (Postagem) obj;
        if (!Objects.equals(this.textoPostagem, other.textoPostagem)) {
            return false;
        }
        if (!Objects.equals(this.urlImagem, other.urlImagem)) {
            return false;
        }
        if (!Objects.equals(this.urlVideo, other.urlVideo)) {
            return false;
        }
        if (!Objects.equals(this.dataPostagem, other.dataPostagem)) {
            return false;
        }
        if (!Objects.equals(this.horaPostagem, other.horaPostagem)) {
            return false;
        }
        return true;
    }

 
}
