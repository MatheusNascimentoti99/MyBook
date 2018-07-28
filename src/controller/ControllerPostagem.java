package controller;

import java.util.LinkedList;

/**
 * A classe <b>ControllerPostagem</b> ger�ncia a escolha dos arquivos
 * que o usu�rio poder� postar.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class ControllerPostagem {

    private LinkedList urlsVideo;
    private LinkedList urlsFoto;

    /**
     *
     */
    public ControllerPostagem() {
        urlsFoto = new LinkedList();
        urlsVideo = new LinkedList();
    }

    /**
     *
     * @return Lista de v�deos anexados em uma postagem.
     */
    public LinkedList getUrlsVideo() {
        return urlsVideo;
    }

    /**
     *
     * @return Lista de imagens anexadas em uma postagem.
     */
    public LinkedList getUrlsFoto() {
        return urlsFoto;
    }

}
