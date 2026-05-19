package ar.edu.uns.cs.ed.TDAS_Implementados;

public class BTNode<E> implements BTPosition<E>{
    private E element;
    private BTPosition<E> left, right, parent;

    public BTNode(E element, BTPosition<E> left, BTPosition<E> right, BTPosition<E> padre){
        this.left=left;
        this.element=element;
        this.right=right;
        parent=padre;
    }
    public void setElemento(E e){
        element=e;
    }
    public void setPadre(BTPosition<E> p){
        parent=p;
    }
    public void setRight(BTPosition<E> right){
        this.right=right;
    }
    public void setLeft(BTPosition<E> left){
        this.left=left;
    }
    public E element(){
        return element;
    }
    public BTPosition<E> getRight(){
        return right;
    }
    public BTPosition<E> getLeft(){
        return left;
    }
    public BTPosition<E> getPadre(){
        return parent;
    }
    
}
