package ar.edu.uns.cs.ed.PracticaParciales.parcial1.ej1;
import ar.edu.uns.cs.ed.TpN4.Ej1.DNodo;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;

public class ListaDE<E> implements PositionList<E>{
    protected DNodo<E> head;
    protected DNodo<E> tail;
    protected int cantElems;

    public ListaDE(){
        DNodo<E> head = new DNodo<E> (null, null,null);
        DNodo<E> tail = new DNodo<E> (null, null, head);
        head.setSiguiente(tail);
        cantElems=0;
    }

    public PositionList<E> dividirLista(Position<E> pos){
        if(isEmpty())
            throw new InvalidPositionException("Lista vacia");
        PositionList<E> listaAux = new ListaDE<E>();
        DNodo<E> nodoAux= (DNodo<E>)pos;
        nodoAux.getAnterior().setSiguiente(tail);
        tail.setAnterior(nodoAux.getAnterior());
        while(nodoAux!=tail){
            DNodo<E> sig = nodoAux.getSiguiente();
            if(nodoAux.element()==null){
                throw new NullPointerException("Elemento nulo.");
            }
            listaAux.addLast(nodoAux.element());
            nodoAux.setElemento(null);
            cantElems--;
        }
        return listaAux;
    }
    public void addLast (E elemento){
        DNodo<E> nodoAux = new DNodo<E>(elemento);
        DNodo<E> ultimo = tail.getAnterior();
        //Cambio de "ultimo"
        ultimo.setSiguiente(nodoAux);
        nodoAux.setAnterior(ultimo);
        nodoAux.setSiguiente(tail);
        tail.setAnterior(nodoAux);
        cantElems++;
    }

}