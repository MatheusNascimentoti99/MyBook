/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Objects;

/**
 *
 * @author Matheus Nascimento
 */
public class Edge {
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

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 103 * hash + Objects.hashCode(this.pre);
        hash = 103 * hash + Objects.hashCode(this.corrent);
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
