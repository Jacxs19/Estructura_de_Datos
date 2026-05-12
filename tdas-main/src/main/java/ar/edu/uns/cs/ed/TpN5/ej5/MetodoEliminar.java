package ar.edu.uns.cs.ed.TpN5.ej5;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TpN5.ej3.DiccionarioConHashAbierto;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.Position;

public class MetodoEliminar<K,V> extends DiccionarioConHashAbierto<K,V>{


    Iterable<Entry<K,V>> eliminarTodas(K c,V v) throws InvalidKeyException{
        if(c==null)
            throw new InvalidKeyException("Llave invalida");
        PositionList<Entry<K,V>> aDevolver = new ListaDoblementeEnlazada<Entry<K,V>>();
        PositionList<Position<Entry<K,V>>> aBorrar = new ListaDoblementeEnlazada<Position<Entry<K,V>>>();
        PositionList<Entry<K,V>> aux = h(c);
        for(Position<Entry<K,V>> bucket : aux.positions()){
            if(bucket.element().getValue().equals(v) && bucket.element().getKey().equals(c)){
                aBorrar.addLast(bucket);
                aDevolver.addLast(bucket.element());
            }
        }
        for(Position<Entry<K,V>> eliminar : aBorrar){
            aux.remove(eliminar);
        }
        return aDevolver;
    }
}

    

