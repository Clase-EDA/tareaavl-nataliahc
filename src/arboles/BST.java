/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author Natalia Hernández
 */
public class BST<T extends Comparable<T>> extends LinkedBT<T> implements BSTADT<T> {

    private NodoBT<T> raiz;
    private int cont = 0;

    public void add(T elem) {
        cont++;
        if (raiz == null) {
            raiz = new NodoBT(elem);
        } else {

            NodoBT<T> papa = null;
            NodoBT<T> aux = raiz;

            while (aux != null) {
                papa = aux;
                if (elem.compareTo(aux.getElement()) >= 0) {
                    aux = aux.getDer();
                } else {
                    aux = aux.getIzq();
                }
            }//while
            aux = new NodoBT(elem);
            papa.cuelga(aux);
        }
    }

    private NodoBT<T> busca(NodoBT<T> actual, T elem) {

        while (actual != null && actual.getElement().compareTo(elem) != 0) {
            if (elem.compareTo(actual.getElement()) < 0) {
                actual = actual.getIzq();
            } else if (elem.compareTo(actual.getElement()) > 0) {
                actual = actual.getDer();
            }
        }
        return actual;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T find(T elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(T elem) {
        NodoBT<T> quitar = busca(raiz, elem);
        boolean res = false;
        if (quitar != null) {
            res = true;
            cont--;
            if (quitar.getDer() == null && quitar.getIzq() == null) { // sin hijos
                if (raiz == quitar) {
                    raiz = null;
                } else {
                    if (elem.compareTo(quitar.getPapa().getElement()) < 0) {
                        quitar.getPapa().setIzq(null);
                    } else {
                        quitar.getPapa().setDer(null);
                    }
                }
            } else {
                if (quitar.getIzq() == null) { // solo tiene hijo derecho
                    if (quitar == raiz) {
                        raiz = quitar.getDer();
                        raiz.setPapa(null);
                    } else {
                        quitar.getPapa().cuelga(quitar.getDer());
                    }
                } else {
                    if (quitar.getDer() == null) { // solo tiene hijo izquierdo
                        if (quitar == raiz) {
                            raiz = quitar.getIzq();
                            raiz.setPapa(null);
                        } 
                        else 
                            quitar.getPapa().cuelga(quitar.getIzq());
                        
                    }
                    else { // tiene dos hijos
                        NodoBT<T> inOrden=quitar.getDer();
                        while(inOrden.getIzq()!=null)
                            inOrden=inOrden.getIzq();
                        quitar.setElement(inOrden.getElement());
                        if(inOrden==quitar.getDer()) 
                            quitar.setDer(inOrden.getDer());
                        else
                            quitar.getPapa().setIzq(inOrden.getDer());
                        
                    }
                }
            }
        }
        return res;
    }

    @Override
    public T removeMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T removeMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T findMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T findMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
