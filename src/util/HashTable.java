
package util;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Classe <b>HashTable</b> , classe que implementa a interface <b>IGraph</b> . 
 * @author Matheus Nascimento e Elvis Nascimento.
 * Implementa��o baseada na aula
 * 08 de Estruturas de Dados, Prof� Dr. Jo�o Rocha.
 * @since Jul 2018.
 */
public class HashTable implements IHashTable, Serializable {

    private final double LOAD_FACTOR = 0.5;
    private final Vertex EMPTY = new Vertex(-1, null);
    private Vertex[] entries;
    private int size;

    /**
     * Construtor da classe <b>HashTable</b> . � recebido por par�metro o tamanho da HashTable.
     * @param size int tamanho da HashTable.
     */
    public HashTable(int size) {
        entries = new Vertex[size];
    }

    private int hashFunction(Vertex[] entrySet, Object key) {
        return Math.abs(key.hashCode() % entrySet.length);
    }

    private boolean isEmpty(int pos) {
        return entries[pos] == null || entries[pos].equals(EMPTY);
    }

    private double loadFactor() {
        return size() / (double) entries.length;
    }

    private int findPos(Object[] entrySet, int pos, Object e) {
        int firstEmpty = -1;
        if (loadFactor() > LOAD_FACTOR) {
            resize();
        }
        while (entries[pos] != null && !entries[pos].equals(e)) {
            if (firstEmpty == -1 && entries[pos].equals(EMPTY)) {
                firstEmpty = pos;
            }
            pos = (pos + 1) % entries.length;
        }
        if (entries[pos] == null && firstEmpty != -1) {
            return firstEmpty;
        } else {
            return pos;
        }
    }

    private void resize() {
        Object[] temp = entries;
        entries = new Vertex[entries.length * 2];
        for (Object e : temp) {
            if (e != null && !e.equals(EMPTY)) {
                putRefit(e);
            }
        }
    }

    private void putRefit(Object e) {
        int i = findPos(entries, hashFunction(entries, ((Vertex) e).getKey()), e);
        if (isEmpty(i)) {
            entries[i] = (Vertex) e;
            if (loadFactor() > LOAD_FACTOR) {
                resize();
            }
        }
    }

    /**
     * M�todo que adiciona um novo v�rtice a HashTable.
     * @param key chave do objeto que ser� armazenado no v�rtice.
     * @param value valor que ser� armazenado no v�rtice.
     */
    @Override
    public void put(int key, Object value) {
        Vertex e = new Vertex(key, value);
        int i = findPos(entries, hashFunction(entries, key), e);
        if (isEmpty(i)) {
            entries[i] = e;
            size++;
            if (loadFactor() > LOAD_FACTOR) {
                resize();
            }
        }
    }

    /**
     * M�todo que retorna um valor a partir de uma chave.
     * @param key chave do valor armazenado. 
     * @return objeto valor.
     */
    @Override
    public Object get(int key) {
        try {
            return entries[hashFunction(entries, key)].getValue();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private int hash(Object key) {
        return hashFunction(entries, key);
    }

    /**
     * M�todo que remove um valor armazenado em um v�rtice da HashTable a partir de uma dada chave.
     * @param key chave do valor a ser removido;
     * @return true, caso seja removido, e false, caso n�o.
     */
    @Override
    public boolean removeKey(int key) {
        for (int i = hash(key); entries[i] != null; i = (i + 1) % entries.length) {
            if (entries[i].getKey() == key) {
                int j = (i + 1) % entries.length;
                int k = i;
                while (entries[j] != null) {
                    if (hash(entries[j].getKey()) == hash(entries[i].getKey())) {
                        k = j;
                    }
                    j = (j + 1) % entries.length;
                }
                if (k != i) {
                    entries[i] = entries[k];
                    entries[k] = null;
                } else {
                    entries[i] = null;
                }
                size--;
                return true;
            }
        }
        return false;

    }

    /**
     * M�todo que remove um valor de um v�rtice da HashTable.
     * @param value valor a ser removido.
     */
    @Override
    public void removeValue(Object value) {
        int i = 0;
        for (Object aux : entries) {
            if (aux != null) {
                if (((Vertex) aux).getValue().equals(value)) {
                    entries[i] = EMPTY;
                    size--;
                    break;
                }
            }
            i++;
        }
    }

    /**
     * M�todo que verifica se a HashTable est� vazia.
     * @return true, caso esteja, e false caso n�o.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * M�todo que retorna a quantidade de v�rtices armazenados na HashTable.
     * @return quantidade de v�rtices.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * M�todo que retorna o iterator da HashTable.
     * @return iterator.
     */
    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    /**
     * M�todo que verifica se a HashTable cont�m um objeto.
     * @param value objeto a ser verificado
     * @return true, caso esteja na HashTable, e false, caso n�o.
     */
    @Override
    public boolean contains(Object value) {
        Iterator it = this.iterator();
        while(it.hasNext()){
            if(((Vertex)it.next()).equals(value))
                return true;
        }
        return false;
    }
    
    /**
     * classe <b>MyIterator</b> , subclasse da classe <b>HashTable</b> .
     */
    public class MyIterator implements Iterator, Serializable {

        private final Vertex[] corrent;
        int size;

        /**
         * Construtor da classe <b>MyIterator</b> .
         */
        public MyIterator() {
            corrent = entries;
            size = 0;
        }

        @Override
        public boolean hasNext() {
            while (size < corrent.length) {
                if (corrent[size] != null) {
                    return true;
                }
                size++;
            }
            return false;
        }

        @Override
        public Vertex next() {

            return corrent[size++];
        }
    }

}
