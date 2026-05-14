package ar.edu.uns.cs.ed.TpN6.ej6;

import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TpN5.ej2.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.TpN6.ej1.TDAArbol;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import java.util.Iterator;

public class metodos {

    public boolean pertenece (Tree<Integer> a, Integer n){
        boolean pertenece=false;
        Iterator<Integer> it = a.iterator();
        while(it.hasNext() && !pertenece){
            Integer numero =it.next();
            if(numero.equals(n))
                pertenece=true;
        }
        return pertenece;
    }
    //Este metodo es O(k) (u O(n)),
    //donde k es la cantidad de nodos del arbol.
    //En el peor caso el elemento no pertenece al arbol
    //(o se encuentra en el ultimo nodo),
    //por lo que se recorren todos los nodos.
}
