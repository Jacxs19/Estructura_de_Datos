package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelsegundoparcial.Parcial1.recu;

import ar.edu.uns.cs.ed.TDAS_Implementados.Entrada;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdaarbolbinario.BinaryTree;
import ar.edu.uns.cs.ed.TDAS_Implementados.TNodo;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;

public class ejercicios<K,V,E> {


    //Ej1
    

    public int removeMasivo(Iterable<K> it) throws InvalidKeyException{
        int i=0;                                                                    //C1
        if(it!=null){                                                               //C2
            for(K llave : it){                                                      //O(k), donde k es la cantidad de llaves del iterador
                if(llave == null)                                                   //C3
                    throw new InvalidKeyException("Llave invalida");              //C4
                PositionList<Entrada<K,V>> listaBucket = buckets[h(llave)];         //C5
                for(Position<Entrada<K,V>> llaves : listaBucket.positions()){       //O(1), ya que la funcion de hash esta bien implementada y no todas las entradas caen en el mismo bucket
                    if(llaves.element().getKey().equals(llave)){                    //C6
                        listaBucket.remove(llaves);                                 //C7
                        i++;                                                        //C8
                        n--;                                                        //C9
                        break;
                    }
                }
            }
        }
        return i;                                                               //C8
    }

    private int h(K key){
        return Math.abs(key.hashCode())%N;
    }

    //T_removeMasivo(n)= O(k), donde k es la cantidad de llaves del iterador pasado por parametro.
    //En el peor de los casos el iterador tiene la misma cantidad de llaves que de entradas del mapeo y cada una
    //cae en distinto bucket, como el Mapeo esta implementado con hash abierto y asumimos que hay una buena funcion de hash,
    //el iterador de las posiciones del bucket es de O(1), ya que el rehash es muy poco frecuente, por lo que utilizamos
    //un tiempo "amortiguado".

    //Ej2

    public void insertarIzquierdo(BinaryTree<Character> A, Character c1, Character c2){
        if(A!=null && !A.isEmpty()){                                                                    //C1
            PositionList<Position<Character>> lista=new ListaDoblementeEnlazada<Position<Character>>(); //C2
            recorridoPostOrden(A,lista,A.root());                                                       //O(n), ya que hago una lista en posOrden de todos los nodos del arbol.
            for(Position<Character> posiciones : lista){                                                //O(k), donde k es la cantidad de nodos en la lista los cuales cumplen la condicion pedida
                Character c = posiciones.element();                                                     //C3
                if(!A.hasLeft(posiciones) && A.hasRight(posiciones) && c.equals(c1)){                   //C4
                    A.addLeft(posiciones, c2);                                                          //O(1)
                }
            }
        }
    }

    public void recorridoPostOrden(BinaryTree<Character> A, PositionList<Position<Character>> l , Position<Character> pos){
        if(A.hasLeft(pos))
            recorridoPostOrden(A,l,A.left(pos));
        if(A.hasRight(pos))
            recorridoPostOrden(A,l,A.right(pos));
        l.addLast(pos);
    }


    //Ej3

    public boolean eliminarMedio(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);                                               //O(1)
        if(nodo=raiz)                                                                   //C1
            throw new InvalidPositionException("p es la raiz");                     //C2
        Position<TNodo<E>> posicionNodo=null;                                           //C3    POS DEL NODO A BORRAR
        boolean esta=false;                                                             //C4
        for(Position<TNodo<E>> hijos : nodo.getPadre().getHijos().positions()){         //O(k1), donde k1 es la cantidad de hermanos del nodo.
            if(hijos.element()==nodo){                                                  //C5
                posicionNodo=hijos;                                                     //C6
                esta=true;                                                              //C7
                break;
            }
        }
        if(!esta)                                                                       //C8
            throw new InvalidPositionException("el padre no conoce a p");           //C9
        if(nodo.getPadre().getHijos().first() != posicionNodo && nodo.getPadre().getHijos().last()!= posicionNodo){     //C10
            if(!nodo.getHijos().isEmpty()){                                                                             //C11
                for(TNodo<E> hijos : nodo.getHijos()){                                                                  //O(k2), donde k2 es la cantidad de hijos del nodo
                    nodo.getPadre().getHijos().addBefore(posicionNodo,hijos);                                           //C12
                    hijos.setPadre(nodo.getPadre());                                                                    //C13
                }
            }
            nodo.getPadre().getHijos().remove(posicionNodo);                                                            //C14
            size--;                                                                                                     //C15
            return true;                                                                                                //C16
        }
        return false;                                                                                                   //C17
    }

    //T_eliminarMedio(n)= O(n), donde n es la cantidad de nodos hijos del padre y nodos del hijo. Ya que primero veo que la posicion 
    //pasada por parametro pertenezca al arbol (buscando entre los hijos del padre) y guardar la posicion para luego eliminarla.
    //Luego itero en base a los hijos del nodo, por lo que el tiempo de ejecucion depende exclusivamente de los hijos del nodo.
    //En el peor de los casos la raiz solo tiene un hijo (la posicion pasada) y luego todos los demas nodos del arbol son hijos del nodo pasado.
    //Por lo tanto el tiempo de ejecucion seria O(n-1) 

    private TNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new InvalidPositionException("p invalida");
            if(p.element()==null)
                throw new InvalidPositionException("posicion borrada anteriormente");
            return (TNodo<E>) p;
        }catch (ClassCastException e){
            throw new InvalidPositionException("p no es un nodo de arbol");
        }
    }





    
}
