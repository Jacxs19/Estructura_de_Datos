package ar.edu.uns.cs.ed.TpN5.ej2;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;

public class MapeoConLista<K,V>implements Map<K,V>{
    protected ListaDoblementeEnlazada<Entrada<K,V>> s;                  //El mapeo utiliza una lista doblemente enlazada.

    //Constructor
    public MapeoConLista(){
        s=new ListaDoblementeEnlazada<Entrada<K,V>>();
    }
    public int size(){
        return s.size();
    }
    public boolean isEmpty(){
        return s.isEmpty();
    }
    
}
