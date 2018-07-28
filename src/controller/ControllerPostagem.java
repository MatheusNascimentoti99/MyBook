
package controller;

import java.util.LinkedList;

/**
 *A classe <b>ControllerPostagem</b> faz o gerenciamento da escolha de arquivos que o usuário poderá postar.
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
     * Método que retorna uma lista contendo Urls de videos.
     * @return lista de urls de videos.
     */
    public LinkedList getUrlsVideo() {
        return urlsVideo;
    }

    /**
     * Método que designa uma nova lista de urls de videos.
     * @param urlsVideo nova lista de urls.
     */
    public void setUrlsVideo(LinkedList urlsVideo) {
        this.urlsVideo = urlsVideo;
    }

    /**
     * Método que retorna uma lista contendo Urls de fotos.
     * @return lista de urls de fotos.
     */
    public LinkedList getUrlsFoto() {
        return urlsFoto;
    }

  /**
     * Método que designa uma nova lista de urls de fotos.
     * @param urlsFoto nova lista de urls.
     */
    public void setUrlsFoto(LinkedList urlsFoto) {
        this.urlsFoto = urlsFoto;
    }
    
}
