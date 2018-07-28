package controller;

import java.util.LinkedList;

/**
 * A classe <b>ControllerPostagem</b> faz o gerenciamento da escolha de arquivos
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
     * Construtor da classe <b>ControllerPostagem</b> .
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
     * M�todo que designa uma nova lista de urls de videos.
     * @param urlsVideo nova lista de urls.
     */
    public void setUrlsVideo(LinkedList urlsVideo) {
        this.urlsVideo = urlsVideo;
    }

    /**
     * M�todo que retorna uma lista contendo Urls de fotos.
     * @return lista de urls de fotos.
     */
    public LinkedList getUrlsFoto() {
        return urlsFoto;
    }

}

