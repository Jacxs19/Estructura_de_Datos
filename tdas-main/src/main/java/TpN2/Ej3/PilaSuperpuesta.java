package TpN2.Ej3;
import java.lang.NullPointerException;
import java.util.Stack;

public class PilaSuperpuesta<E>{


    public  Stack<E> pilasSuperpuestas (Stack<E> p1 , Stack<E> p2){
        Stack<E> superPuesto = new Stack<E> ();
        try{
            while(!p1.empty() || !p2.empty()){
                if(!p1.empty())
                    superPuesto.push(p1.pop());
                if(!p2.empty())
                    superPuesto.push(p2.pop());
            }
        }
        catch (NullPointerException e){
            System.out.println("Se trato de acceder a un nulo: "+e.getMessage());
        }
        return superPuesto;
    }
}