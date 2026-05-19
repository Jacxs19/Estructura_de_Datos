package ar.edu.uns.cs.ed.TpN4.Ej6;

import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;



import java.util.Iterator;

public class Metodos<E> {
    public PositionList<E> listasIntercaladas(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> intercalada = new ListaDoblementeEnlazada<E>();
        Iterator<E> it1 = l1.iterator();
        Iterator<E> it2= l2.iterator();
        while(it1.hasNext() || it2.hasNext()){
            if(it1.hasNext())
                intercalada.addLast(it1.next());
            if(it2.hasNext())
                intercalada.addLast(it2.next());
            } 
        
        return intercalada; 
    }
   /*  
   Si me piden usar solo Positions
    Position<E> p1 = L1.isEmpty() ? null : L1.first();
    Position<E> p2 = L2.isEmpty() ? null : L2.first();
    while (p1 != null || p2 != null) {
        if (p1 != null) {
            intercalada.addLast(p1.element());
            p1 = (p1 == L1.last()) ? null : L1.next(p1);
        }
        if (p2 != null) {
            intercalada.addLast(p2.element());
            p2 = (p2 == L2.last()) ? null : L2.next(p2);
        }
    }
} 
*/
    public PositionList<Integer> listaIntercaladaEnteros(PositionList<Integer> l1, PositionList<Integer> l2){       //Ya estan ordenadas.
        PositionList<Integer> lista = new ListaDoblementeEnlazada<Integer>();
        if (l1 ==null || l2==null)
            return lista;
            Iterator<Integer> it1 = l1.iterator();
            Iterator<Integer> it2 = l2.iterator();
            Integer n1 = (it1.hasNext()) ? it1.next() : null;
            Integer n2 = (it2.hasNext()) ? it2.next() : null;

            while(n1 !=null && n2 !=null){
                if(n1<n2){
                    if(!pertenece(n1,lista))           
                        lista.addLast(n1);
                    n1= it1.hasNext() ? it1.next() : null;
                }
                else if(n1>n2){
                    if(!pertenece(n2,lista))
                        lista.addLast(n2);
                    n2= it2.hasNext() ? it2.next() : null;
                }
                else{                                                                   //Son iguales
                    if(!pertenece(n2,lista))
                        lista.addLast(n2);
                    n1= it1.hasNext() ? it1.next() : null;
                    n2= it2.hasNext() ? it2.next() : null;
                }
            }
            while (n1!=null){
                if(!pertenece(n1,lista))
                    lista.addLast(n1);
                n1= it1.hasNext() ? it1.next() : null;
            }
            while(n2!=null){
                if(!pertenece(n2,lista))
                    lista.addLast(n2);
                n2= it2.hasNext() ? it2.next() : null;
            }
            return lista;
    }
    private boolean pertenece(Integer num, PositionList<Integer> L){
        for(Integer e : L)
            if(e.equals(num))
                return true;
        return false;
    }
}