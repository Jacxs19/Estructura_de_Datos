package ar.edu.uns.cs.ed.TDAS_Implementados;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

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