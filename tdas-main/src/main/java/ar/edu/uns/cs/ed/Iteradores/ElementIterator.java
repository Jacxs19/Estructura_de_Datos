package ar.edu.uns.cs.ed.Iteradores;
import java.util.*;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
public class ElementIterator<E> implements Iterator<E>{
    protected PositionList<E> lista;
    protected Position<E> cursor;
    
    public ElementIterator(PositionList<E> l){
        lista=l;
        if(lista.isEmpty())
            cursor=null;
        else
            cursor=l.first();
    }
    // Devuelve true si hay algún elemento más para ver.
    // Hay siguiente si el cursor no está más allá de la última posición
    public boolean hasNext(){
        return cursor!=null;
    }
    // Devuelve el siguiente elemento y avanza el cursor. Falla si hasNext es falso.
    public E next(){
        if(cursor==null)
            throw new NoSuchElementException("Iterador de lista: No hay siguiente.");
        E resultado =cursor.element();
        cursor=(cursor == lista.last())? null : lista.next(cursor);
        return resultado;
    }
}

