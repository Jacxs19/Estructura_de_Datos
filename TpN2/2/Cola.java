import java.util.Queue;
import java.util.LinkedList;
public class Cola {
    
    
    public Queue<Integer> nuevaCola (Queue<Integer> c){
        Integer elemento=0;
        
        Queue <Integer> nueva = new LinkedList<Integer>();
        try{ 
        for(int i=0; i< c.size();i++)
            if((elemento=c.poll())/2 !=0)
                nueva.add(elemento);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Se intento superar el limite de la cola"); 
        }
        return nueva;
    }

    
}
