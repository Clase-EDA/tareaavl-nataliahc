/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Natalia Hernández
 */
public class LinkedBT<T> implements BinaryTreeADT<T> {

    private NodoBT raiz;
    private int cont;

    public int size() {
        return cont;
    }

    public Iterator<T> preorden() {
        ArrayList<T> lista = new ArrayList<T>();
        preorden(raiz, lista);
        return lista.iterator();

    }

    private void preorden(NodoBT actual, ArrayList<T> lista) {
        if (actual == null) {
            return;
        }
        lista.add((T) actual.getElement());
        preorden(actual.getIzq(), lista);
        preorden(actual.getDer(), lista);
    }

    public Iterator<T> preorden2() {
        Stack<NodoBT> pila = new Stack();
        ArrayList<T> lista = new ArrayList<T>();
        pila.push(raiz);
        while (!pila.isEmpty()) {
            NodoBT actual = pila.pop();
            lista.add((T) actual.getElement());
            if (actual.getDer() != null) {
                pila.add(actual.getDer());
            }
            if (actual.getIzq() != null) {
                pila.add(actual.getIzq());
            }
        }
        return lista.iterator();
    }

    public int altura() {
        return altura(raiz, 0);
    }

    private int altura(NodoBT actual, int altura) {
        if (actual == null) {
            return altura;
        } 
        return 1+Math.max(altura(actual.getIzq(), altura), altura(actual.getDer(), altura));

    }

    public Iterator<T> postorden() {
        ArrayList<T> lista = new ArrayList<T>();
        postorden(raiz, lista);
        return lista.iterator();

    }

    private void postorden(NodoBT actual, ArrayList<T> lista) {
        if (actual == null) {
            return;
        }
        postorden(actual.getIzq(), lista);
        postorden(actual.getDer(), lista);
        lista.add((T) actual.getElement());
    }

    public Iterator<T> inorden() {
        ArrayList<T> lista = new ArrayList<T>();
        inorden(raiz, lista);
        return lista.iterator();

    }

    private void inorden(NodoBT actual, ArrayList<T> lista) {
        if (actual == null) {
            return;
        }
        inorden(actual.getIzq(), lista);
        lista.add((T) actual.getElement());
        inorden(actual.getDer(), lista);
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
}
