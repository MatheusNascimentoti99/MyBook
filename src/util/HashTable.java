/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Matheus Nascimento e Elvis Nascimento Implementação retirada da aula
 * 08 de Estruturas de Dados, Profº Dr. João Rocha.
 */
public class HashTable implements IHashTable, Serializable {

    private final double LOAD_FACTOR = 0.5;
    private final Vertex EMPTY = new Vertex(-1, null);
    private Vertex[] entries;
    private int size;

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public boolean contains(Object value) {
        Iterator it = this.iterator();
        while(it.hasNext()){
            if(((Vertex)it.next()).equals(value))
                return true;
        }
        return false;
    }
    public class MyIterator implements Iterator {

        private final Vertex[] corrent;
        int size;

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
