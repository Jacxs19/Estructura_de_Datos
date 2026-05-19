package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelsegundoparcial.Parcial3;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TDAS_Implementados.TNodo;
import ar.edu.uns.cs.ed.TDAS_Implementados.Entrada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;

public class Meotodos {

    public boolean alMenoseEntradas(K key, int e) throws InvalidKeyException{
        if(key==null)                                                           
            throw new InvalidKeyException("Llave invalida");
        int cant=0;
        PositionList<Entrada<K,V>> bucket = buckets[h(key)];
        for(Entrada<K,V> entradas : bucket){
            if(etradas.getKey().equals(key))
                cant++;
            if(cant==e)
                return true;
        }
        return false;
    }
    //T_alMenoseEntradas(n)= O(1), ya que asumimos que hay una buena implementacion del hash que garantiza buena distribucion de claves, por lo que no todas las entradas caen en un bucket, 
    //entonces la cantidad de entradas en un bucket es constante.
    private int h(K key){
        return Math.abs(key.hashCode())%N;
    }

    //Ej2

    public Iterable<E> convertirAhoja(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        PositionList<E> listaADevolver = new ListaDoblementeEnlazada<E>();
        PositionList<Position<TNodo<E>>> eliminar = new ListaDoblementeEnlazada<Position<TNodo<E>>>();
        for(Position<TNodo<E>> hijos : nodo.getHijos().positions()){
            eliminarSubArbol(hijos.element(),listaADevolver);
            eliminar.AddLast(hijos);
        }
        for(Position<TNodo<E>> pos : eliminar)
            nodo.getHijos().remove(pos);
        return listaADevolver;
    }

    private void eliminarSubArbol(TNodo<E> nodo, PositionList<E> l){
        for(TNodo<E> hijos : nodo.getHijos())
            eliminarSubArbol(hijos, l);
        l.addLast(nodo.element());
        nodo.setPadre(null);
        nodo.setElement(null);
        size--;
    }
    
    private TNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new InvalidPositionException("p invalida");
            if(p.element()==null)
                throw new InvalidPositionException("p eliminada previamente");
            return (TNodo<E>) p;
        }catch (ClassCastException e){
            throw new InvalidPositionException("p no es un nodo de arbol")
        }
    }



    
}
