package ar.edu.uns.cs.ed.TpN8.ej4;

import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.TDAS_Implementados.ArregloCola;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;

public class metodo<V,E> {

    public int longitudCamino(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2){
        Map<Vertex<V>,Integer> distancia = new MapeoConHashAbierto<Vertex<V>,Integer>();
        for(Vertex<V> v : g.vertices())
            distancia.put(v,-1);                                                                    //Uso el -1 como que no fue visitado
        return BFS(g,v1,v2,distancia);
    }

    private int BFS(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2, Map<Vertex<V>,Integer> dis){
        Queue<Vertex<V>> q = new ArregloCola<Vertex<V>>();
        q.enqueue(v1);
        dis.put(v1,0);                                                                        //La distancia representa la cantidad de arcos hasta llegar a V2
        while(!q.isEmpty()){
            Vertex<V> vertice = q.dequeue();
            if(vertice==v2)
                return dis.get(vertice);
            for(Edge<E> e : g.incidentEdges(vertice)){                                              //Miro en los arcos adyacentes y guardo los vertices en una cola.
                Vertex<V> opuesto = g.opposite(vertice, e);                                         //Asegurandome de realizar el recorrido por niveles
                if(dis.get(opuesto)==-1){
                    dis.put(opuesto, dis.get(vertice)+1);
                    q.enqueue(opuesto);
                }
            }
        }
        return -1;                                                                                  //En caso de que no encuentre
    }
    


    
}
