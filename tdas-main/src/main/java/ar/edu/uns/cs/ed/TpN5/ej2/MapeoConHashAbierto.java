package ar.edu.uns.cs.ed.TpN5.ej2;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import java.util.Iterator;



public class MapeoConHashAbierto<K,V>implements Map<K,V>{
    private  PositionList<Entrada<K,V>>[] A;                
    private  static final double factorDeCarga= 0.9f;
    private int n;                                                      //Cantidad de entradas en el mapeo
    private int N=17;            
    
    //Constructor
    public MapeoConHashAbierto(){
        A= new ListaDoblementeEnlazada[N];                          
        for(int i=0; i<N; i++)                                      
            A[i]= new ListaDoblementeEnlazada<Entrada<K,V>>();      
        n=0;                                                        
    }
    public int size(){
        return n;               //C1
    }
    //T2(N)= C1 = O(1)
    public boolean isEmpty(){
        return n==0;            //C1
    }
    //T3(N)= C1 = O(1)
    public V get (K key){
        if(key==null)                                                   //C1                   
            throw new InvalidKeyException("Llave invalida");         //C2

        PositionList<Entrada<K,V>> bucket = A[h(key)];                  //C3
        
        for(Entrada<K,V> entrada : bucket){                             //n (en el peor de los casos todas las entradas caen en un bucket)
            if(entrada.getKey().equals(key)){                           //C4
                return entrada.getValue();                              //C5
            }
        }
        return null;                                                    //C6
    }
    //T4(N)= C1+ max(C2,  C3+n(C4+max(C5,null)) +c6) = C1 + C3+n(C4+C5+C6)= C1 + C3 + nC4 + nC5 + nC6 = O(n)
    public V put (K key, V value){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        PositionList<Entrada<K,V>> bucket= A[h(key)];
        for(Entrada<K,V> aux : bucket){
            if(aux.getKey().equals(key)){
                V valorViejo = aux.getValue();
                aux.setValue(value);
                return valorViejo;
            }
        }
        Entrada<K,V> entrada = new Entrada<K,V>(key, value);
        bucket.addLast(entrada);
        n++;
        double factorActual = (double) n/N;
        if( factorActual > factorDeCarga){                                //Si el factor de cargo es menor a n/N hago REHASH
            reHash();
        }
        return null;
    }

    public V remove (K key){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        PositionList<Entrada<K,V>> bucket = A[h(key)];
        Iterator<Position<Entrada<K,V>>> it = bucket.positions().iterator();
        while(it.hasNext()){
            Position<Entrada<K,V>> p = it.next();
            if(p.element().getKey().equals(key)){
                V valor = p.element().getValue();
                bucket.remove(p);
                n--;
                return valor;
            }
        }
        return null;       
    }

    public Iterable<K> keys(){
        PositionList<K> llaves = new ListaDoblementeEnlazada<K>();
        for(int i=0; i<N; i++){                             //Cada bucket del arreglo
            for(Entrada<K,V> buckets : A[i]){               //Consigo las entradas
                llaves.addLast(buckets.getKey());           //Gurado las llaves
            }
        }
        return llaves;
    }
    public Iterable<V> values(){
        PositionList<V> valores = new ListaDoblementeEnlazada<V>();
        for(int i=0;i<N;i++){                               //Cada bucket del arreglo
            for(Entrada<K,V> buckets : A[i]){               //Consigo las entradas
                valores.addLast(buckets.getValue());        //Guardo los valores
            }
        }
        return valores;
    }
    
    public Iterable<Entry<K,V>> entries(){
        PositionList<Entry<K,V>> listaEntradas = new ListaDoblementeEnlazada<Entry<K,V>>();
        for(int i=0; i<N; i++){                             //Cada bucket del arreglo
            for(Entrada<K,V> buckets : A[i])                //Consigo las entradas
                listaEntradas.addLast(buckets);             //Guardo las entradas
            }
        return listaEntradas;
    }

    private void reHash(){
        int tamanio = N+1;
        boolean esPrimo =false;
        while(!esPrimo){
            if(!SiguientePrimo(tamanio))
                tamanio++;
            else
                esPrimo=true;
        }
        PositionList<Entrada<K,V>>[] aux = new ListaDoblementeEnlazada[tamanio];
        for(int i=0; i<tamanio;i++){
            aux[i]= new ListaDoblementeEnlazada<Entrada<K,V>>();
        }
        PositionList<Entrada<K,V>>[] viejo = A;                                                     //Guardo el arreglo viejo para actualizar el N
        N=tamanio;
        for(int i=0; i<viejo.length; i++)                                                           //Miro todos los buckets del arreglo viejo
            for(Entrada<K,V> entradas : viejo[i]){                                                  //En cada bucket ciclo en todas las entradas
                aux[h(entradas.getKey())].addLast(entradas);                                         //Guardo la entrada rescatada en el nuevo arreglo
            }
        A=aux;
        }
    
    private boolean SiguientePrimo(int tamanio){
        for(int i=2; i<tamanio; i++){
            if(tamanio % i ==0)
                return false;
        }
        return true;
    }
    
    private int h(K key){
        return Math.abs(key.hashCode()) % N;
    } 
        
}
    
















    















 
 
    





    

