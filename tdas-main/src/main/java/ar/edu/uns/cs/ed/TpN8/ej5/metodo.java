package ar.edu.uns.cs.ed.TpN8.ej5;

import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.TDAS_Implementados.ArregloCola;
import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class metodo<V,E> {

    public boolean existeCamino(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2){
        Map<Vertex<V>,Boolean> visitado = new MapeoConHashAbierto<Vertex<V>,Boolean>();
        Map<Vertex<V>,Vertex<V>> previo = new MapeoConHashAbierto<Vertex<V>,Vertex<V>>();
        for(Vertex<V> v : g.vertices()){
            visitado.put(v,false); 
            previo.put(v,null);
        }                                                                   
        if(BFS(g,v1,v2,visitado,previo)){
            PositionList<Vertex<V>> camino = recuperar(v2,previo);
            for(Vertex<V> v : camino)
                System.out.print(v.element() + " ");
            return true;
        }
        return false;
    }

    private boolean BFS(Graph<V,E> g, Vertex<V> s, Vertex<V> t, Map<Vertex<V>,Boolean> visitados, Map<Vertex<V>,Vertex<V>> previo){
        Queue<Vertex<V>> q = new ArregloCola<Vertex<V>>();
        q.enqueue(s);
        visitados.put(s,true);
        while(!q.isEmpty()){
            Vertex<V> x = q.dequeue();
            if(x.equals(t))
                return true;
            for(Edge<E> e : g.incidentEdges(x)){
                Vertex<V> v = g.opposite(x,e);
                if(!visitados.get(v)){
                    q.enqueue(v);
                    visitados.put(v,true);
                    previo.put(v,x);
                }
            }
        }
        return false;
    }

    private PositionList<Vertex<V>> recuperar(Vertex<V> t, Map<Vertex<V>,Vertex<V>> previo){
        PositionList<Vertex<V>> L = new ListaDoblementeEnlazada<Vertex<V>>();
        Vertex<V> x = t;
        while(x != null){
            L.addFirst(x);
            x = previo.get(x);
        }
        return L;
    }
}

