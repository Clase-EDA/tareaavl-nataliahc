/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author Natalia Hernández
 */
public class NodoAVL <T extends Comparable<T>> {
    public int fe;
    private T element;
    private NodoAVL<T> izq, der, papa;
    
    NodoAVL(T elem) {
        element=elem;
        izq=null;
        der=null;
        papa=null;
        fe=0;
    }

    public T getElement() {
        return element;
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public NodoAVL<T> getDer() {
        return der;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setIzq(NodoAVL<T> izq) {
        this.izq = izq;
    }

    public void setDer(NodoAVL<T> der) {
        this.der = der;
    }

    public NodoAVL<T> getPapa() {
        return papa;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getFe() {
        return fe;
    }

    public void setPapa(NodoAVL<T> papa) {
        this.papa = papa;
    }
    
    public void cuelga(NodoAVL<T> n) {
        if(n.getElement().compareTo(element)<0) {
            izq=n;
        }
        else {
            der=n;
        }
        n.setPapa(this);
    }
    
    public int compareTo(NodoAVL<T> otro) {
        return element.compareTo(otro.getElement());
    }
    
    
    
}
