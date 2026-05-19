package ar.edu.uns.cs.ed.TpN4.Ej4;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;


public class MetodoDuplicar<E> {

    public PositionList<E> nuevaListaDuplicada (PositionList<E> l){
        PositionList<E> listaNueva = new ListaDoblementeEnlazada<E>(); 
        for(E element : l){
            listaNueva.addLast(element);
            listaNueva.addLast(element);
        }
        return listaNueva;
    }
    
}
