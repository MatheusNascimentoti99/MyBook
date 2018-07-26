package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import model.User;

/**
 *
 * A classe <b>Vertex</b> é responsavel pela criação e manutenção de um nó do grafo.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class Vertex implements Serializable{

    private int key;
    private Object value;
    private HashMap arestas;
    public Vertex(int key, Object value) {
        this.key = key;
        this.value = value;
        arestas = new HashMap();
    }

    public HashMap getArestas() {
        return arestas;
    }

    public void setArestas(HashMap arestas) {
        this.arestas = arestas;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Vertex){
            return (this.hashCode() == o.hashCode());
        } else if( o instanceof User){
            return o.hashCode() == key;
        } 
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.key;
        hash = 73 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
}
