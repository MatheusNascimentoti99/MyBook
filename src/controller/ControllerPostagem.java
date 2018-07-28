package controller;

import java.util.LinkedList;

/**
 * A classe <b>ControllerPostagem</b> gerência a escolha dos arquivos
 * que o usuário poderá postar.
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
     * @return Lista de vídeos anexados em uma postagem.
     */
    public LinkedList getUrlsVideo() {
        return urlsVideo;
    }

    /**
     * Método que retorna uma lista contendo Urls de fotos.
     * @return lista de urls de fotos.
     */
    public LinkedList getUrlsFoto() {
        return urlsFoto;
    }

}

