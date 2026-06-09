package ar.edu.uns.cs.ed.TpN9.ej3;

public class DefaultComparator<E> implements java.util.Comparator<E>{
    public int compare(E a, E b) throws ClassCastException{
        return ((Comparable<E>)a).compareTo(b);
    }
    
}
