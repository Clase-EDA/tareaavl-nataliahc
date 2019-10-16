/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.ArrayList;

/**
 *
 * @author Natalia Hernández
 */
public class ArbolAVL<T extends Comparable<T>> extends LinkedBT<T> implements BSTADT<T> {

    NodoAVL<T> raiz;
    int cont;

    public ArbolAVL() {
        raiz = null;
        cont = 0;
    }

    /////////////////////////////////// imprimir
    public void imprime() {
        int h = altura(); 
        for (int i=1; i<=h; i++) 
            imprime(raiz, i);
    }

    private void imprime(NodoAVL<T> actual, int nivel) {
        if (actual == null) 
            return; 
        if (nivel == 1) 
            System.out.print(actual.getElement().toString() + "\n"); 
        else if (nivel > 1) 
        { 
            imprime(actual.getIzq(), nivel-1); 
            imprime(actual.getDer(), nivel-1); 
        } 
    }
    
    // altura
    public int altura() {
        return altura(raiz, 0);
    }

    private int altura(NodoAVL actual, int altura) {
        if (actual == null) {
            return altura;
        } 
        return 1+Math.max(altura(actual.getIzq(), altura), altura(actual.getDer(), altura));
    }
    
    
    ////////////////////////////////////////// insertar
    public void inserta(T elem) {
        if (cont == 0) {
            raiz = new NodoAVL(elem);
        } else {

            NodoAVL<T> actual = buscaYPon(raiz, elem);
            boolean termine = false;
            while (!termine && actual!=raiz) {
                if (actual == actual.getPapa().getIzq()) {
                    actual.getPapa().fe -= 1;
                } else {
                    actual.getPapa().fe += 1;
                }
                if (actual.getPapa().fe == 2) {
                    actual=rotacion(actual.getPapa());
                    termine = true;
                } else if (actual.getPapa().fe == 0) {
                    termine = true;
                }
                actual = actual.getPapa();
            }
        }
        cont++;
    }
    
    

    private NodoAVL<T> buscaYPon(NodoAVL<T> actual, T elem) {
        NodoAVL<T> nuevo = new NodoAVL(elem);
        NodoAVL<T> papa = actual;

        while (actual != null) {
            papa = actual;
            if (nuevo.compareTo(actual) < 0) {
                actual = actual.getIzq();
            } else {
                actual = actual.getDer();
            }
        }

        papa.cuelga(nuevo);
        return nuevo;
    }
    
    private NodoAVL<T> rotacion(NodoAVL<T> actual) {
        NodoAVL<T> alfa, beta, gamma, A, B, C, D, papa;
        if(actual!=null){
            //iz-izq
            if(actual.getFe() ==-2 &&(actual.getIzq()!=null && actual.getIzq().getFe()<=0)){
                alfa = actual;
                papa = actual.getPapa();
                beta = alfa.getIzq();
                gamma = beta.getIzq();
                A = gamma.getIzq();
                B = gamma.getDer();
                C= beta.getDer();
                D = alfa.getDer();

                gamma.cuelga(A);
                gamma.cuelga(B);
                alfa.cuelga(C);
                alfa.cuelga(D);
                beta.cuelga(alfa);
                beta.cuelga(gamma);

                if(papa!=null){
                    papa.cuelga(beta);
                }
                else{
                    beta.setPapa(null);
                    raiz = beta;
                }
                actualizaFe(alfa);
                actualizaFe(beta);
                actualizaFe(gamma);
                return beta;
            }
            //der-der
            if(actual.getFe()==2 &&(actual.getDer()!=null && actual.getDer().getFe()>=0)){
                alfa = actual;
                beta = alfa.getDer();
                gamma = beta.getDer();
                papa = actual.getPapa();
                A=alfa.getIzq();
                B = beta.getIzq();
                C = gamma.getDer();
                D=gamma.getIzq();

                alfa.cuelga(A);
                alfa.cuelga(B);
                beta.cuelga(alfa);
                beta.cuelga(gamma);
                gamma.cuelga(C);
                gamma.cuelga(D);

                if(papa!=null)
                    papa.cuelga(beta);
                else{
                    beta.setPapa(null);
                    raiz = beta;
                }
                actualizaFe(alfa);
                actualizaFe(beta);
                actualizaFe(gamma);
                return beta;
            }
            //izq-der
            if(actual.getFe()==-2&&(actual.getIzq()!=null && actual.getIzq().getFe()==1)){
                alfa = actual;
                beta = alfa.getIzq();
                gamma = beta.getDer();
                papa=alfa.getPapa();
                A = beta.getIzq();
                B= gamma.getIzq();
                C=gamma.getDer();
                D = alfa.getDer();

                beta.cuelga(A);
                beta.cuelga(B);
                alfa.cuelga(C);
                alfa.cuelga(D);
                gamma.cuelga(alfa);
                gamma.cuelga(beta);

                if(papa!=null)
                    papa.cuelga(gamma);
                else{
                    gamma.setPapa(null);
                    raiz = gamma;
                }
                actualizaFe(alfa);
                actualizaFe(beta);
                actualizaFe(gamma);
                return gamma;
            }
            //der-izq
            if(actual.getFe()==2&&(actual.getDer()!=null && actual.getDer().getFe()==-1)){
                alfa= actual;
                beta = alfa.getDer();
                gamma=beta.getIzq();
                papa = alfa.getPapa();
                A=alfa.getIzq();
                B=gamma.getIzq();
                C = gamma.getDer();
                D = beta.getDer();

                gamma.cuelga(alfa);
                gamma.cuelga(beta);
                alfa.cuelga(A);
                alfa.cuelga(B);
                beta.cuelga(C);
                beta.cuelga(D);

                if(papa!=null)
                    papa.cuelga(gamma);
                else{
                    gamma.setPapa(null);
                    raiz= gamma;
                }
                actualizaFe(alfa);
                actualizaFe(beta);
                actualizaFe(gamma);
                return gamma;
            }
        }
        
        return null;
        
    }
    
    public void actualizaFe(NodoAVL<T> actual){
        int altDer = 0, altIzq = 0;
        if (actual.getDer() != null) 
            altDer = altura(actual.getDer(), 0);
        if (actual.getIzq() != null) 
            altIzq = altura(actual.getIzq(), 0);
        actual.setFe( altDer - altIzq);
    }
    
    
    /////////////////////////////////////////// borrar
    public boolean borrar(T elem){ 
        if(elem!= null){
            NodoAVL<T> actual;
            actual = soloBusca(raiz, elem);
            if(actual==null) 
                return false;     
            NodoAVL<T> papa = actual.getPapa();
            cont--;
            
            if(actual.getDer()!=null && actual.getIzq()!=null){//dos hijos
                NodoAVL<T> aux = actual.getDer();
                while(aux.getIzq()!=null)
                    aux=aux.getIzq();
                actual.setElement(aux.getElement());
                if(aux==actual.getDer()){
                    actual.setDer(aux.getDer());
                    actualizaFeBorrar(actual, -1);
                }
                else{
                    aux.getPapa().setIzq(aux.getDer());
                    actualizaFeBorrar(actual, 1);
                }

                aux.setPapa(null);
                aux.setIzq(null);
                aux.setDer(null);
            }
            else{
                if(actual.getDer()==null && actual.getIzq()==null){ //hoja
                    if(actual==raiz)
                        raiz=null;
                    else{

                        if((papa.getDer()!=null) && papa.getDer().getElement().equals(elem)){ 
                            papa.setDer(null);
                            actualizaFeBorrar(papa, -1);
                        }
                        else{
                            papa.setIzq(null);
                            actualizaFeBorrar(papa, 1);
                        }
                        actual.setElement(null);
                        actual.setPapa(null);
                    }
                }
                else{ // un hijo

                    actual.setElement(null);

                    if(actual.getDer()!=null){ //hijo derecho
                        if(actual==raiz){
                            raiz = actual.getDer();
                            raiz.setPapa(null);
                        }
                        else{
                            papa.cuelga(actual.getDer());
                            actualizaFeBorrar(papa,-1);
                        }
                        actual.setDer(null);
                    }

                    else{ //hijo izquierdo
                        if(actual==raiz){
                            raiz = actual.getDer();
                            raiz.setPapa(null);
                        }
                        else{
                            papa.cuelga(actual.getIzq());
                            actualizaFeBorrar(papa, 1);
                        }
                        actual.setIzq(null);
                    }
                    actual.setPapa(null);
                }
            }

            return true;
        }
        else
            return false;
    }
    
    // buscar
    private NodoAVL<T> soloBusca(NodoAVL<T> actual, T elem) {

        while (actual != null && elem.compareTo(actual.getElement())!=0) {
            if (elem.compareTo(actual.getElement()) < 0) {
                actual = actual.getIzq();
            } else {
                actual = actual.getDer();
            }
        }
        
        return actual;
    }

   private void actualizaFeBorrar(NodoAVL<T> actual, int i){
       boolean termine = false;
       while(actual!=null && !termine){
           actual.setFe(actual.getFe()+i);
           if(Math.abs(actual.getFe())==1)
               termine = true;
           else{
              if(Math.abs(actual.getFe())==2){ 
                  actual = rotacion(actual);
                  termine = true;
              }
              else{ 
                  if(actual!=raiz){
                      if(actual.getElement().compareTo(actual.getPapa().getElement())<0)
                          i = 1;
                      else
                          i = -1;
                      actual = actual.getPapa();
                  }
                  else 
                      termine= true;
                  
              }
           }
       }
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
    public void add(T elem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public boolean remove(T elem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
