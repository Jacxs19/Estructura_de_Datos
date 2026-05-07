package ar.edu.uns.cs.ed.TpN5.ej2;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.*;
import ar.edu.uns.cs.ed.tdas.Entry;

import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;

public class MapeoConHashAbierto<K,V>implements Map<K,V>{
    private  PositionList<Entry<K,V>>[] A;
    int n;                              //Cantidad de entradas en el mapeo
    int N=17;               
    
    //Constructor
    public MapeoConHashAbierto(){
        A= new ListaDoblementeEnlazada[N];
        for(int i=0; i<N-1; i++)
            A[i]= new ListaDoblementeEnlazada<Entry<K,V>>();
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n==0;
    }
    public V get (K key){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");

        PositionList<Entry<K,V>> bucket = A[h(key)];
        
        for(Entry<K,V> entrada : bucket){
            if(entrada.getKey().equals(key)){
                return entrada.getValue();
            }
        }

        return null;
    }
    public V put (K key, V value){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        PositionList<Entry<K,V>> bucket= A[h(key)];
        for(Entry<K,V> aux : bucket){
            if(aux.getKey().equals(key)){
                V valorViejo = aux.getValue();
                aux.setValue(value);
                return valorViejo;
            }
        }
        Entry<K,V> entrada = new Entrada<K,V>(key, value);
        bucket.addLast(entrada);
        n++;

    }

        

    
}
    
    





    private int h(K key){
        return key.hashCode() % N;
    }
}
