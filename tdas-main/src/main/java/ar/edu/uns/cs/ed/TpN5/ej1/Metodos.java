package ar.edu.uns.cs.ed.TpN5.ej1;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.*;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TpN5.ej2.MapeoConLista;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Dictionary;

import java.security.Key;
import java.util.Iterator;


public class Metodos<K,V> {

    //inciso a)
    public PositionList<Pair<Integer,Integer>> elementosCoincidentes (Map<Integer,Integer> M1, Map<Integer,Integer> M2){
        PositionList<Pair<Integer,Integer>> L = new ListaDoblementeEnlazada<Pair<Integer,Integer>>();
        boolean encontro =false;
        for(Entry<Integer,Integer> E1 : M1.entries()){
            Iterator<Entry<Integer,Integer>> it2 = M2.entries().iterator();
            encontro=false;
            while(it2.hasNext()&& !encontro){
                Entry<Integer,Integer> aux= it2.next();
                if(E1.getKey().equals(aux.getKey()))
                    if(!E1.getValue().equals(aux.getValue())){
                        L.addLast(new Pair<Integer,Integer>(E1.getKey(), E1.getValue()));
                        L.addLast(new Pair<Integer,Integer>(aux.getKey(),aux.getValue()));
                        encontro=true;
                    }
            }
        }
        return L;
}

    //inciso b)
    public boolean contenidos (Map<K,V> M1 , Map<K,V> M2){
        boolean esta=false;
        for(Entry<K,V> E1 : M1.entries()){ 
            esta=false;
            Iterator<Entry<K,V>> it2 = M2.entries().iterator();
            while(it2.hasNext()&&!esta){
                Entry<K,V> aux = it2.next();
                if(E1.getKey().equals(aux.getKey()))
                    esta=true;                                  //Si esta corto el while y vuelvo al for-each y controlo la siguiente llave
            }
            if(!esta){                                          //Si al salir del while de M2 no encontre una llave igual, no esta contenido y retorno falso.
                return false;
            }
        }
        return true;
    }

    //Inciso c)
    public Dictionary<K,V> acomodar (Dictionary<K,V> d){
        Map<K,V> aux = new MapeoConLista<K,V>();
        for(Entry<K,V> E1 : d.entries()){
            aux.put(E1.getKey(),E1.getValue());                                 //Si ya existia esa llave, pisa el valor anterior por el entrante y devuelve el viejo.
        }
        Dictionary<K,V> diccionarioAcomodado = new DiccionarioConLista<K,V>();
        for(Entry<K,V> E2 : aux.entries()){
            diccionarioAcomodado.insert(E2.getKey(),E2.getValue());
        }
        return diccionarioAcomodado;
    }

    //Inciso d)
    public Map<Character,Integer> aparicionesCaracter(PositionList<Character> lista){
        Map<Character,Integer> mapeoApariciones = new MapeoConLista<Character,Integer>();
        for(Character c : lista){
            if(mapeoApariciones.get(c)!=null){                                              //Si devuelve el valor de aparicicones, coloco el mismo caracter y le aumento el tamaño.
                    mapeoApariciones.put(c,mapeoApariciones.get(c)+1);
            }
            else{
                mapeoApariciones.put(c,1);                                            //Si es la primera vez que aparece, lo guardo y le pongo 1 aparicion.
            }
        }
        return mapeoApariciones;
    }     
}
