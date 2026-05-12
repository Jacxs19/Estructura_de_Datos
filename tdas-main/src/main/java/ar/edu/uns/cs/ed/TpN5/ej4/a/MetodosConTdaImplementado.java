package ar.edu.uns.cs.ed.TpN5.ej4.a;
import java.util.Iterator;

import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TpN5.ej1.Pair;
import ar.edu.uns.cs.ed.TpN5.ej2.*;
import ar.edu.uns.cs.ed.TpN5.ej3.*;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TpN5.ej3.DiccionarioConHashAbierto;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Dictionary;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;

import ar.edu.uns.cs.ed.tdas.Entry;

public class MetodosConTdaImplementado<K,V>{

    //a)
    public PositionList<Entry<Integer,Integer>> elementosCoincidentes (MapeoConHashAbierto<Integer,Integer> M1, MapeoConHashAbierto<Integer,Integer> M2){
        PositionList<Entry<Integer,Integer>> aDevolver = new ListaDoblementeEnlazada<Entry<Integer,Integer>>();
        boolean esta=false;
        for(Entry<Integer,Integer> llaves : M1.entries()){
            Iterator<Entry<Integer,Integer>> it = M2.entries().iterator();
            esta=false;
            while(it.hasNext()&&!esta){
                Entry<Integer,Integer> key = it.next();
                if(key.getKey().equals(llaves.getKey()))
                    if(!key.getValue().equals(llaves.getValue())){
                        aDevolver.addLast(key);
                        aDevolver.addLast(llaves);
                        esta=true;
                    }
            }
        }
        return aDevolver;
    }

    //b)
    public boolean clavesContenidas (MapeoConHashAbierto<K,V> M1, MapeoConHashAbierto<K,V> M2){
        boolean esta=false;
        for(K clave1 : M1.keys()){
            esta=false;
            Iterator<K> it = M2.keys().iterator();
            while(it.hasNext()&& !esta)
                if(clave1.equals(it.next()))
                    esta=true;
            if(!esta)
                return false;
                    
        }
        return true;
    }

    //c)

    public Dictionary<K,V> acomodar (Dictionary<K,V> d){
        Map<K,V> mapeo = new MapeoConHashAbierto<K,V>();
        Dictionary<K,V> acomodado = new DiccionarioConHashAbierto<K,V>();
        for(Entry<K,V> llaves : d.entries()){
            mapeo.put(llaves.getKey(), llaves.getValue());          //Si hay 2 llaves iguales, reemplaza la vieja y pone la nueva (func. del mapeo);
        }
        for(Entry<K,V> entradasRescatadas : mapeo.entries()){
            acomodado.insert(entradasRescatadas.getKey(), entradasRescatadas.getValue());
        }
        return acomodado;
    } 

    //d)
    public Map<Character,Integer> aparicionesCaracter(PositionList<Character> lista){
        Map<Character, Integer> aDevolver = new MapeoConHashAbierto<Character, Integer>();
        for (Character c : lista){
            if(aDevolver.get(c)==null){
                aDevolver.put(c,1);
            }
            else
                aDevolver.put(c,aDevolver.get(c)+1);
        }
        return aDevolver;
    }
}