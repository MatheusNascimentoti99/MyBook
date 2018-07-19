/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Iterator;




/**
 *
 * @author Matheus Nascimento
 */
public interface IHashTable {

    public void put(int key, Object value);

    public Object get(int key);
    
    public boolean contains( Object value);
    
    public boolean removeKey(int key);

    public void removeValue(Object value);

    public boolean isEmpty();

    public int size();
    
    public Iterator iterator();

}
