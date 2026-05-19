package ar.edu.uns.cs.ed.TpN7.ej4;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.tdaarbolbinario.BinaryTree;
import ar.edu.uns.cs.ed.tdas.Position;

public class metodo<E> {

    public void completarDerechos(E r, BinaryTree<E> t){
        if(t.isEmpty())
            throw new EmptyTreeException("Arbol vacio");
        recorridoCompletar(r, t, t.root());
        

        }
    
    
    private void recorridoCompletar(E r, BinaryTree<E> t, Position<E> p){           //Recorrido post-orden
        if(t.hasLeft(p))            //C1
            recorridoCompletar(r,t,t.left(p));      //n
        if(t.hasRight(p))           //C2
            recorridoCompletar(r,t,t.right(p));     //n
        if(t.hasLeft(p)&&!t.hasRight(p)){           //C2
            t.addRight(p, r);                       //n
        }
    }
    //T_recorridoCompletar(n)= En el peor de los casos, en un arbol de tamaño n todos sus nodos 
    //tienen hijos izquierdos pero no tienen hijos derechos, por lo que el recorrido seria de O(n)
    //ya que visito a todos sus nodos independientemente de si tienen hijos derechos o no.  (Es un recorrido lineal)
}