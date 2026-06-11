package ar.edu.uns.cs.ed.TpN10.ej2;


import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Entry;

import java.util.Comparator;

public class MapeoConABB<K,V> implements Map<K,V> {
    private NodoMapABB<K,V> raiz;
    private int size;
    private Comparator<K> comp;

    public MapeoConABB(Comparator<K> c){
        raiz=null;
        size=0;
        comp=c;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public V get(K key){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        NodoMapABB<K,V> nodo = busqueda(key, raiz);
        if(nodo==null)
            return null;
        return nodo.getValue();
    }

    public V put (K key, V value){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        return insertar(key, value, raiz);
    }

    public V remove(K key){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        NodoMapABB<K,V> nodo = busqueda(key, raiz);
        if(nodo==null)                                          //Caso que no lo encuentre
            return null;
        NodoMapABB<K,V> iz = nodo.getHijoIzq();
        NodoMapABB<K,V> der = nodo.getHijoDer();
        V valor = nodo.getValue();
        if(iz==null && der==null){
            casoSinHijos(nodo);
        }
        else if(iz != null && der==null || iz==null && der!=null){
            byPass(nodo,iz,der);
        }
        else{
            eliminarConDosHijos(nodo);
        }
        return valor;
    }
    //T_elminar(h) = O(h), donde h es la altura del ABB



    public Iterable<K> keys(){
        PositionList<K> lista = new ListaDoblementeEnlazada<K>();
        for(int i=0; i<size ; i++){
            inOrdenClaves(raiz,lista);
        }
        return lista;
    }
    
    public Iterable<V> values(){
        PositionList<V> lista = new ListaDoblementeEnlazada<V>();
        if(raiz!=null){
            inOrdenValores(raiz,lista);
        }
        return lista;
    }

    public Iterable<Entry<K,V>> entries(){
        PositionList<Entry<K,V>> lista = new ListaDoblementeEnlazada<Entry<K,V>>();
        if(raiz!=null)
            inOrdenNodos(raiz,lista);
        return lista;
    }

    //Metodos auxiliares
    
    private void casoSinHijos(NodoMapABB<K,V> nodo){
        if(nodo==raiz){
            raiz=null;
            size=0;
        }
        else if(nodo.getPadre().getHijoDer()==nodo){
            nodo.getPadre().setHijoDer(null);
            size--; 
        }
        else{
            nodo.getPadre().setHijoIzq(null);
            size--;    
        }
        nodo.setValue(null);
        nodo.setKey(null);
    }
    private void eliminarConDosHijos(NodoMapABB<K,V> nodo){
        NodoMapABB<K,V> minimo = buscarMinimo(nodo.getHijoDer());
        nodo.setValue(minimo.getValue());
        nodo.setKey(minimo.getKey());                               //Preguntar
        if(minimo.getHijoDer()==null)
            casoSinHijos(minimo);
        else
            byPass(minimo, minimo.getHijoIzq(), minimo.getHijoDer());
    }

    private NodoMapABB<K,V> buscarMinimo(NodoMapABB<K,V> nodo){
        if(nodo.getHijoIzq()!=null)
            return buscarMinimo(nodo.getHijoIzq());
        return nodo;
    }

    private void byPass(NodoMapABB<K,V> n, NodoMapABB<K,V> iz, NodoMapABB<K,V> der){
        if(n==raiz){                                            
            NodoMapABB<K,V> hijo = (iz != null) ? iz : der;
            raiz = hijo;
            hijo.setPadre(null);
            n.setPadre(null);
            n.setHijoIzq(null);
            n.setHijoDer(null);
            size--;
            return;
        }
        NodoMapABB<K,V> padre = n.getPadre();
        if(padre.getHijoDer()==n){
            if(iz!=null){
                padre.setHijoDer(iz);
                iz.setPadre(padre);
            }
            else{
                padre.setHijoDer(der);
                der.setPadre(padre);
            }
        }
        else{
            if(iz!=null){
                padre.setHijoIzq(iz);
                iz.setPadre(padre);
            }
            else{
                padre.setHijoIzq(der);
                der.setPadre(padre);
            }
        }
        n.setPadre(null);
        n.setHijoIzq(null);
        n.setHijoDer(null);
        n.setKey(null);
        n.setValue(null);
        size--;
    }

    private void inOrdenNodos(NodoMapABB<K,V> n, PositionList<Entry<K,V>> l){
        if(n.getHijoIzq()!=null)
            inOrdenNodos(n.getHijoIzq(), l);
        l.addLast(n);
        if(n.getHijoDer()!=null)
            inOrdenNodos(n.getHijoDer(),l);
    }

    private void inOrdenValores(NodoMapABB<K,V> n, PositionList<V> l){
        if(n.getHijoIzq()!=null)
            inOrdenValores(n.getHijoIzq(), l);
        l.addLast(n.getValue());
        if(n.getHijoDer()!=null)
            inOrdenValores(n.getHijoDer(),l);
    }


    private void inOrdenClaves(NodoMapABB<K,V> n , PositionList<K> l){          //Lo utilizo para que las claves quede en orden ascendente
        if(n.getHijoIzq()!=null)
            inOrdenClaves(n.getHijoIzq(),l);
        l.addLast(n.getKey());
        if(n.getHijoDer()!=null)
            inOrdenClaves(n.getHijoDer(),l);
    }

    private V insertar(K key, V value, NodoMapABB<K,V> n){
        if(size==0){
            raiz = new NodoMapABB<K,V> (key,value,null);
            size=1;
            return null;
        }
        int i = comp.compare(key,n.getKey());
        if(i==0){                                           //Caso claves iguales
            V aDevolver = n.getValue();
            n.setValue(value);
            return aDevolver;
        }
        else if(i<0){                                       //Caso key < n.getKey()
            if(n.getHijoIzq()==null){
                NodoMapABB<K,V> nodo = new NodoMapABB<K,V>(key, value, n);
                n.setHijoIzq(nodo);
                size++;
                return null;
            }
            return insertar(key,value,n.getHijoIzq());
        }
        if(n.getHijoDer()==null){                          //Caso key > n.getKey()
            NodoMapABB<K,V> nodo = new NodoMapABB<K,V>(key, value, n);
            n.setHijoDer(nodo);
            size++;
            return null;
        }
        return insertar(key,value,n.getHijoDer());
    }

    private NodoMapABB<K,V> busqueda(K clave , NodoMapABB<K,V> n){
        if(n==null)
            return null;
        int i = comp.compare(clave,n.getKey());
        if(i==0)
            return n;
        else if(i<0)
            return busqueda(clave, n.getHijoIzq());
        else
            return busqueda(clave,n.getHijoDer());
    }
}
