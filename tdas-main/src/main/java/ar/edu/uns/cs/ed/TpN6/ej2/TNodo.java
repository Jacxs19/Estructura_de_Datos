package ar.edu.uns.cs.ed.TpN6.ej2;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;

public class TNodo<E> implements Position<E>{
    private E elem;
    private TNodo<E> padre;
    private PositionList<TNodo<E>> hijos;

    public TNodo(E elemento, TNodo<E> padre){
        elem=elemento;
        this.padre=padre;
        hijos = new ListaDoblementeEnlazada<TNodo<E>> ();
    }
    public TNodo(E elemento){
        this(elemento, null);
    }
    public E element(){
        return elem;
    }
    public PositionList<TNodo<E>> getHijos(){
        return hijos;
    }
    public void setElemento(E elemento){
        elem= elemento;
    }
    public TNodo<E> getPadre(){
        return padre;
    }
    public void setPadre(TNodo<E> padre){
        this.padre=padre;
    }
}