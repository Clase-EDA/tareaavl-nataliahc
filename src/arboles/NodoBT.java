/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author Natalia Hernández
 */
public class NodoBT <T extends Comparable<T>> {
    private T element;
    private NodoBT<T> izq, der, papa;
    NodoBT(T elem) {
        element=elem;
        izq=null;
        der=null;
        papa=null;
    }

    public T getElement() {
        return element;
    }

    public NodoBT<T> getIzq() {
        return izq;
    }

    public NodoBT<T> getDer() {
        return der;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setIzq(NodoBT<T> izq) {
        this.izq = izq;
    }

    public void setDer(NodoBT<T> der) {
        this.der = der;
    }

    public NodoBT<T> getPapa() {
        return papa;
    }

    public void setPapa(NodoBT<T> papa) {
        this.papa = papa;
    }
    
    public void cuelga(NodoBT<T> n) {
        if(n.getElement().compareTo(element)<0) {
            izq=n;
        }
        else {
            der=n;
        }
        n.setPapa(this);
    }
    
}
