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


        //o
    public int duplicarElem(E elem) throws EmptyListException{
        if(cantElems==0)
            throw new EmptyListException("Lista vacia");
        int cant=0;
        DNodo<E> nodo = head.getSiguiente();
        while(nodo!=tail){
            DNodo<E> sig = nodo.getSiguiente();
            if(nodo.element().equals(elem)){
                DNodo<E> insertar = new DNodo<E> (elem);
                sig.setAnterior(insertar);
                insertar.setSiguiente(sig);
                nodo.setSiguiente(insertar);
                insertar.setAnterior(nodo);
                cantElems++;
                cant++;
            }
            nodo=sig;
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

    //o
    public PositionList<E> metodo (PositionList<E> L1, PositionList<E> L2){
        PositionList<E> intercalada = new listaDE<E>();
        for(E elem1 : L1)
            for(E elem2 :L2){
                if(elem1.equals(elem2)){
                    if(!pertenece(elem1,intercalada)){
                        intercalada.addLast(elem1);
                    }
                    break;
                }
            }
        return intercalada;
    }


    public PositionList<E> metodo (PositionList<E> L1, PositionList<E> L2){
        PositionList<E> intercalada = new listaDE<E>();
        boolean esta=false;
        for(E elem1 : L1){
            Iterator<E> it2 = L2.iterator();
            esta=false;
            while(it2.hasNext() && !esta){
                E elem2 = it2.next();
                if(elem1.equals(elem2)){
                    if(!pertenece(elem1,intercalada)){
                        intercalada.addLast(elem1);
                        esta=true;
                    }
                    else
                        esta=true;
                }
            }
        }
        return intercalada;
    }
    private boolean pertenece(E elem, PositionList<E> L){
        for(E elemento : L)
            if(elem.equals(elemento))
                return true;
        return false;
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
    //o
    public Stack<Character> eliminarDePila(Stack<Character> p , Character c){
        Stack<Character> aux = new PilaConArreglo<Character>();
        Stack<Character> aDevolver = new PilaConArreglo<Character>();
        while(!p.isEmpty())
            aux.push(p.pop());
        while(!aux.isEmpty()){
            Character letra = aux.pop();
            if(!letra.equals(c))
                aDevolver.push(letra);
            p.push(letra);
        }
        return aDevolver;





    }
    
