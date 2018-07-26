/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * A classe <b>Edge</b> é responsavel para criar uma adjacência entre dois vertices.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class Edge implements Serializable {

    private Vertex pre;
    private Vertex corrent;
    private Object peso;

    public Edge(Vertex pre, Vertex corrent, Object peso) {
        this.pre = pre;
        this.corrent = corrent;
        this.peso = peso;
    }

    public Vertex getPre() {
        return pre;
    }

    public void setPre(Vertex pre) {
        this.pre = pre;
    }

    public Vertex getCorrent() {
        return corrent;
    }

    public void setCorrent(Vertex corrent) {
        this.corrent = corrent;
    }

    public Object getPeso() {
        return peso;
    }

    public void setPeso(Object peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = hash + pre.hashCode();
        hash = hash + corrent.hashCode();
        return hash;
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
        final Edge other = (Edge) obj;
        return true;
    }

}
