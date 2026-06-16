package ar.edu.uns.cs.ed.PracticaParciales.Promocion.primermetodo;



import ar.edu.uns.cs.ed.TDAS_Implementados.ArregloCola;
import ar.edu.uns.cs.ed.tdas.tdacola.Queue;
import ar.edu.uns.cs.ed.TDAS_Implementados.MapeoConHashAbierto;
import ar.edu.uns.cs.ed.TpN4.Ej1.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map;
import ar.edu.uns.cs.ed.tdas.excepciones.GraphException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdagrafo.GraphD;

public class metodoGrafo<V,E> {

    public PositionList<PositionList<Vertex<V>>> RESOLVER(GraphD<V,E>G, V R1, V R2){
        if(G!=null){
            PositionList<PositionList<Vertex<V>>> soluciones = new ListaDoblementeEnlazada<PositionList<Vertex<V>>>();
            PositionList<Vertex<V>> caminoActual = new ListaDoblementeEnlazada<Vertex<V>>();
            Map<Vertex<V>,Boolean> visitados = new MapeoConHashAbierto<Vertex<V>,Boolean>();
            Vertex<V> V1 = buscar(G, R1);
            Vertex<V> V2 = buscar(G, R2);
            if(V1!= null && V2 !=null){
                dfs(G,V1,V2,visitados,caminoActual,soluciones);
                return soluciones;
            }
            throw new InvalidVertexException("Vertice nulo");
        }
        throw new GraphException("grafo vacio");
    }

    private void dfs(GraphD<V,E> g,Vertex<V> actual,Vertex<V> destino,Map<Vertex<V>,Boolean> visitados,PositionList<Vertex<V>> camino, PositionList<PositionList<Vertex<V>>> soluciones){
        visitados.put(actual,true);
        camino.addLast(actual);
        if(actual.equals(destino)){
            PositionList<Vertex<V>> copia = new ListaDoblementeEnlazada<Vertex<V>>();
            for(Vertex<V> v : camino)
                copia.addLast(v);
            soluciones.addLast(copia);
        }
        else{
            for(Edge<E> e : g.succesorEdges(actual)){ // dirigido
                Vertex<V> sig = g.opposite(actual,e);
                if(visitados.get(sig)==null || visitados.get(sig)==false)
                    dfs(g,sig,destino,visitados,camino,soluciones);
            }
        }
        camino.remove(camino.last());
        visitados.put(actual,false);
    }

    private Vertex<V> buscar(GraphD<V,E> G, V R1){
        for(Vertex<V> v : G.vertices()){
            if(v.element().equals(R1))
                return v;
        }
        return null;                            //No lo encontre
    }






    public boolean esConexo(Graph<V,E> g){
        Map<Vertex<V>, Boolean> visitados = new MapeoConHashAbierto<Vertex<V>,Boolean>();
        for(Vertex<V> v : g.vertices())
            visitados.put(v,false);
        if(visitados.isEmpty())
            return true;                            //Un grafo vacio es conexo
        Vertex<V> inicio = g.vertices().iterator().next();
        DFSConexo(g,visitados,inicio);                      //Arranco desde un vertice cualquiera y chequeo que desde ahi todos los vertices del grafo queden vistos
        for(Vertex<V> v : g.vertices()){
            if(!visitados.get(v))
                return false;
        }
        return true;
    }

    private void DFSConexo(Graph<V,E> g, Map<Vertex<V>,Boolean> m, Vertex<V> v){
        m.put(v,true);
        for(Edge<E> arcos :g.incidentEdges(v)){
            Vertex<V> opuesto = g.opposite(v, arcos);
            if(!m.get(opuesto))
                DFSConexo(g,m,opuesto);
        }
    }



    public int longitudMasCorta(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2){
        Map<Vertex<V>,Boolean> visitados = new MapeoConHashAbierto<Vertex<V>,Boolean>();
        Map<Vertex<V>,Vertex<V>> previos = new MapeoConHashAbierto<Vertex<V>,Vertex<V>>();
        for(Vertex<V> v : g.vertices()){
            visitados.put(v,false);
        }
        
        BFSCaminoCorto(g,v1,v2,visitados, previos);
        PositionList<Vertex<V>> camino = recuperarDistancia(v2,previos);
        return camino.size()-1;
    }

    private void BFSCaminoCorto(Graph<V,E> g, Vertex<V> origen , Vertex<V> fin , Map<Vertex<V>,Boolean> visitados, Map<Vertex<V>, Vertex<V>> previo){
        Queue<Vertex<V>> cola = new ArregloCola<Vertex<V>>();
        cola.enqueue(origen);
        visitados.put(origen,true);
        while(!cola.isEmpty()){
            Vertex<V> analizar = cola.dequeue();
            if(analizar.equals(fin))
                return;
            for(Edge<E> arcos : g.incidentEdges(analizar)){
                Vertex<V> opuesto = g.opposite(analizar, arcos);
                if(!visitados.get(opuesto)){
                    cola.enqueue(opuesto);                          //Encolo los vertices adyacentes para recorrerlos luego de visitar los nodos "hermanos de "analizar"".
                    visitados.put(opuesto,true);
                    previo.put(opuesto,analizar);
                }
            }
        }
    }

    private PositionList<Vertex<V>> recuperarDistancia(Vertex<V> fin,Map<Vertex<V>,Vertex<V>> previos){
        Vertex<V> x = fin;
        PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada<Vertex<V>>();
        while(x!=null){
            lista.addFirst(x);
            x=previos.get(x);
        }
        return lista;
    }
    //u otra forma:
    public int longitudMasCorta2(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2){
        Map<Vertex<V>,Boolean> visitados= new MapeoConHashAbierto<Vertex<V>,Boolean>();
        Map<Vertex<V>,Integer> caminoMinimo = new MapeoConHashAbierto<Vertex<V>,Integer>();
        for(Vertex<V> v : g.vertices()){
            visitados.put(v,false);
        }
        BFSCaminoCorto2(g,v1,v2,visitados,caminoMinimo);
        return caminoMinimo.get(v2);
    }
    //T_longitudMasCorta2(n)= O(n+m), donde n es la cantidad de vertices del grafo y m la cantidad de arcos

    private void BFSCaminoCorto2(Graph<V,E> g, Vertex<V> origen, Vertex<V> fin,Map<Vertex<V>,Boolean> visitados, Map<Vertex<V>,Integer> distancia){
        Queue<Vertex<V>> cola = new ArregloCola<Vertex<V>>();
        cola.enqueue(origen);
        visitados.put(origen,true);
        distancia.put(origen,0);
        while(!cola.isEmpty()){
            Vertex<V> analizar = cola.dequeue();
            if(analizar.equals(fin))
                return;
            for(Edge<E> arcos : g.incidentEdges(analizar)){
                Vertex<V> opuesto = g.opposite(analizar,arcos);
                if(!visitados.get(opuesto)){
                    visitados.put(opuesto,true);
                    cola.enqueue(opuesto);
                    distancia.put(opuesto,distancia.get(analizar)+1);
                }
            }
        }
    }

    public boolean existeCamino(Graph<V,E> g, Vertex<V> v1, Vertex<V> v2){
        Map<Vertex<V>,Boolean> visitados = new MapeoConHashAbierto<Vertex<V>,Boolean>();
        Map<Vertex<V>,Vertex<V>> camino = new MapeoConHashAbierto<Vertex<V>,Vertex<V>>();
        for(Vertex<V> v : g.vertices()){
            visitados.put(v,false);
        }
        boolean existe = BFSExisteCamino(g,v1,v2,visitados,camino);
        if(existe){
            PositionList<Vertex<V>> caminoRecuperado = recupero(v2,camino);
            for(Vertex<V> v : caminoRecuperado)
                System.out.println(v.element()+" ");
            return true;
        }
        return false;
    }

    //T_existeCamino(n) = O(n+m), donde n es la cantidad de vertices grafo y m es la cantidad de arcos del grafo, en el peor de los casos recorro todo el grafo

    private boolean BFSExisteCamino(Graph<V,E> g,Vertex<V> origen,Vertex<V> fin,Map<Vertex<V>,Boolean> visitados,Map<Vertex<V>,Vertex<V>> camino){
        Queue<Vertex<V>> cola = new ArregloCola<Vertex<V>>();
        cola.enqueue(origen);
        visitados.put(origen,true);
        camino.put(origen,null);
        while(!cola.isEmpty()){
            Vertex<V> analizar = cola.dequeue();
            if(analizar.equals(fin))
                return true;
            for(Edge<E> arcos : g.incidentEdges(analizar)){
                Vertex<V> opuesto = g.opposite(analizar,arcos);
                if(!visitados.get(opuesto)){
                    cola.enqueue(opuesto);
                    visitados.put(opuesto,true);
                    camino.put(opuesto,analizar);
                }
            }
        }
        return false;
    }
    private PositionList<Vertex<V>> recupero(Vertex<V> v, Map<Vertex<V>,Vertex<V>> m){
        Vertex<V> x = v;
        PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada<Vertex<V>>();
        while(x!=null){
            lista.addFirst(x);
            x=m.get(x);
        }
        return lista;
    }
    
}
