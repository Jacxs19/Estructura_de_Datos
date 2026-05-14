package ar.edu.uns.cs.ed.TpN7.ej1;

import ar.edu.uns.cs.ed.tdas.tdaarbolbinario.BinaryTree;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;


public class ArbolBinarioEnlazado<E> implements BinaryTree<E>{
    private BTPosition<E> raiz;
    private int size;

    public ArbolBinarioEnlazado(){
        raiz=null;
        size=0;
    }

    public int size(){
        return size;
    }
    public boolean hasLeft(Position<E> v){
        BTPosition<E> p = checkPosition(v);
        return p.getLeft() !=null;
    }
    public boolean hasRight(Position<E> v){
        BTPosition<E> p = checkPosition(v);
        return p.getRight() !=null;
    }
    public boolean isInternal(Position<E> v){
        return hasLeft(v) || hasRight(v);
    }
    public Position<E> left(Position<E> v){
        BTPosition<E> p = checkPosition(v);
        if(hasLeft(v)){
            return p.getLeft();
        }
        throw new BoundaryViolationException("v no tiene hijo izquierdo");
    }
    public Position<E> right(Position<E> v){
        BTPosition<E> p = checkPosition(v);
        if(hasRight(v))
            return p.getRight();
        throw new BoundaryViolationException("v no tiene hijo derecho");
    }
    public Position<E> addLeft(Position<E> v, E r){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        BTPosition<E> p = checkPosition(v);
        if(hasLeft(v))
            throw new InvalidOperationException("v ya tiene un hijo izquierdo");
        BTPosition<E> izq = new BTNode<E> (r,null,null,p);
        p.setLeft(izq);
        size++;
        return izq;
    }
    public Position<E> addRight(Position<E> v, E r){
        if(size==0)
            throw new InvalidPositionException("Arbol vacio");
        BTPosition<E> p = checkPosition(v);
        if(hasRight(v))
            throw new InvalidOperationException("v ya tiene un hijo derecho");
        BTPosition<E> derecho = new BTNode<E> (r,null,null,p);
        p.setRight(derecho);
        size++;
        return derecho;
    }
    public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2){
        
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
