/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * A classe <b>Amizade</b> é utilizado para dar alguns atributos que podem
 * influênciar na amizade entre dois usuários. Classe ainda sem funcionalidade no projeto.
 * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class Amizade implements Serializable {

    private int afinidade;

   
    public Amizade(int afinidade) {
        this.afinidade = afinidade;
    }

    /**
     *
     * @return Retorna o nível de afinidade em uma amizade.
     */
    public int getAfinidade() {
        return afinidade;
    }

    /**
     *
     * @param afinidade Altera o nível de afinidade em uma amizade.
     */
    public void setAfinidade(int afinidade) {
        this.afinidade = afinidade;
    }

}
