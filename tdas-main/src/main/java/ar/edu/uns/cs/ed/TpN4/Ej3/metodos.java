package ar.edu.uns.cs.ed.TpN4.Ej3;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import java.util.Iterator;

public class  metodos{
    //Preguntar <E> en cada ej0
    public <E> boolean seEncuentra (PositionList<E> l, E e1){
        if(l==null || e1==null)
            throw new NullPointerException("La lista o el elemento es nulo.");
        if(l.isEmpty())
            return false;
        boolean esta =false;
        Iterator<E> it = l.iterator();
        E elemento=null;
        while (it.hasNext()&&!esta){
            elemento=it.next();
            if(e1.equals(elemento))
                esta=true;
        }
        return esta;
    }
    public <E> int aparicionesDeElemento(PositionList<E> l, E e1){
        if(l==null || e1==null)
            throw new NullPointerException("La lista o el elemento es nulo.");
        if(l.isEmpty())
            return 0;
        int cant=0;
        E elemento=null;
        Iterator<E> it = l.iterator();
        while(it.hasNext()){
            elemento=it.next();
            if(elemento.equals(e1)) cant++;
            }
        return cant;
    }
    public <E> boolean alMenosNVeces(PositionList<E> l, E x, int n){
        if(l==null || x==null)
            throw new NullPointerException("Lista o elemento nulo.");
        if(l.isEmpty())                 
            return false;           //Retorno antes por eficacia.
        Iterator<E> it = l.iterator();
        E elemento=null;
        int contador=0;
        while(it.hasNext() && contador <n){
            elemento=it.next();
            if(elemento== x)
                contador++;
        }
        return contador==n;
    }





}
