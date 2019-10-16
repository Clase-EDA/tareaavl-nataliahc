/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author Natalia Hernández
 */
public class Arboles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolAVL<Integer> arbol = new ArbolAVL();
        arbol.inserta(1);
        arbol.inserta(2);
        arbol.imprime();

        arbol.borrar(1);
        arbol.imprime();


    }
}
