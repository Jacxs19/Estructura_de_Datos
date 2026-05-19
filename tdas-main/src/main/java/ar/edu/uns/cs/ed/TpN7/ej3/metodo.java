package ar.edu.uns.cs.ed.TpN7.ej3;

import ar.edu.uns.cs.ed.tdas.tdaarbolbinario.BinaryTree;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TpN5.ej3.DiccionarioConHashAbierto;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Dictionary;


public class metodo {

    public Iterable<Character> notacionInfija(BinaryTree<Character> A){
        PositionList<Character> lista = new ListaDoblementeEnlazada<Character>()
        if (A!=null && !A.isEmpty()){
            recorrido (lista, A, A.root());
        }
        return lista;
    }

    private void recorrido(PositionList<Character> l , BinaryTree<Character> A, Position<Character> p){         //InOrden
        if(p.element()!=null){
            Character c = p.element();
            if(A.hasLeft(p))
                recorrido(l,A,A.left(p));
            l.addLast(c);
            if(A.hasRight(p))
                recorrido(l,A,A.right(p));
        }
    }
    
}
