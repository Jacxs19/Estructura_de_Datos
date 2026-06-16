package ar.edu.uns.cs.ed.TpN9.ej2;

import java.util.Comparator;

import ar.edu.uns.cs.ed.tdas.tdacolaconprioridad.Comparador;

import ar.edu.uns.cs.ed.TpN9.ej1.ColaConPrioridad;

public class algoritmo<K,V,E> {

    //Algoritmo de ordenamiento:
    //por cada elemento del arreglo A, éste es insertado en una cola con prioridades C. 
    //cuando todos los elementos del arreglo A han sido insertados en la cola conprioridades C, 
    //el arreglo A se considera vacío y luego se elimina el elemento mínimo M de C repetidamente y
    //se inserta a M en el arreglo A en orden FIFO.

    public void ordenamientoFIFO( Integer[] A, int n){                               //n es tamanio del arreglo
        Comparator<Integer> comp = new Comparador<Integer>();
        ColaConPrioridad<Integer,Integer> cola = new ColaConPrioridad<Integer,Integer>(comp);
        for(int i=0; i<A.length-1; i++){
            cola.insert(A[i], A[i]);
        }
        for(int i=0; i<A.length-1; i++){
            A[i]=cola.removeMin().getValue();
        }
    }
    
}
