package ar.edu.uns.cs.ed.TpN6.ej4;

import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;

public class metodos {

    public Iterable<Position<String>> posicionesIgualString(Tree<String> a, String s){ 
        PositionList<Position<String>> listaIterable = new ListaDoblementeEnlazada<Position<String>>();
        PositionList<Position<String>> listaEnPostOrden = new ListaDoblementeEnlazada<Position<String>>();
        postOrden(listaEnPostOrden, a.root(), a);
        for(Position<String> posiciones : listaEnPostOrden){
            if(posiciones.element().equals(s)){
                listaIterable.addLast(posiciones);
            }
        }
        return listaIterable;     
    }

    private void postOrden(PositionList<Position<String>> lista , Position<String> n, Tree<String> a){
        for(Position<String> pos : a.children(n)){
            postOrden(lista,pos,a);
        }
        lista.addLast(n); 
    }

}