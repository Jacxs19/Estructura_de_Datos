package ar.edu.uns.cs.ed.TpN5.ej2;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;

public class MapeoConHashAbierto<K,V>implements Map<K,V>{
    private  Map<K,V>[] A;
    int n;                              //Cantidad de entradas en el mapeo
    int N=17;               
    
    //Constructor
    public MapeoConHashAbierto(){
        A= (Map<K,V>[]) new MapeoConLista[N];
        for(int i=0; i<N-1; i++)
            A[i]= new MapeoConLista<K,V>();
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n==0;
    }
    
}
