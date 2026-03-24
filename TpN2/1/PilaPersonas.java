import java.util.EmptyStackException;
import java.util.Stack;

public class PilaPersonas{

    public void invertir (Persona[] A){
        try{
        Stack <Persona> pila = new Stack <Persona>();
        for(int i=0; i<A.length; i++)
            pila.push(A[i]);
        for(int i=0; i<A.length; i++){
            A[i]=pila.pop();
        }
        }
        catch(EmptyStackException e){
            System.out.println("Hubo un intento de desapilar una pila vacia.");
        }
    
}
}