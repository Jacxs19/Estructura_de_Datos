package ar.edu.uns.cs.ed.TDAS_Implementados;
import ar.edu.uns.cs.ed.tdas.tdapila.Stack;
import java.util.EmptyStackException;

@SuppressWarnings("unchecked")

public class ArregloPila<E> implements Stack<E> {
    private E[] arreglo;
    private int cant;
    
    public ArregloPila() {
        arreglo= (E[])new Object[1000];
        cant=0;    
    }

    public int size(){
        return cant;
    }
    public boolean isEmpty(){
        return cant==0;
    }
    public E top(){
        if(isEmpty())
            throw new EmptyStackException();
        else
            return arreglo[cant-1];
    }
    public void push(E element){
        if(cant==arreglo.length){
            E[] aux = (E[]) new Object[arreglo.length *2];
            for(int i=0; i<cant; i++){
                aux[i]=arreglo[i];
            }
            arreglo=aux;
        }
        arreglo[cant]=element;
        cant++;
    }
    public E pop(){
        if(isEmpty())
            throw new EmptyStackException();
        else{ 
            E elemento= arreglo[cant-1];
            arreglo[cant-1]=null;
            cant--;
            return elemento;
        }
    }
}
