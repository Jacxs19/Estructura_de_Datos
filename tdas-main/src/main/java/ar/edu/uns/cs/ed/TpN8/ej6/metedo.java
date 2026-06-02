package ar.edu.uns.cs.ed.TpN8.ej6;

import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;

public class metedo<V,E> {

    public void caminoMinimo(Graph<V,E> G, Vertex<V> v1, Vertex<V> v2){
        Map<Vertex<V>,Vertex<V>> previo = new MapeoConHashAbierto<Vertex<V>,Vertex<V>>();
        Map<Vertex<V>,Boolean> visitados = new MapeoConHashAbierto<Vertex<V>,Boolean>();
        for(Vertex<V> vertice : G.vertices()){
            previo.put(vertice,null);
            visitados.put(vertice,false);
        }
        int distMin=Integer.MAX_VALUE;
        for(Edge<E> ver : G.incidentEdges(v1)){
            int i=BFS(G,v1,v2,previo,visitados);
            if(visitados.get(v2)==true && i<distMin){
                distMin=i;
            }
            for(Vertex<V> vertices : G.vertices()){
                previo.put(vertices,null);
                visitados.put(vertices,false);
            }
        
        }
        

    }
    
}
