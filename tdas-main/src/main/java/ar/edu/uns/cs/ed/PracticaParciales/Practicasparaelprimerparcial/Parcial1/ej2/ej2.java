package ar.edu.uns.cs.ed.PracticaParciales.Practicasparaelprimerparcial.Parcial1.ej2;
import ar.edu.uns.cs.ed.tdas.tdapila.Stack;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;

public class ej2 {

    public void metodo(Stack<Character> p, Queue<Character> q){
        Stack<Character> aux = new pilaConArreglo<Character>();     //Stack es el TDA, pilaConArreglo implementa el TDA.
        while (!p.isEmpty())
            aux.push(p.pop());
        while(!q.isEmpty())
            p.push(q.dequeue());
        while (!aux.isEmpty())
            p.push(aux.pop());
    }

    
}
