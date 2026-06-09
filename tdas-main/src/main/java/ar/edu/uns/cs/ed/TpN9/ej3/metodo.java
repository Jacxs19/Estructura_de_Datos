package ar.edu.uns.cs.ed.TpN9.ej3;

import ar.edu.uns.cs.ed.tdas.tdacolaconprioridad.PriorityQueue;
import ar.edu.uns.cs.ed.TpN9.ej1.ColaConPrioridad;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Dictionary;
import ar.edu.uns.cs.ed.tdas.tdacolaconprioridad.Comparador;
import ar.edu.uns.cs.ed.tdas.Entry;

import java.util.Comparator;


public class metodo {

    public int[] valOrdenados(Dictionary<Character,Integer> d){
        Comparator<Character> comp = new DefaultComparator<Character>();
        int[] a = new int[d.size()];
        PriorityQueue <Character,Integer> c = new ColaConPrioridad<Character,Integer>(comp);
        for(Entry<Character,Integer> k : d.entries()){
            c.insert(k.getKey(), k.getValue());
        }
        int i=0;
        while(!c.isEmpty()){
            a[i]=c.removeMin().getValue();
            i++;
        }
        return a;
    }
}