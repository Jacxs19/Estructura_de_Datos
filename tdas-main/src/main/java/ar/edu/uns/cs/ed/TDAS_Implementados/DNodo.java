package ar.edu.uns.cs.ed.TDAS_Implementados;
import ar.edu.uns.cs.ed.tdas.Position;

public class DNodo<E> implements Position<E>{
    private E elem;
    private DNodo<E> siguiente;
    private DNodo<E> anterior;

    //Constructor
    public DNodo(E elemento, DNodo<E> sig, DNodo<E> prev){
        elem=elemento;
        siguiente=sig;
        anterior=prev;
    }
    public DNodo(E elemento){
        this(elemento,null,null);
    }

    //Setters
    public void setElemento(E elemento){
        elem=elemento;
    }
    public void setSiguiente(DNodo<E> sig){
        siguiente=sig;
    }
    public void setAnterior(DNodo<E> ant){
        anterior=ant;
    }
    
    //Getters
    public E element(){
        return elem;
    }
    public DNodo<E> getSiguiente(){
        return siguiente;
    }
    public DNodo<E> getAnterior(){
        return anterior;
    }
}
