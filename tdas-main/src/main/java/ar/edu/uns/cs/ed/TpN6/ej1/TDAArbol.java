package ar.edu.uns.cs.ed.TpN6.ej1;

import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;
import java.util.Iterator;

public class TDAArbol<E> implements Tree<E>{
    private TNodo<E> raiz;
    private int size;

    public TDAArbol(){
        raiz=null;
        size=0;
    }
    public boolean isEmpty(){
        return raiz==null;
    }
    public int size(){
        return size;
    }
    
    public Position<E> root(){
        if(raiz==null)
            throw new EmptyTreeException("Arbol vacio");
        return raiz;
    }
    public Position<E> parent (Position<E> v){
        TNodo<E> aux = checkPosition(v);
        if(aux==raiz)
            throw new BoundaryViolationException("Raiz del arbol");
        return aux.getPadre();
    }
    public Iterable<Position<E>> children (Position<E> v){
        TNodo<E> aux = checkPosition(v);
        PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
        for(TNodo<E> nodos : aux.getHijos()){
            lista.addLast(nodos);
        }
        return lista;
    }
    public boolean isInternal(Position<E> v){       //Un nodo es interno si tiene al menos 1 hijo
        TNodo<E> aux = checkPosition(v);
        return !aux.getHijos().isEmpty();
    }
    public boolean isExternal(Position<E> v){
        TNodo<E> aux = checkPosition(v);
        return aux.getHijos().isEmpty();
    }
    public boolean isRoot(Position<E> v){
        TNodo<E> aux = checkPosition(v);
        return aux==raiz;
    }
    public void createRoot(E e){
        if(raiz!=null)
            throw new InvalidOperationException("El arbol ya posee una raiz");
        raiz= new TNodo<E>(e);
        size=1;
    }
    public Position<E> addFirstChild(Position<E> p, E e){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        TNodo<E> nodo = checkPosition(p);
        TNodo<E> nodoE = new TNodo<E> (e, nodo);
        nodo.getHijos().addFirst(nodoE);
        size++;
        return nodoE;        
    }
    public Position<E> addLastChild(Position<E> p, E e){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        TNodo<E> nodo = checkPosition(p);
        TNodo<E> nodoE = new TNodo<E> (e,nodo);
        nodo.getHijos().addLast(nodoE);
        size++;
        return nodoE;
    }
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        TNodo<E> nodo = checkPosition(p);
        TNodo<E> nodoHijoRB = checkPosition(rb);
        if(nodoHijoRB.getPadre()!=nodo)
            throw new InvalidPositionException("El TNodo rb no es hijo del nodo p");
        TNodo<E> nodoE = new TNodo<E> (e,nodo);
        PositionList<TNodo<E>> lista = nodo.getHijos();
        Position<TNodo<E>> posicionEncontrada=null;             //Le asigno null porque y a verifique que luego lo voy a encontrar
        for(Position<TNodo<E>> nodos : lista.positions()){
            if(nodos.element()==nodoHijoRB){
                posicionEncontrada = nodos;
                break;
            }
        }
        lista.addBefore(posicionEncontrada,nodoE);
        size++;
        return nodoE;
    }
    public Position<E> addAfter (Position<E> p, Position<E> lb, E e){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        TNodo<E> nodo = checkPosition(p);
        TNodo<E> nodoHijoLB = checkPosition(lb);
        if(nodoHijoLB.getPadre()!=nodo)
            throw new InvalidPositionException("El TNodo lb no es hijo del nodo p");
        TNodo<E> nodoE = new TNodo<E> (e, nodo);
        PositionList<TNodo<E>> lista = nodo.getHijos();
        Position<TNodo<E>> posicionEncontrada=null;             //Le asigno null porque ya verifique que luego lo voy a encontrar
        for(Position<TNodo<E>> posiciones : lista.positions()){
            if(posiciones.element()==nodoHijoLB){
                posicionEncontrada=posiciones;
                break;
            }
        }
        lista.addAfter(posicionEncontrada, nodoE);
        size++;
        return nodoE;
    }
    public void removeExternalNode(Position<E> p){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        TNodo<E> posicion = checkPosition(p);
        if(!posicion.getHijos().isEmpty())
            throw new InvalidPositionException("p no es hoja");
        if(p==raiz){
            raiz=null;
            size=0;
            posicion.setElemento(null);
            return;
        }
        TNodo<E> padre = posicion.getPadre();
        PositionList<TNodo<E>> hijosPadre = padre.getHijos();
        Position<TNodo<E>> posicionEncontrada =null;
        boolean encontre=false;
        Iterable<Position<TNodo<E>>> posiciones = hijosPadre.positions();
        Iterator<Position<TNodo<E>>> it = posiciones.iterator();
        while(it.hasNext() && !encontre){
            posicionEncontrada=it.next();
            if(posicionEncontrada.element()==posicion)
                encontre=true;
        }
        if(!encontre)
            throw new InvalidPositionException("p no pertenece al arbol");
        hijosPadre.remove(posicionEncontrada);
        posicion.setElemento(null);
        posicion.setPadre(null);
        size--;
    }
    public void removeInternalNode(Position<E> p){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        TNodo<E> nodo = checkPosition(p);
        if(nodo.getHijos().isEmpty())
            throw new InvalidPositionException("p no es un nodo interno");
        if(nodo==raiz && nodo.getHijos().size()==1){
            raiz=nodo.getHijos().first().element();
            raiz.setPadre(null);
            nodo.setElemento(null);
            size--;
            return;
        }
        if(nodo == raiz && nodo.getHijos().size()!=1)
            throw new InvalidPositionException("No se puede eliminar la raiz si tiene mas de un hijo.");    //Caso en donde me den la raiz, pero esta tiene mas de un hijo.
        PositionList<TNodo<E>> listaDeHijos = nodo.getHijos();              //Guardo los hijos del nodo a eliminar
        PositionList<TNodo<E>> auxiliar = nodo.getPadre().getHijos();       //Consigo la lista de los hijos del padre
        Position<TNodo<E>> posicion =null;                                  //La voy a buscar
        Iterator<Position<TNodo<E>>> it = auxiliar.positions().iterator();
        boolean encontre=false;                                             //"Bandera" para saber si la posicion pertenece al arbol.
        while(it.hasNext()&&!encontre){
            posicion=it.next();
            if(posicion.element()==nodo){
                encontre=true;
            }
        }
        if(!encontre)
            throw new InvalidPositionException("p no pertenece al arbol");
        
        for(TNodo<E> nodos : listaDeHijos){
            auxiliar.addBefore(posicion,nodos);
            nodos.setPadre(nodo.getPadre());        //Nodo es el nodo eliminado, y nodos son los hijos de nodo (actualizo el padre).
        }
        auxiliar.remove(posicion);
        nodo.setPadre(null);
        nodo.setElemento(null);
        size--;
    }

    public void removeNode(Position<E> p){
        if(size==0)
            throw new InvalidPositionException("Posicion Invalida");
        TNodo<E> nodo = checkPosition(p);
        if(nodo.getHijos().isEmpty()){ 
            removeExternalNode(p);
            return;
        }
        if(nodo==raiz && nodo.getHijos().size()==1){
            raiz=nodo.getHijos().first().element();
            raiz.setPadre(null);
            nodo.setElemento(null);
            size--;
            return;
        }
        if(nodo == raiz && nodo.getHijos().size()!=1)
            throw new InvalidPositionException("No se puede eliminar la raiz si tiene mas de un hijo.");
        //Si pase todos los if, y no salio significa que es un nodo interno.
        removeInternalNode(p);
        return;
    }

    //Devuelve un iterador de los elementos almacenados en el árbol en preorden.
    public Iterator<E> iterator(){
        PositionList<E> lista = new ListaDoblementeEnlazada<E>();
        for(Position<E> p : positions())
            lista.addLast(p.element());
        return lista.iterator();
    }

    public Iterable<Position<E>> positions(){
        PositionList<Position<E>> l = new ListaDoblementeEnlazada<Position<E>>();
        if(!isEmpty())
            pre(raiz,l);
        return l;

    }

    public E replace(Position<E> v, E e){
        TNodo<E> nodo = checkPosition(v);
        E elementoViejo = nodo.element();
        nodo.setElemento(e);
        return elementoViejo;
    }

    private void pre(TNodo<E> v, PositionList<Position<E>> l){
        l.addLast(v);
        for(TNodo<E> h : v.getHijos())
            pre(h,l);
    }

    private TNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new InvalidPositionException("Posicion invalida");
            if(p.element()==null)
                throw new InvalidPositionException("Posicion borrada anteriormente");
            return (TNodo<E>) p;
        }catch(ClassCastException e){
            throw new InvalidPositionException("No corresponde a un nodo de arbol");
        }
    }
}