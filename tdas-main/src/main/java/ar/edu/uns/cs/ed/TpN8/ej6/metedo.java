package ar.edu.uns.cs.ed.TpN8.ej6;

import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TDAS_Implementados.ArregloCola;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;

public class metedo<V,E> {

    public void caminoMinimo(Graph<V,Double> g, Vertex<V> v1, Vertex<V> v2){
        Map<Vertex<V>,Vertex<V>> previo = new MapeoConHashAbierto<Vertex<V>,Vertex<V>>();
        Map<Vertex<V>,Boolean> visitados = new MapeoConHashAbierto<Vertex<V>,Boolean>();
        Map<Vertex<V>,Double> distancia = new MapeoConHashAbierto<Vertex<V>,Double>();
        for(Vertex<V> v : g.vertices()){
            distancia.put(v,Double.MAX_VALUE);
            previo.put(v,null);
            visitados.put(v,false);
        }
        distancia.put(v1,0.0);
        for(Vertex<V> v : g.vertices()){                            //Lo utilizo para iterar la cantidad de veces de nodos del arbol y poder hacer mas de un recorrido.
            Vertex<V> u = minimo(g,distancia,visitados);            //Recorrido Dijkstra
            if(u==null)
                break;
            visitados.put(u,true);
            for(Edge<Double> e : g.incidentEdges(u)){               //Voy viendo los arcos que salen de u
                Vertex<V> w = g.opposite(u, e);                     //Consigo el vertice opuesto
                if(!visitados.get(w)){                              //Si no lo visite antes 
                    Double nuevoCosto = distancia.get(u);           //Consigo la distancia nueva
                    nuevoCosto+= e.element();                       //Y se la sumo al arco actual.
                    if(nuevoCosto< distancia.get(w)){               //Si es menor el peso del arco
                        distancia.put(w,nuevoCosto);                //lo pongo en la lista y lo guardo
                        previo.put(w,u);                            //El mejor camino que conozco para llegar a w pasa por u
                    }
                }
            }   
        }
        PositionList<Vertex<V>> camino = recuperar(v2, previo);
        for(Vertex<V> v : camino){
            System.out.print(v.element() + " ");
        }
        System.out.println();
        System.out.println("Costo minimo: " + distancia.get(v2));
    }

    private Vertex<V> minimo(Graph<V,Double> g, Map<Vertex<V>,Double> distancia, Map<Vertex<V>,Boolean> visitados){
        Vertex<V> v = null;                                                         //No guardo ningun vertice
        double min = Double.MAX_VALUE;                                              //Seteo un valor minimo (uso el maximo de nros reales)
        for(Vertex<V> vertice :g.vertices()){                                       //Busco el vértice no procesado con distancia mínima
            if(!visitados.get(vertice) && distancia.get(vertice)<min){              //Si todavía no fue procesado y tiene una distancia menor que la mejor encontrada hasta ahora, lo tomo como candidato
                min = distancia.get(vertice);
                v=vertice;
            }
        }
        return v;                                                                   // Retorno el vértice más cercano al origen que aún no fue procesado
    }
    
    private PositionList<Vertex<V>> recuperar(Vertex<V> destino,Map<Vertex<V>,Vertex<V>> P){
        PositionList<Vertex<V>> L = new ListaDoblementeEnlazada<Vertex<V>>();
        Vertex<V> x = destino;
        while(x != null){
            L.addFirst(x);
            x = P.get(x);
        }
        return L;
    }   
    
    


    
}
