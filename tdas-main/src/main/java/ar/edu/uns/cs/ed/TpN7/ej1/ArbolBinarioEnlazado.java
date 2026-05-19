package ar.edu.uns.cs.ed.TpN7.ej1;

import ar.edu.uns.cs.ed.tdas.tdaarbolbinario.BinaryTree;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import java.util.Iterator;


public class ArbolBinarioEnlazado<E> implements BinaryTree<E>{
    protected BTNode<E> raiz;
    protected int size;

    public ArbolBinarioEnlazado(){
        raiz=null;
        size=0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public boolean hasLeft(Position<E> v){
        BTPosition<E> p = checkPosition(v);
        return p.getLeft() !=null;
    }
    public boolean hasRight(Position<E> v){
        BTNode<E> p = checkPosition(v);
        return p.getRight() !=null;
    }
    public boolean isInternal(Position<E> v){
        return hasLeft(v) || hasRight(v);
    }
    public Position<E> left(Position<E> v){
        BTNode<E> p = checkPosition(v);
        if(hasLeft(v)){
            return p.getLeft();
        }
        throw new BoundaryViolationException("v no tiene hijo izquierdo");
    }
    public Position<E> right(Position<E> v){
        BTNode<E> p = checkPosition(v);
        if(hasRight(v))
            return p.getRight();
        throw new BoundaryViolationException("v no tiene hijo derecho");
    }
    public Position<E> addLeft(Position<E> v, E r){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        BTNode<E> p = checkPosition(v);
        if(hasLeft(v))
            throw new InvalidOperationException("v ya tiene un hijo izquierdo");
        BTNode<E> izq = new BTNode<E> (r,null,null,p);
        p.setLeft(izq);
        size++;
        return izq;
    }
    public Position<E> addRight(Position<E> v, E r){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        BTNode<E> p = checkPosition(v);
        if(hasRight(v))
            throw new InvalidOperationException("v ya tiene un hijo derecho");
        BTNode<E> derecho = new BTNode<E> (r,null,null,p);
        p.setRight(derecho);
        size++;
        return derecho;
    }
    public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        BTNode<E> v = checkPosition(r);
        if(isInternal(v))
            throw new InvalidPositionException("v no es una hoja");
        size+= T1.size() + T2.size();
        if(!T1.isEmpty()){
            BTNode<E> izq = checkPosition(T1.root());
            izq.setPadre(v);
            v.setLeft(izq);
        }
        if(!T2.isEmpty()){
            BTNode<E> der = checkPosition(T2.root());
            der.setPadre(v);
            v.setRight(der);
        }
    }

    public Iterator<E> iterator(){
        PositionList<E> lista = new ListaDoblementeEnlazada<E>();
        if(raiz!=null)
            preOrden(lista,raiz);
        return lista.iterator();
    }
    public Iterable<Position<E>> positions(){
        PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
        if(raiz!=null){
            preOrdenPos(lista,raiz);
        }
        return lista;
    }

    public E replace(Position<E> v, E e){
        BTNode<E> nodo = checkPosition(v);
        E elemento = nodo.element();
        nodo.setElemento(e);
        return elemento;
    }

    public Position<E> root(){
        if(size==0)
            throw new EmptyTreeException("Arbol vacio");
        return raiz;
    }
    public Position<E> parent(Position<E> v){
        BTNode<E> nodo = checkPosition(v);
        if(nodo==raiz)
            throw new BoundaryViolationException("La posicion ");
        return nodo.getPadre();
    }

    public Iterable<Position<E>> children(Position<E> v){
        BTNode<E> nodo = checkPosition(v);
        PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
        if(nodo.getLeft()!=null){
            lista.addLast(nodo.getLeft());
        }
        if(nodo.getRight()!=null)
            lista.addLast(nodo.getRight());
        return lista;
    }
    
    public boolean isExternal (Position<E> v){
        return !hasRight(v) && !hasLeft(v);
    }

    public boolean isRoot(Position<E> v){
        BTNode<E> nodo = checkPosition(v);
        return nodo == raiz;
    }

    public void createRoot(E e){
        if(!isEmpty())
            throw new InvalidOperationException("El arbol ya tiene raiz");
        raiz = new BTNode<E>(e, null,null,null);
        size++;
    }

    public void remove(Position<E> p){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        if(isInternal(p)){ 
            if()
            return;
        }
    }


    private void preOrdenPos(PositionList<Position<E>> l , BTNode<E> nodo){
        l.addLast(nodo);
        if(nodo.getLeft()!=null)
            preOrdenPos(l,checkPosition(nodo.getLeft()));
        if(nodo.getRight()!=null)
            preOrdenPos(l,checkPosition(nodo.getRight()));
    }

    private void preOrden(PositionList<E> l , BTNode<E> nodo){
        l.addLast(nodo.element());
        if(nodo.getLeft()!=null){
            BTNode<E> left = checkPosition(nodo.getLeft());
            preOrden(l,left);
        }
        if(nodo.getRight()!=null){
            BTNode<E> right = checkPosition(nodo.getRight());
            preOrden(l,right);
        }
    }


    private BTNode<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new InvalidPositionException("Posicion invalida");
            if(p.element()==null)
                throw new InvalidPositionException("Posicion eliminada anteriormente.");
            return (BTNode<E>) p;
        }catch (ClassCastException e){
            throw new InvalidPositionException("p no es un nodo de arbol binario");
        }

    }
}
