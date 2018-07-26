
package controller;

import java.util.LinkedList;

/**
 *A classe <b>ControllerPostagem</b> faz o gerenciamento da escolha de arquivos que o usu�rio poder� postar.
 * @author Matheus Nascimento e Elvis Serafim
 * 
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
     * @return
     */
    public LinkedList getUrlsVideo() {
        return urlsVideo;
    }

    /**
     *
     * @param urlsVideo
     */
    public void setUrlsVideo(LinkedList urlsVideo) {
        this.urlsVideo = urlsVideo;
    }

    /**
     *
     * @return
     */
    public LinkedList getUrlsFoto() {
        return urlsFoto;
    }

    /**
     *
     * @param urlsFoto
     */
    public void setUrlsFoto(LinkedList urlsFoto) {
        this.urlsFoto = urlsFoto;
    }
    
}
