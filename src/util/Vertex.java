package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import model.User;

/**
 *
 * A classe <b>Vertex</b> � responsavel pela cria��o e manuten��o de um n� do grafo.
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
     * Construtor da classe <b>Vertex</b> . � recebido por par�metro uma chave e um valor, para ser armazenado no v�rtice. Tamb�m � criado no construtor um 
     * HashMap de arestas para um novo v�rtice.
     * @param key chave do tipo inteiro.
     * @param value valor, do tipo object.
     */
    public Vertex(int key, Object value) {
        this.key = key;
        this.value = value;
        arestas = new HashMap();
    }

    /**
     * M�todo que retorna a HashMap de arestas de um v�rtice.
     * @return HashMap de arestas.
     */
    public HashMap getArestas() {
        return arestas;
    }

    /**
     * M�todo que designa um novo HashMap de arestas para um v�rtice.
     * @param arestas novo HashMap de arestas.
     */
    public void setArestas(HashMap arestas) {
        this.arestas = arestas;
    }

    /**
     * M�todo que retorna a chave de referencia do valor que � armazenado no v�rtice.
     * @return chave do valor armazenado.
     */
    public int getKey() {
        return key;
    }

    /**
     * M�todo que designa uma nova chave para o valor armazenado no v�rtice.
     * @param key nova chave.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * M�todo que retorna o valor armazenado no v�rtice.
     * @return Object valor.
     */
    public Object getValue() {
        return value;
    }

    /**
     * M�todo que designa um novo valor para o v�rtice.
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
