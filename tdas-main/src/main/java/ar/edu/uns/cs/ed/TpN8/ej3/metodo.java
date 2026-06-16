package ar.edu.uns.cs.ed.TpN8.ej3;

import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import java.util.Iterator;

public class metodo<V,E> {

    public boolean esConexo(Graph<V,E> g){
        Map<Vertex<V>,Boolean> visitados = new MapeoConHashAbierto<Vertex<V>,Boolean>();
        for(Vertex<V> v : g.vertices()){
            if(v!=null)
                visitados.put(v,false);
        }
        if(visitados.isEmpty())                                 //Grafo vacio, es conexo
            return true;
        Vertex<V> vertice = g.vertices().iterator().next();
        dfs(g, visitados, vertice);
        
        for(Vertex<V> v : g.vertices())                         //Verifico que todos los vertices esten visitados
            if(visitados.get(v)==false)
                return false;    
        return true;                       
    }

    private void dfs (Graph<V,E> g , Map<Vertex<V>,Boolean> visitados, Vertex<V> v){
        if(visitados.get(v)==true)                                                      //No vuelvo a verificar los vertices ya visitados
            return;
        visitados.put(v,true);
        for(Edge<E> e : g.incidentEdges(v)){                                            //Para cada arco incidente de (v) recorro el opuesto a ese arco en DFS    
            dfs(g,visitados,g.opposite(v,e));
        }
    }

    


    
}
