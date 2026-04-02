package TpN2.Ej4;
import java.util.Queue;
import java.util.LinkedList;
class ColaEnteros{
    

    public int mayorValor(Queue <Integer> q){
        
        int max=Integer.MIN_VALUE;                                                  //o usar .peek(), pero antes como controlo q!=null?
        Queue <Integer> aux = new LinkedList<Integer>();
        while (!q.isEmpty()){
            if(max<q.peek())
                max=q.peek();
            aux.offer(q.poll());
        }
        while (!aux.isEmpty())
            q.offer(aux.poll());

        
        return max;
        
    }
}