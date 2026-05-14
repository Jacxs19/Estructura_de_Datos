package ar.edu.uns.cs.ed.TpN7.ej1;

import ar.edu.uns.cs.ed.tdas.Position;

public interface BTPosition<E> extends Position<E> {
    //Setters
    public void setElemento(E e);
    public void setPadre(BTPosition<E> p);
    public void setLeft(BTPosition<E> p);
    public void setRight(BTPosition<E> p);
    //Getters
    public E element();
    public BTPosition<E> getPadre();
    public BTPosition<E> getLeft();
    public BTPosition<E> getRight();
    
}
