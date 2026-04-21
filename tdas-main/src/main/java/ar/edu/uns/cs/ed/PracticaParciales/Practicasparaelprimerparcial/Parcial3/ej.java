package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelprimerparcial.Parcial3;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdapila.Stack;
import java.util.EmptyStackException;
import java.util.Queue;

public class ej<E> {


    //Ej1
    public void agregarElementos (PilaConArreglo<E> p){
        if(this.p.length==tope || this.p.length-tope< p.size()){
            E[] pilaNueva = (E[])new Object[this.p.length*2];
            for(int i=0; i<tope;i++){
                pilaNueva[i]=this.p[i];
            }
            while (!p.isEmpty()){
                pilaNuva[tope]=(p.pop());
                tope++;
            }
            this.p=pilaNueva;
            }
        else{
            while(!p.isEmpty()){
                this.p[tope](p.pop());
                tope++;
            }
        }
    }

    public E pop(){
        if(isEmpty())
            throw new EmptyStackException("Pila Vacia");
        E elemento = p[tope-1];
        p[tope-1]=null;
        tope--;
        return elemento;
    }
    //Ej2
    public int maximoEnCola(Queue<Integer> q){
        if(q!=null && !q.isEmpty()){ 
            Queue<Integer> aux = new ColaConArreglo<Integer>();             //Asumo que ColaConArreglo implementa Queue
            Integer mayor=0;
                while (!q.isEmpty()){
                    Integer numero=q.dequeue();
                    aux.enqueue(numero);
                    if(numero>mayor)
                        mayor=numero;
            }
            while(!aux.isEmpty()){
                q.enqueue(aux.dequeue());
            }
        return mayor;
        }
    }

    //Ej3
    public PositionList<Character> soloVocales (PositionList<Character> pl, int n){
        if(pl==null)
            throw new NullPointerException("pl es una referencia nula");
        PositionList<Character> nueva = new ListaDoblementeEnlazada<Character>();               //Asumo que ListaDoblementeEnlazada implementa PositionList
        int cont =0;                                                                            //Cuento la cantidad de veces que aparece para luego hacer un return
        for(Character c : pl){
            if(cont==n)
                return nueva;
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
                nueva.addLast(c);
                cont++;
            }
        }
        return nueva;
    }
}


    


