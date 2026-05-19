package ar.edu.uns.cs.ed.TDAS_Implementados;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import java.util.Iterator;



public class ListaDoblementeEnlazada<E> implements PositionList<E>{
    protected DNodo<E> head;
    protected DNodo<E> tail;
    protected int tamanio;

    //Constructor
    public ListaDoblementeEnlazada(){
        head= new DNodo<E>(null, null, null);
        tail= new DNodo<E>(null,null,head);
        head.setSiguiente(tail);
        tamanio=0;
    }
    //Consultas
    public int size(){
        return tamanio;
    }
    public boolean isEmpty(){
        return tamanio==0;
    }
    public Position<E> first(){
        if (isEmpty())
            throw new EmptyListException("Lista vacia");
        return head.getSiguiente();
    }
    public Position<E> last(){
        if (isEmpty())
            throw new EmptyListException("Lista vacia");
        return tail.getAnterior();
    }
    public Position<E> next(Position<E> p){
        if(isEmpty())
            throw new InvalidPositionException("Lista Vacia");
        DNodo<E> n= checkPosition(p);
        if(n.getSiguiente()==tail)
            throw new BoundaryViolationException("Es el ultimo de la lista");
        return n.getSiguiente();
    }
    public Position<E> prev (Position<E> p){
        if(isEmpty())
            throw new InvalidPositionException("Lista Vacia");
        DNodo<E> n= checkPosition(p);
        if(n.getAnterior()==head)
            throw new BoundaryViolationException("Es el primero de la lista");
        return n.getAnterior();
    }
    //Recorridos
    public void addFirst(E element){
        DNodo<E> aux = new DNodo<E> (element, head.getSiguiente(),head);
        head.getSiguiente().setAnterior(aux);
        head.setSiguiente(aux);
        tamanio++;
    }
    public void addLast(E element){
        DNodo<E> aux= new DNodo<E> (element, tail,tail.getAnterior());
        tail.getAnterior().setSiguiente(aux);
        tail.setAnterior(aux);
        tamanio++;
    }
    
    public void addAfter(Position<E> p, E element){
        if(isEmpty())
            throw new InvalidPositionException("Lista vacia");
        DNodo <E> aux= checkPosition(p);
        DNodo <E> nodo=new DNodo<E>(element, aux.getSiguiente(),aux);
        aux.getSiguiente().setAnterior(nodo);
        aux.setSiguiente(nodo);
        tamanio++;
    }
    public void addBefore(Position<E> p, E element){
        if(isEmpty())
            throw new InvalidPositionException("Lista vacia");
        DNodo<E> aux= checkPosition(p);
        DNodo<E> nodo = new DNodo<E> (element, aux, aux.getAnterior());
        aux.getAnterior().setSiguiente(nodo);
        aux.setAnterior(nodo);
        tamanio++;
    }
    public E remove (Position<E> p){
        if(isEmpty())
            throw new InvalidPositionException("Lista vacia");
        DNodo<E> aux = checkPosition(p);
        aux.getSiguiente().setAnterior(aux.getAnterior());
        aux.getAnterior().setSiguiente(aux.getSiguiente());
        E elemento = (E) aux.element();
        //Elimino el nodo aux
        aux.setAnterior(null);
        aux.setSiguiente(null);
        aux.setElemento(null);
        tamanio--;
        return elemento;
    }
    public E set (Position<E> p, E element){
        if(isEmpty())
            throw new InvalidPositionException("Lista vacia");
        DNodo<E> aux = checkPosition(p);
        E elemento=aux.element();
        aux.setElemento(element);
        return elemento;
    }
    public Iterator<E> iterator(){
        return new ElementIterator<E>(this);
    }
    public Iterable<Position<E>> positions(){
        PositionList<Position<E>> positions = new ListaDoblementeEnlazada<Position<E>>();
        DNodo<E> nodoAux= head.getSiguiente();
        while(nodoAux!=tail){
            positions.addLast(nodoAux);
            nodoAux=nodoAux.getSiguiente();
        }
        return positions;  
    }

    //Metodos aux
    private DNodo<E> checkPosition(Position<E> p){  //Preguntar.
        try{ 
            if(p==null)
                throw new InvalidPositionException("Posicion Nula");
            if(p.element()==null)
                throw new InvalidPositionException("p eliminada previamente");
            return (DNodo<E>) p;
        }
        catch(ClassCastException e){
            throw new InvalidPositionException("p no es un nodo de lista");
        }

    }

























    /*PositionList<Position<E>> listaAux= new ListaDoblementeEnlazada<Position<E>>();
        if(!isEmpty()){
            Position <E> p = first();
            while (p!=null){
                listaAux.addLast(p);
                try{
                    if(p!=last())
                        try{
                            p=next(p);
                    } catch (InvalidPositionException e){
                        System.out.println("Posicion Invalida");
                    } catch (BoundaryViolationException e){
                        System.out.println("El ultimo elemento no tiene siguiente");
                    }
                    else
                        p=null;
                } catch(EmptyListException e){
                    System.out.println("No hay ultimo elemento en lista vacia.");
                }
            }
        }
        return listaAux; */
}
























/*PositionList<Position<E>> listaAux= new ListaDoblementeEnlazada<Position<E>>();
        if(!isEmpty()){
            Position <E> p = first();
            while (p!=null){
                listaAux.addLast(p);
                try{
                    if(p!=last())
                        try{
                            p=next(p);
                    } catch (InvalidPositionException e){
                        System.out.println("Posicion Invalida");
                    } catch (BoundaryViolationException e){
                        System.out.println("El ultimo elemento no tiene siguiente");
                    }
                    else
                        p=null;
                } catch(EmptyListException e){
                    System.out.println("No hay ultimo elemento en lista vacia.");
                }
            }
        }
        return listaAux; */
