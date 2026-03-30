import java.lang.*;
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
        return ((Arreglo.length-eliminar+cant) % Arreglo.length);
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
        Arreglo[cant]=element;
        cant= (cant+1) % Arreglo.length;
        
    }
    public E dequeue() throws EmptyQueueException{
        if(isEmpty())
            throw new EmptyQueueException("Error, cola vacia.");
        else{
            E elemento = Arreglo[eliminar];
            Arreglo[eliminar]= null;
            eliminar= (eliminar+1) % Arreglo.length;
            return elemento;
        }

    }

}