/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author Natalia Hernández
 */
public interface BSTADT <T> extends BinaryTreeADT<T> {
    public void add(T elem);
    public boolean remove(T elem);
    public T removeMin();
    public T removeMax();
    public T findMin();
    public T findMax();
    
}
