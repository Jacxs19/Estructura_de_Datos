package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelprimerparcial.Parcial2;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TpN4.Ej1.*;
import ar.edu.uns.cs.ed.TpN4.Ej2.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.Iteradores.ElementIterator;
import java.util.Iterator;
import java.util.Stack;
import ar.edu.uns.cs.ed.TpN3.Ej1.ArregloPila;

public class ej<E> extends ListaDoblementeEnlazada<E> {
    //Ej 1.
    public int duplicarElem(E elem) throws EmptyListException{
        int cant=0;
        if(isEmpty())
            throw new EmptyListException("Lista Vacia");
        PositionList<Position<E>> aux = ListaDoblementeEnlazada<Position<E>>();
        for(Position<E> p : this.positions()){
            if (p.element().equals(elem)){
                aux.addLast(p);
                cant++;
            }
        }
        
        for(Position <E> p :aux){
            addAfter(p, p.element());
        }
        return cant;
        }
    
    public void addAfter (Position<E> p , E elem){
        if(isEmpty())
            throw new InvalidPositionException("Lista vacia");
        DNodo<E> aux = checkPosition(p);
        DNodo<E> elemento = new DNodo<E> (elem);
        aux.getSiguiente().setAnterior(elemento);
        elemento.setSiguiente(aux.getSiguiente());
        elemento.setAnterior(aux);
        aux.setSiguiente(elemento);
        cantElems++;
    }
    private DNodo<E> checkPosition(Position<E> p){
        try{
            if(p==null)
                throw new NullPointerException("Posicion Invalida");
            if(p.element()==null)
                throw new InvalidPositionException("Elemento borrado anteriormente");
            return (DNodo<E>) p;
        }catch(ClassCastException e){
            throw new ClassCastException("p no era un nodo Doblemente enlazado.");
        }
    }


    //Preguntar Break
    //Ej2, Dado dos listas genericas l1, l2, retorne una lista l3 con los elementos que se encuentran tanto en l1 como en l2. Comparar por equivalencia.
    public PositionList<E> listasIncluidas(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> l3 = new ListaDoblementeEnlazada<E>();
        for(Position<E> pos1 : l1.positions()){
            for(Position<E> pos2 : l2.positions()){
                if(pos1.element().equals(pos2.element())){
                    l3.addLast(pos2.element());
                    break;
                }
            }
        }
        return l3;
    }


    //Ej 3.
    public Stack<Character> eliminarDePila(Stack<Character> p , Character c){
        Stack<Character> pilaADevolver = new pila<Character>();                 //Asumo que pila implementa Stack
        if(p!=null && !p.isEmpty()){
            Stack<Character> pilaAux = new pila<Character>();
            Character aux;
            while(!p.isEmpty()){
                aux=p.pop();
                if(!aux.equals(c)){
                    pilaAux.push(aux);
                }
            }
            while(!pilaAux.isEmpty()){
                pilaADevolver.push(pilaAux.pop());
            }
        return pilaADevolver;
        }
        return pilaADevolver;       //Devuelvo una pila vacia si p esta vacio.
    }










    }
    
