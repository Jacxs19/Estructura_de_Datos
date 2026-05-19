package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelsegundoparcial.EjProfe;

import ar.edu.uns.cs.ed.TDAS_Implementados.TDAArbol;
import ar.edu.uns.cs.ed.TDAS_Implementados.TNodo;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;

import java.util.Iterator;

import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;

public class metodos<E> {
    

    //Ej1
    public int sizeSubarbol(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);                                       
        if(nodo==raiz)
            return size;                                            
        TNodo<E> padre = nodo.getPadre();                                       
        PositionList<TNodo<E>> hijosPadre = padre.getHijos();                   
        boolean perteneceAlArbol= false;                                        
        for(TNodo<E> hijos : hijosPadre){                                       
            if(hijos==nodo){                                                    
                perteneceAlArbol=true;                                          
                break;                                                          
            }
        }
        if(!perteneceAlArbol)
            throw new InvalidPositionException("La posicion no pertenece al arbol");
        int cantidad = cantidadNodos(p);                                        //k, donde k es la cantidad de hijos del sub-arbol.
        return cantidad;                                                        //C0
    }
    //T_sizeSubArbol(n)= Es de orden O(k), donde k es la cantidad de nodos del sub-arbol
    //ya que recorro a todos los nodos parientes (hijos, nietos, etc.) del nodo raiz del sub-arbol

    private int cantidadNodos(Position<E> p){
        TNodo<E> nodo = checkPosition(p);
        int cant=0;
        for(TNodo<E> hijos : nodo.getHijos())
            cant= cantidadNodos(hijos);
        return cant+1;
    }
    //Este metodo es de O(k), donde k es la cant. de hijos del sub-arbol.

    private TNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new InvalidPositionException("p es una referencia nula");
            if(p.element()==null)
                throw new InvalidPositionException("p fue borrada anteriormente");
            return (TNodo<E>) p;
        }catch(ClassCastException e){
            throw new InvalidPositionException("p no es un nodo de arbol");
        }
    }
    //Metodo lineal, es constante ya que solo tiene comparaciones, O(1).


    //Ej2

    public Map<Position<E>, Integer> mapSizeSubarboles() throws InvalidPositionException{
        Map<Position<E>, Integer> mapeo = new MapeoConHashAbierto<Position<E>,Integer>();
        for(Position<E> p : positions()){
            mapeo.put(p, sizeSubarbol(p));
        }
        return mapeo;
    }

    public Iterable<Position<E>> positions(){
        PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
        preOrden(lista,raiz);
        return lista;
    }

    private void preOrden(PositionList<Position<E>> l, Position<E> p){
        TNodo<E> nodo = checkPosition(p);
        for(TNodo<E> hijos : nodo.getHijos()){
            preOrden(l, hijos);
        }
        l.addLast(nodo);
    }
    //T_mapSizeSubarboles(n)= este metodo es de O(n), donde n es la cantidad de nodos
    //del arbol. Esto es ya que tengo que recorrer todos los nodos y sus hijos.

    //Ej3

    public int podarSubarbol(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        int cant= podar(nodo);
        return cant;        
    }

    private int podar(Position<E> n){
       int contador=0;
       TNodo<E> nodo = checkPosition(n);
        for(TNodo<E> nodosHijos : nodo.getHijos()){
            contador+= podar(nodosHijos);
        }
        if(nodo!=raiz){                                                                 //Con esto elimino el hijo de la lista del padre
            for(Position<TNodo<E>> pos : nodo.getPadre().getHijos().positions()){
                if(pos.element()==nodo){
                    nodo.getPadre().getHijos().remove(pos);
                    break;
                }
            }
        }
        nodo.setElemento(null);
        nodo.setPadre(null);
        size--;
        return contador +1;
    }

    //T_podarSubArbol(n)= en el peor de los casos, la posicion pasada por parametro es la raiz, por lo que tengo que recorrer todos los hijos de la raiz
    //e invalidarlos, por lo que este metodo es de O(k), donde k es la cantidad de nodos del sub-arbol.



    //Ej 4
    
    public void cambiarRotulo(Tree<E> T, E e, E f){
        if(T!=null && !T.isEmpty()){
            Iterator<Position<E>> it = T.positions().iterator();
            while(it.hasNext()){
                Position<E> pos= it.next();
                if(pos.element().equals(e))
                    T.replace(pos, f);
            }    
        }
    }
    //T_cambiarRotulo(n)= O(n), donde n es la cantidad de nodos del arbol, ya que 
    //recorro todos los nodos una vez y replace es O(1), ya que solo cambia el rótulo del nodo.





    //Ej 5

    public void insertarMasivo(E e, int x, Tree<E> A ){
        if(A!=null && ! A.isEmpty()){
            int nivel=0;
            Position<E> nodo = A.root();
            PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
            recorridoPorNiveles(A, nodo,x,nivel, lista);
            for(Position<E> pos : lista){                                                   //O(k), donde k es la cantidad de nodos en ese nivel
                A.addFirstChild(pos, e);
            }
        }


    }
    private void recorridoPorNiveles(Tree<E> A, Position<E> n, int x, int nivelActual, PositionList<Position<E>> l){
        if(nivelActual==x){
            l.addLast(n);
            return;
        }
        for(Position<E> p : A.children(n)){
            recorridoPorNiveles(A,p,x,nivelActual+1, l);
        }       
    }
    //T_insertarMasivo(n)= O(n), donde n es la cantidad de nodos del arbol. En el peor de los casos
    //el nivel a buscar es el ultimo del arbol, por lo que recorro una vez todos los nodos.
}