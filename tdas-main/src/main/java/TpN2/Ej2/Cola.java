package TpN2.Ej2;
import java.util.Queue;
import java.util.LinkedList;
public class Cola {
    
    
    public Queue<Integer> soloImpares (Queue<Integer> c){
        int elemento=0;
        int tamanio =c.size();
        
        Queue <Integer> nueva = new LinkedList<Integer>();
        
        for(int i=0; i< tamanio;i++)
            if((elemento=c.poll())/2 !=0)
                nueva.offer(elemento);
        
        
        return nueva;
    }

    
}
