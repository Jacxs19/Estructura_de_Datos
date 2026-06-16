package ar.edu.uns.cs.ed.TpN8.ej7;

import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TDAS_Implementados.ArregloCola;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;


public class metodo {

    public <V,E> PositionList<Vertex<V>> verticesMenoresAK(Graph<V,E> g, Vertex<V> origen, int k){
        Map<Vertex<V>,Boolean> visitados=new MapeoConHashAbierto<Vertex<V>,Boolean>();
        for(Vertex<V> v : g.vertices())
            visitados.put(v,false);//pongo todos en falso
        PositionList<Vertex<V>> resultado=new ListaDoblementeEnlazada<Vertex<V>>();
        Queue<Vertex<V>> cola=new ArregloCola<Vertex<V>>();
        visitados.put(origen,true);//marco origen como visitado
        cola.enqueue(origen);//lo agrego a la cola pq es el primer nivel
        int numero=1;//es el numero de visita
        while(!cola.isEmpty()){
            Vertex<V> actual = cola.dequeue();//saco el primer vertice que hay en la cola
            if(numero < k)//tiene que ser menor a k, como dice el enunciado
                resultado.addLast(actual);
            else
                break;//de lo contrario termina el recorrido (debe ser eficiente el programa)
            numero++;//paso al siguiente vertice de visita
            for(Edge<E> e : g.incidentEdges(actual)){//recorro vecinos si tiene
                Vertex<V> vecino=g.opposite(actual,e);//obtengo vecino
                if(!visitados.get(vecino)){//si vecino no fue visitado
                    visitados.put(vecino,true);//lo marco como visitado
                    cola.enqueue(vecino);//y lo meto en la cola
                }
            }
        }
        return resultado;
    }
    
}
