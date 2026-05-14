package ar.edu.uns.cs.ed.TpN6.ej5;

import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TpN5.ej2.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.TpN6.ej1.TDAArbol;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import java.util.Iterator;

public class metodo<E> {
    public int eliminarApariciones(Tree<E> a, E e){
        int cant=0;
        Iterator<Position<E>> it = a.positions().iterator();
        PositionList<Position<E>> listaAux= new ListaDoblementeEnlazada<Position<E>>();
        while(it.hasNext()){
            Position<E> posicion= it.next();
            if(posicion.element().equals(e)){ 
                cant++;
                listaAux.addLast(posicion);
            }
        }
        for(Position<E> n : listaAux){
            a.removeNode(n);
        }
        return cant;
    }
    
}
