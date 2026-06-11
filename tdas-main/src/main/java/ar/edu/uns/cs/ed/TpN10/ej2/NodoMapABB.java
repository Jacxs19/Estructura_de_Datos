package ar.edu.uns.cs.ed.TpN10.ej2;

import ar.edu.uns.cs.ed.tdas.Entry;


public class NodoMapABB<K,V> implements Entry<K,V> {
    private K clave;
    private V valor;
    private NodoMapABB<K,V> padre;
    private NodoMapABB<K,V> hijoiz, hijoder;

    public NodoMapABB(K key, V value, NodoMapABB<K,V> p){
        clave=key;
        valor=value;
        padre=p;
        hijoiz=null;
        hijoder=null;
    }

    //Getters
    public K getKey(){
        return clave;
    }
    public V getValue(){
        return valor;
    }
    public NodoMapABB<K,V> getPadre(){
        return padre;
    }
    public NodoMapABB<K,V> getHijoIzq(){
        return hijoiz;
    }
    public NodoMapABB<K,V> getHijoDer(){
        return hijoder;
    }
    //Setters
    public void setKey(K key){
        clave=key;
    }
    public void setValue(V value){
        valor=value;
    }
    public void setPadre(NodoMapABB<K,V> p){
        padre=p;
    }
    public void setHijoIzq(NodoMapABB<K,V> i){
        hijoiz=i;
    }
    public void setHijoDer(NodoMapABB<K,V> d){
        hijoder=d;
    }
}
