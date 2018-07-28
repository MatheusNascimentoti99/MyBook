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
    
    /**
     * Construtor da classe <b>Vertex</b> . É recebido por parâmetro uma chave e um valor, para ser armazenado no vértice. Também é criado no construtor um 
     * HashMap de arestas para um novo vértice.
     * @param key chave do tipo inteiro.
     * @param value valor, do tipo object.
     */
    public Vertex(int key, Object value) {
        this.key = key;
        this.value = value;
        arestas = new HashMap();
    }

    /**
     * Método que retorna a HashMap de arestas de um vértice.
     * @return HashMap de arestas.
     */
    public HashMap getArestas() {
        return arestas;
    }

    /**
     * Método que designa um novo HashMap de arestas para um vértice.
     * @param arestas novo HashMap de arestas.
     */
    public void setArestas(HashMap arestas) {
        this.arestas = arestas;
    }

    /**
     * Método que retorna a chave de referencia do valor que é armazenado no vértice.
     * @return chave do valor armazenado.
     */
    public int getKey() {
        return key;
    }

    /**
     * Método que designa uma nova chave para o valor armazenado no vértice.
     * @param key nova chave.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Método que retorna o valor armazenado no vértice.
     * @return Object valor.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Método que designa um novo valor para o vértice.
     * @param value novo valor Object.
     */
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
