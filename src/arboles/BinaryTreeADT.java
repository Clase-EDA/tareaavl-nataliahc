/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.Iterator;

/**
 *
 * @author Natalia Hernández
 */
public interface BinaryTreeADT <T> {
    
    public boolean isEmpty();
    public int size();
    public boolean contains();
    public T find(T elemento);
    
    public Iterator<T> inorden();
    public Iterator<T> preorden();
    public Iterator<T> postorden();
     
}
