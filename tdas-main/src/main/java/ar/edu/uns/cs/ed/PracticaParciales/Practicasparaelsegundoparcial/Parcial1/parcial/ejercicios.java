package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelsegundoparcial.Parcial1.parcial;

import ar.edu.uns.cs.ed.TDAS_Implementados.Entrada;
import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.TDAS_Implementados.TNodo;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdaarbolbinario.BinaryTree;

public class ejercicios<E,K,V> {

    //Ej1

    public int todas(K key) throws InvalidKeyException{
        if(key ==null)
            throw new InvalidKeyException("Llave invalida");
        int contador=0;
        PositionList<Entrada<K,V>> bucket = A[h(key)];
        for(Entrada<K,V> entradas : bucket){
            if(entradas.getKey().equals(key))
                contador++; 
        }
        return contador;
    }


    private int h (K key){
        return Math.abs(key.hashCode())%N;
    }

    //T_todas(n)= O(1), debido a que tenemos una buena funcion de hash, lo que permite
    //una buena distribucion de claves, asumimos que no todas las entradas caen en un solo bucket
    //por lo que el recorrido es lineal, implica que es de O(1)



    //Ej2

    public Mapeo<Character, Integer> eliminarHojas(BinaryTree<Character> arbol, Position<Character> p) throws InvalidPositionException{
        Mapeo<Character, Integer> map = new MapeoConHashAbierto<Character,Integer>();
        if(arbol!=null && !arbol.isEmpty()){
            eliminarEInsertar(arbol, p, map);
        }
        return map;
    }

    private void eliminarEInsertar(BinaryTree<Character> A, Position<Character> p, Mapeo<Character,Integer> m){             //Recorrido posOrden, (miro los hijos y luego el padre.)
        if(p!=null){
            boolean EsHoja = (!arbol.hasLeft(p) && !arbol.hasRight(p)); 
            if(A.hasLeft(p))
                eliminarEInsertar(A,A.left(p),m);
            if(A.hasRight(p))
                eliminarEInsertar(A, A.right(p), m);
            if(EsHoja){
                Character c = p.element();
                if(m.get(c)==null)
                    m.put(c,1);
                else
                    m.put(c,m.get(c)+1);
                A.remove(p);
            }
        }
    }


    //Ej3

    public boolean eliminarUltimo(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        if(nodo==raiz)
            throw new InvalidPositionException("No se puede eliminar la raiz");
        if(nodo.getPadre().getHijos().isEmpty())
            return false;
        Position<TNodo<E>> Posultimo =nodo.getPadre().getHijos().last();               //Consigo el ultimo hijo del padre del nodo
        if(Posultimo.element()!=nodo)
            return false;
        if(nodo.getHijos().size()!=0){
            for(TNodo<E> hijos : nodo.getHijos()){                                     //Itero en base a los TNodos de la lista de hijos del nodo a eliminar
                nodo.getPadre().getHijos().addBefore(Posultimo, hijos);
                hijos.setPadre(nodo.getPadre());
            }
        }
        nodo.getPadre().getHijos().remove(Posultimo);
        nodo.setElemento(null);
        nodo.setPadre(null);
        size--;
        return true;
    }

    private TNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new InvalidPositionException("p invalida");
            if(p.element()==null)
                throw new InvalidPositionException("p borrada anteriormente");
            return (TNodo<E>) p;
        }catch(ClassCastException e){
            throw new InvalidPositionException("p no es un nodo de arbol");  
        }
    }
    //T_eliminarUltimo(n)= O(k), donde k es la cantidad de hijos del nodo a eliminar, ya que
    //En el peor de los casos el nodo p tiene hijos, por lo que 
    //debo visitar a los hijos de la posicion del nodo pasada por parametro una vez e insertarlo en el mismo orden que estaban.




    
}
