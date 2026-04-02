package TpN2.Ej1;
import java.lang.NullPointerException;
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
        catch(NullPointerException e){
            System.out.println("La referencia al arreglo es nula. "+e.getMessage());
        }
    
}
}