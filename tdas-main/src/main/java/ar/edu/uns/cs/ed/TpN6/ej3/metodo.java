package ar.edu.uns.cs.ed.TpN6.ej3;

import ar.edu.uns.cs.ed.TpN5.ej2.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.TpN6.ej1.TDAArbol;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import java.util.Iterator;

public class metodo {

    public Map<Character, Integer> cantidadRepeticiones(Tree<Character> t){
        Map<Character, Integer> mapeoADevolver = new MapeoConHashAbierto<Character, Integer>();
        Iterator<Character> it = t.iterator();
        while(it.hasNext()){
            Character c = it.next(); 
            if(mapeoADevolver.get(c)!=null){
                mapeoADevolver.put(c,mapeoADevolver.get(c)+1);
            }
            else{
                mapeoADevolver.put(c,1);
            }
        }
        return mapeoADevolver;
    }

    
}
