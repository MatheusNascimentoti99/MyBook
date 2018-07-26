/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
     *
     * A classe <b>Amizade</b> � utilizado para dar alguns atributos que podem influ�nciar na amizade entre dois usu�rios.
     * @author Matheus Nascimento e Elvis Serafim
     * @since Jul 2018
     * @version 1.0
     */
public class Amizade implements Serializable{
    private int afinidade;

    public Amizade(int afinidade) {
        this.afinidade = afinidade;
    }

    public int getAfinidade() {
        return afinidade;
    }

    public void setAfinidade(int afinidade) {
        this.afinidade = afinidade;
    }
    
}
