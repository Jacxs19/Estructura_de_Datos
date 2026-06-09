package ar.edu.uns.cs.ed.TpN9.ej1;

import ar.edu.uns.cs.ed.tdas.tdacolaconprioridad.PriorityQueue;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyPriorityQueueException;
import ar.edu.uns.cs.ed.TDAS_Implementados.Entrada;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

import java.util.Comparator;

@SuppressWarnings("unchecked")

public class ColaConPrioridad<K,V> implements PriorityQueue<K,V>{
    protected Entrada<K,V> [] elems;
    protected Comparator<K> comp;
    protected int size;

    public ColaConPrioridad(Comparator<K> comp){
        elems= (Entrada<K,V> []) new Entrada[100];  //No se utiliza la pos 0 (elems[0]=null)
        this.comp=comp;                             //Comparador del cliente
        size=0;
    }
                                                    //Hijo izquierdo = 2i
                                                    //Hijo derecho = 2i+1
                                                    //Padre = i/2
    public int size(){
        return size;
    }
    //T_size(n)=O(1)
    
    public boolean isEmpty(){
        return size==0;
    }
    //T_isEmpty(n)=O(1)

    public Entry<K,V> min(){
        if(size==0)
            throw new EmptyPriorityQueueException("Cola vacia");
        return elems[1];
    }
    //T_min(n)=O(1)

    public Entry<K,V> insert(K key , V value){
        if(key==null)
            throw new InvalidKeyException("Llave invalida");
        Entrada<K,V> entrada = new Entrada<K,V>(key,value);
        if(size==elems.length-1)
            aumentar();
        elems[++size]=entrada;
        //Metodo burbuja (para arriba)
        int i=size;                 // seteo indice i de la posicion corriente en arreglo que es la última
        boolean seguir=true;        // Bandera para saber cuándo encontré la ubicación de entrada
        while(i>1 && seguir){
            Entrada<K,V> elemActual= elems[i];
            Entrada<K,V> elemPadre= elems[i/2];
            if(comp.compare(elemActual.getKey(),elemPadre.getKey()) <0 ){
                Entrada<K,V> aux = elems[i];
                elems[i]= elems[i/2];
                elems[i/2] = aux;
                i/=2;               // Reinicializo i con el índice de su padre
            }
            else
                seguir=false;
        }
        return entrada;
    }
    //T_insert(n) = O(h) = O(log2(n)) si n es la cantidad de nodos del heap this y h su altura.

    public Entry<K,V> removeMin(){
        if(size==0)
            throw new EmptyPriorityQueueException("Cola vacia");
        Entry<K,V> minimo = min();
        if(size==1){
            elems[1]=null;
            size=0;
            return minimo;
        }else{
            //Paso la última entrada a la raíz y la borro del final del arreglo y decremento size:
            elems[1]=elems[size];
            elems[size]=null;
            size--;
            //Metodo burbuja hacia abajo
            int i=1;
            boolean seguir=true;
            while(seguir){
                int hi=i*2;         //Hijo izquierdo
                int hd=(i*2)+1;        //Hijo derecho
                boolean tieneHijoIzquierdo = hi<=size();        //Chequeo que exista
                boolean tieneHijoDerecho = hd<=size();          //Chequeo que exista
                if(!tieneHijoIzquierdo)
                    seguir =false;
                else{
                    int m=size;          //En m voy a computar la posición del mínimo de los hijos de i
                    if(tieneHijoDerecho){
                        //Calculo cuál es el menor de los hijos usando el comparador de prioridades
                        if(comp.compare(elems[hi].getKey() , elems[hd].getKey())<0){
                            m=hi;
                        }
                        else m=hd;
                    } 
                    else m=hi;    //Si hay hijo izquierdo y no hay hijo derecho, el mínimo es el izq
                    //Me fijo si hay que intercambiar el actual con el menor de sus hijos
                    if(comp.compare(elems[i].getKey(),elems[m].getKey())>0){
                        Entrada<K,V> aux = elems[i];    // Intercambio la entrada i con la m
                        elems[i]=elems[m];
                        elems[m]=aux;
                        i=m; // Reinicializo i para en la siguiente iteración actualizar a partir de posición m.
                    }else seguir = false; //Si la comparación de entrada i con la m dio bien, termino.
                }
            }
            return minimo;
        }
    }
    //removeMin(n) = O(h) = O(log2(n)) El método tiene la complejidad del bucle while, 
    //que en el peor escenario realiza tantas iteraciones como altura h tiene árbol 

    private void aumentar(){
        Entrada<K,V> [] a = (Entrada<K,V> []) new Entrada[elems.length*2];
        for(int i=1; i<elems.length; i++)
            a[i]=elems[i];
        elems=a;
    }
}