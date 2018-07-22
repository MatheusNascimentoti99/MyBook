/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.LinkedList;

/**
 *
 * @author Usuário 01
 */
public class ControllerPostagem {
    private LinkedList urlsVideo;
    private LinkedList urlsFoto;

    public ControllerPostagem() {
        urlsFoto = new LinkedList();
        urlsVideo = new LinkedList();
    }

    public LinkedList getUrlsVideo() {
        return urlsVideo;
    }

    public void setUrlsVideo(LinkedList urlsVideo) {
        this.urlsVideo = urlsVideo;
    }

    public LinkedList getUrlsFoto() {
        return urlsFoto;
    }

    public void setUrlsFoto(LinkedList urlsFoto) {
        this.urlsFoto = urlsFoto;
    }
    
}
