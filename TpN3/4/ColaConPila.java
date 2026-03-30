import java.util.Stack;
public class ColaConPila<E> implements Queue<E>{
    private Stack<E> pila;
    
    public ColaConPila (){
        pila = new Stack<E>();
    }
    public int size(){
        return pila.size();
    }
    public boolean isEmpty(){
        return pila.size()==0;
    }
    public E front(){
        if(isEmpty())
            throw new EmptyQueueException("Error, cola vacia.");
        else{
            Stack<E> pilaAux= new Stack<E>();
            while(!pila.empty())
                pilaAux.push(pila.pop());
            E element =pilaAux.peek();
            while(!pilaAux.empty())
                pila.push(pilaAux.pop());
            return element;
        } 
    }
    public void enqueue (E element){
        pila.push(element);
    }
    public E dequeue(){
        if(pila.empty())
            throw new EmptyQueueException("Error, cola vacia.");
        else{ 
         E element=null;
            Stack<E> pilaAux= new Stack<E>();
            while (!pila.empty()){
                pilaAux.push(pila.pop());
            }
            element= pilaAux.pop();
            while(!pilaAux.empty())
                pila.push(pilaAux.pop());
            return element;
        }
    }
}