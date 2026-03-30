import java.util.EmptyStackException;

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
