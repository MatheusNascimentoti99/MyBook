
package util;

import java.util.Iterator;



/**
 * Interface <b>IHashTable</b> .
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
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
