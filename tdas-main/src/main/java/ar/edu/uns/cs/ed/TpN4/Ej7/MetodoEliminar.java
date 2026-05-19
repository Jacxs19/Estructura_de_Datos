package ar.edu.uns.cs.ed.TpN4.Ej7;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TDAS_Implementados.DNodo;



public class MetodoEliminar<E> {
    
    private DNodo<E> header;
    private int tamanio;

    public void eliminar(PositionList<E> l1 , PositionList<E> l2){
        if(l1!=null && l2!=null){ 
            PositionList<Position<E>> listaAux= new ListaDoblementeEnlazada<Position<E>>();
            for(Position<E> elem :l1.positions())
                for(Position<E> borrar : l2.positions())
                    if(borrar.element().equals(elem.element()))
                        listaAux.addLast(elem);

            for(Position<E> borrarL1 : listaAux){
                l1.remove(borrarL1);
            }

            Position<E> aux = l2.last();
            while(aux!=null){
                l1.addLast(aux.element());
                aux = (aux== l2.first()) ? null : l2.prev(aux);
            }
        }
        
    }
    public void addFirst(E element){
        DNodo<E> nuevo = new DNodo<E>(element);
        header.getSiguiente().setAnterior(nuevo);
        nuevo.setSiguiente(header.getSiguiente());
        nuevo.setAnterior(header);
        header.setSiguiente(nuevo);
        tamanio++;
    }
    
}




