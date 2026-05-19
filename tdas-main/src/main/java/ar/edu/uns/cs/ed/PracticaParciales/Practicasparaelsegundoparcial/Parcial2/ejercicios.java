package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelsegundoparcial.Parcial2;

import ar.edu.uns.cs.ed.TDAS_Implementados.Entrada;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TDAS_Implementados.TNodo;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class ejercicios<E> {

    public int cantEntradas(K key, V value){
        int cant=0;                                                     //C1
        if(k==null)                                                     //C2
            throw new InvalidKeyException("llave invalida");         //C3
        PositionList<Entrada<K,V>> bucket = a[h(key)];                     //C4
        for(Entrada<K,V> entradas : bucket){                            //O(1) , asumimos que hay buena distribucion de claves y como tenemos una buena funcion de hash.
            if(entradas.getKey().equals(key))      //C5                 //El tamaño promedio del bucket es constante, ya que no todos las entradas caen en el mismo.
                cant++;                             //C6
        }
        return cant;                                //C7
    }
    //T_cantEntradas(n)= O(1), ya que estamos en un diccionario con hash abierto y tenemos una buena funcion de hash
    //por lo que no todas las entradas caen en el mismo bucket. Pero si este fuera el caso el metodo seria de O(n), donde n es la
    //cantidad de entradas en el bucket (peor caso donde tenemos una mala funcion de hash y todas las entradas estan en el mismo bucket). 

    private int h(K key){
        return Math.abs(key.hashCode())%N;
    }


    //Ej2
    //a)

   // class TNodo<E> implements Position<E>{
  //      private E elemento;
    //    private TNodo<E> padre;
      //  private PositionList<TNodo<E>> hijos;
        
        //public TNodo(E element, TNodo<E> padre){
          //  elemento=element;
            //this.padre=padre;
            //hijos=new ListaDoblementeEnlazada<TNodo<E>>();
        //}
        //public TNodo(E element){
         //   this(element, null);
        //}

        //public E element(){
          //  return elemento;
        //}
        //+getters y setters
    //}

    public void añadirUltimosHijos(Position<E> p, E r){
        TNodo<E> nodo = checkPosition(p);                       //C1
        añadirUltimo(nodo,r);                                   //O(n), donde n es la cantidad de nodos del sub-arbol
        return;                                                 //C2
    }
    //T_añadirUltimosHijos(n)= O(n), donde n es la cantidad de nodos del arbol.
    //En el peor de los casos la posicion pasada por parametro es la raiz, por lo que 
    //debo visitar todos los nodos del arbol e insertarle un ultimo hijo con rotulo r.

    private void añadirUltimo(TNodo<E> n, E r){
        for(TNodo<E> nodosHijos : n.getHijos()){
            añadirUltimo(nodosHijos, r);
        }
        TNodo<E> nodoInsertar = new TNodo<E>(r,n);
        n.getHijos().addLast(nodoInsertar);
    }

    private TNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new InvalidPositionException("p invalida");
            if(p.element()==null)
                throw new InvalidPositionException("posicion borrada anteriormente");
            return (TNodo<E>) p;
        }catch(ClassCastException e){
            throw new InvalidPositionException("p no pertenece a un nodo de arbol");
        }
    }


}
