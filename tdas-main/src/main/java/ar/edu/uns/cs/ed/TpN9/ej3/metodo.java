package ar.edu.uns.cs.ed.TpN9.ej3;

import ar.edu.uns.cs.ed.tdas.tdacolaconprioridad.PriorityQueue;
import ar.edu.uns.cs.ed.TpN9.ej1.ColaConPrioridad;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Dictionary;
import ar.edu.uns.cs.ed.tdas.tdacolaconprioridad.Comparador;
import ar.edu.uns.cs.ed.tdas.Entry;

import java.util.Comparator;


public class metodo {

    public int[] valOrdenados(Dictionary<Character,Integer> d){
        Comparator<Integer> comp = new Comparador<Integer>();                        
        int[] a = new int[d.size()];
        PriorityQueue <Integer,Integer> c = new ColaConPrioridad<Integer,Integer>(comp);  //Declaro a c como una cola de prioridad de enteros en enteros
        for(Entry<Character,Integer> k : d.entries()){                                      //Para poder ordenarlos de menor a mayor
            c.insert(k.getValue(), k.getValue());
        }
        int i=0;
        while(!c.isEmpty()){                                                            
            a[i]=c.removeMin().getValue();
            i++;
        }
        return a;
    }
}