package ar.edu.uns.cs.ed.TpN8.ej1.i;

import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;

import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;

@SuppressWarnings ("unchecked")

public class GrafoNoDirigidoConListaDeAdyacencia<V,E> implements Graph<V,E>{
    protected PositionList<Vertice<V,E>> nodos;
    protected PositionList<Arco<V,E>> arcos;

    public GrafoNoDirigidoConListaDeAdyacencia(){
        nodos=new ListaDoblementeEnlazada<Vertice<V,E>>();
        arcos=new ListaDoblementeEnlazada<Arco<V,E>>();
    }
    public Iterable<Vertex<V>> vertices(){
        PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada<Vertex<V>>();
        for(Vertex<V> v : nodos)
            lista.addLast(v);
        return lista;
    }
    public Iterable<Edge<E>> edges(){
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        for(Edge<E> e : arcos)
            lista.addLast(e);
        return lista;
    }
    public Iterable<Edge<E>> incidentEdges(Vertex<V> v){
        Vertice<V,E> vertice = checkVertex(v);
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        for(Edge<E> e : vertice.getAdyacentes())
            lista.addLast(e);
        return lista;
    }
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e){
        Vertice<V,E> vertice = checkVertex(v);
        Arco<V,E> arco = checkEdge(e);
        if(arco.getV1()==vertice)
            return arco.getV2();
        else if(arco.getV2()==vertice)
            return arco.getV1();
        throw new InvalidEdgeException("El arco no incide en el vertice");   
    }
    public Vertex<V> [] endvertices(Edge<E> e){
        Arco<V,E> arco = checkEdge(e);
        Vertice<V,E>[] a = new Vertice[2];
        a[0]= arco.getV1();
        a[1]= arco.getV2();
        return a;
    }
    public boolean areAdjacent(Vertex<V> v , Vertex<V> w){
        Vertice<V,E> vertice1 = checkVertex(v);
        Vertice<V,E> vertice2 = checkVertex(w);
        for(Edge<E> e : vertice1.getAdyacentes()){
            Arco<V,E> arco = (Arco<V,E>) e;
            if(arco.getV1()==vertice2 || arco.getV2()==vertice2)
                return true;
        }
        return false;
    }
    public V replace(Vertex<V> v, V x){
        Vertice<V,E> vertice = checkVertex(v);
        V elemento = vertice.element();
        vertice.setRotulo(x);
        return elemento;
    }
    public E replace(Edge<E> e, E x){
        Arco<V,E> arco = checkEdge(e);
        E elemento = arco.element();
        arco.setRotulo(x);
        return elemento;
    }
    public Vertex<V> insertVertex(V x){
        Vertice<V,E> vertice = new Vertice<V,E>(x);
        nodos.addLast(vertice);
        vertice.setPosicionEnNodos(nodos.last());
        return vertice;
    }
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e){
        Vertice<V,E> vertice1= checkVertex(v);
        Vertice<V,E> vertice2= checkVertex(w);
        Arco<V,E> arco = new Arco<V,E>(e,vertice1,vertice2);
        vertice1.getAdyacentes().addLast(arco);
        arco.setPosicionEnIv1(vertice1.getAdyacentes().last());
        vertice2.getAdyacentes().addLast(arco);
        arco.setPosicionEnIv2(vertice2.getAdyacentes().last());
        arcos.addLast(arco);
        arco.setPosicionEnArcos(arcos.last());
        return arco;
    }
    public V removeVertex(Vertex<V> v){
        Vertice<V,E> vertice = checkVertex(v);
        PositionList<Position<Arco<V,E>>> listaABorrar = new ListaDoblementeEnlazada<Position<Arco<V,E>>>();
        for(Position<Arco<V,E>> e : vertice.getAdyacentes().positions()){
            listaABorrar.addLast(e);
        }
        for(Position<Arco<V,E>> p : listaABorrar){
            Arco<V,E> A = p.element();
            A.getV1().getAdyacentes().remove(A.getPosicionEnIv1());
            A.getV2().getAdyacentes().remove(A.getPosicionEnIv2());
            arcos.remove(A.getPosicionEnArcos());
            A.setPosicionEnArcos(null);
            A.setPosicionEnIv1(null);
            A.setPosicionEnIv2(null);
            A.setRotulo(null);
        }
        V elemento = vertice.element();
        nodos.remove(vertice.getPosicion());
        vertice.setRotulo(null);
        vertice.setPosicionEnNodos(null);
        return elemento;
    }
    public E removeEdge(Edge<E> e){
        Arco<V,E> arco = checkEdge(e);
        arco.getV1().getAdyacentes().remove(arco.getPosicionEnIv1());
        arco.getV2().getAdyacentes().remove(arco.getPosicionEnIv2());
        arcos.remove(arco.getPosicionEnArcos());
        E elemento = arco.element();
        arco.setPosicionEnArcos(null);
        arco.setPosicionEnIv1(null);
        arco.setPosicionEnIv2(null);
        arco.setV1(null);
        arco.setV2(null);
        arco.setRotulo(null);
        return elemento;
    }

    private Vertice<V,E> checkVertex(Vertex<V> v){
        try{
            if(v==null)
                throw new InvalidVertexException("v invalido");
            if(v.element()==null)
                throw new InvalidVertexException("v borrado anteriormente");
            return (Vertice<V,E>) v;
        }catch(ClassCastException c){
            throw new InvalidVertexException("v no es un vertice de grafo");
        }
    }

    private Arco<V,E> checkEdge(Edge<E> e){
        try{
            if(e==null)
                throw new InvalidEdgeException("e invalido");
            if(e.element()==null)
                throw new InvalidEdgeException("e borrado anteriormente");
            return (Arco<V,E>) e;
        }catch(ClassCastException c){
            throw new InvalidEdgeException("e no es un arco de grafo");
        }
    }
}