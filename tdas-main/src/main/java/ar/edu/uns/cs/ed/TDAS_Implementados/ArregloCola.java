package ar.edu.uns.cs.ed.TDAS_Implementados;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;

@SuppressWarnings("unchecked")

public class ArregloCola<E> implements Queue<E>{
    private E [] Arreglo;
    private int cant;               //r es la posición en la cual se va a insertar el siguiente elemento con un enqueue.
    private int eliminar;           // f es la posición en q del próximo elemento a eliminar en un dequeue
    
    
    public ArregloCola (){
        Arreglo = (E[]) new Object[100000];
        cant=0;
        eliminar=0;
        
    }
    public int size(){
        return cant-eliminar;
    }
    public boolean isEmpty(){
        return cant==eliminar;
    }
    public E front() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("Error, cola vacia.");
        else{
            return Arreglo[eliminar];
        }
    }
    public void enqueue(E element){
        if(cant==Arreglo.length){
            E[] arregloAux= (E[]) new Object[Arreglo.length*2];
            int j=0;
            for(int i=eliminar; i<cant; i++){
                arregloAux[j]=Arreglo[i];
                j++;
            }
            cant=this.size();
            eliminar=0;
            Arreglo=arregloAux;
            }
        Arreglo[cant]=element;
        cant= (cant+1) % Arreglo.length;
        }
        
        
    
    public E dequeue() throws EmptyQueueException{
        if(isEmpty())
            throw new EmptyQueueException("Error, cola vacia.");
        else{
            E elemento = Arreglo[eliminar];
            Arreglo[eliminar]= null;
            eliminar+=1;
            return elemento;
        }

    }

}