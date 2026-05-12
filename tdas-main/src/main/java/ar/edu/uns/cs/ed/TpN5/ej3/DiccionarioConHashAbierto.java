package ar.edu.uns.cs.ed.TpN5.ej3;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Dictionary;
import ar.edu.uns.cs.ed.TpN5.ej2.Entrada;


import java.util.Iterator;

public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V>{
    private PositionList<Entrada<K,V>>[] A;
    private int N=17;
    private int n;
    private static final double factorDeCarga = 0.9;

    public DiccionarioConHashAbierto(){
        A= new ListaDoblementeEnlazada[N];
        for(int i=0; i<N; i++)
            A[i]= new ListaDoblementeEnlazada<Entrada<K,V>>();
        n=0;
    }
    
    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n==0;
    }
    
    public Entry<K,V> find (K key){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        PositionList<Entrada<K,V>> bucket = A[h(key)];
        for(Entrada<K,V> llaves : bucket){
            if(llaves.getKey().equals(key))
                return llaves;
        }
        return null;
    }
    
    public Iterable<Entry<K,V>> findAll(K key){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        PositionList<Entrada<K,V>> bucket = A [h(key)];
        PositionList<Entry<K,V>> aDevolver = new ListaDoblementeEnlazada<Entry<K,V>>();
        for(Entrada<K,V> llaves : bucket){
            if(llaves.getKey().equals(key))
                aDevolver.addLast(llaves);
        }
        return aDevolver;
    }

    public Entry<K,V> insert (K key, V value){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        Entrada<K,V> insertar = new Entrada<K,V>(key,value);
        A[h(key)].addLast(insertar);
        n++;
        double factorCargaNuevo = (double) n/N;
        if(factorCargaNuevo > factorDeCarga){
            reHash();
        }
        return insertar;
    }





    public Entry<K,V> remove(Entry<K,V> e){
        if(e==null)
            throw new InvalidEntryException("Entrada invalida");
            for(Position<Entrada<K,V>> bucket : A[h(e.getKey())].positions()){
                if(bucket.element().getKey().equals(e.getKey()) && bucket.element().getValue().equals(e.getValue())){
                    Entry<K,V> aux = bucket.element();
                    A[h(e.getKey())].remove(bucket);
                    n--;
                    return aux;  
                }      
            }
        throw new InvalidEntryException("Entrada inexistente");
    }

    public Iterable<Entry<K,V>> entries(){
        PositionList<Entry<K,V>> iterador = new ListaDoblementeEnlazada<Entry<K,V>>();
        for(int i=0; i<N; i++)
            for(Entrada<K,V> entrada : A[i])
                iterador.addLast(entrada);
        return iterador;

    }



    private void reHash(){
        int tamanio = N+1;
        boolean esPrimo=false;
        while(!esPrimo){
            if(!siguientePrimo(tamanio))
                tamanio++;
            else
                esPrimo=true;
        }
        PositionList<Entrada<K,V>> [] aDevolver = new ListaDoblementeEnlazada[tamanio];
        for(int i=0; i<tamanio; i++)
            aDevolver[i]= new ListaDoblementeEnlazada<Entrada<K,V>>();
        PositionList<Entrada<K,V>> [] vieja = A;
        N=tamanio;
        for(int i=0; i<vieja.length; i++)
            for(Entrada<K,V> entradas : vieja[i]){
                aDevolver[h(entradas.getKey())].addLast(entradas);
            }
        A=aDevolver;
            
    }

    private boolean siguientePrimo(int tamanio){
        for(int i=2; i<tamanio; i++)
            if(tamanio % i ==0)
                return false;
        return true;
    }

    private int h(K key){
        return Math.abs(key.hashCode()) % N;
    }

    
}