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
        if(v==null)
            throw new InvalidVertexException("Vertice invalido");
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        Vertice<V,E> vertice = (Vertice<V,E>) v;
        for(Edge<E> e : vertice.getAdyacentes())
            lista.addLast(e);
        return lista;
    }
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e){
        if(v==null)
            throw new InvalidVertexException("Vertice invalido");
        if(e==null)
            throw new InvalidEdgeException("Arco invalido");
        Arco<V,E> arco = (Arco<V,E>) e;
        if(arco.getV1()==v)
            return arco.getV2();
        else if(arco.getV2()==v)
            return arco.getV1();
        throw new InvalidEdgeException("El arco no incide en el vertice");   
    }
    public Vertex<V> [] endvertices(Edge<E> e){
        if(e==null)
            throw new InvalidEdgeException("Arco invalido");
        Arco<V,E> arco = (Arco<V,E>) e;
        Vertex<V>[] a = (Vertex<V>[]) new Vertex[2];
        a[0]= arco.getV1();
        a[1]= arco.getV2();
        return a;
    }
    public boolean areAdjacent(Vertex<V> v , Vertex<V> w){
        if(v==null || w==null)
            throw new InvalidVertexException("Vertice invalido");
        Vertice<V,E> vertice1 = (Vertice<V,E>) v;
        Vertice<V,E> vertice2 = (Vertice<V,E>) w;
        for(Edge<E> e : vertice1.getAdyacentes()){
            Arco<V,E> arco = (Arco<V,E>) e;
            if(arco.getV1()==vertice2 || arco.getV2()==vertice2)
                return true;
        }
        return false;
    }
    public V replace(Vertex<V> v, V x){
        if(v==null)
            throw new InvalidVertexException("Vertice invalido");
        Vertice<V,E> vertice = (Vertice<V,E>) v;
        V elemento = vertice.element();
        vertice.setRotulo(x);
        return elemento;
    }
    public E replace(Edge<E> e, E x){
        if(e==null)
            throw new InvalidEdgeException("Arco invalido");
        Arco<V,E> arco = (Arco<V,E>) e;
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
        if(v==null || w==null)
            throw new InvalidVertexException("Vertice invalido");
        Vertice<V,E> vertice1= (Vertice<V,E>) v;
        Vertice<V,E> vertice2= (Vertice<V,E>) w;
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
        if(v==null)
            throw new InvalidVertexException("Vertice invalido");
        Vertice<V,E> vertice = (Vertice<V,E>) v;
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
        if(e==null)
            throw new InvalidEdgeException("Arco invalido");
        Arco<V,E> arco = (Arco<V,E>) e;
        arco.getV1().getAdyacentes().remove(arco.getPosicionEnIv1());
        arco.getV2().getAdyacentes().remove(arco.getPosicionEnIv2());
        arcos.remove(arco.getPosicionEnArcos());
        E elemento = arco.element();
        arco.setPosicionEnArcos(null);
        arco.setPosicionEnIv1(null);
        arco.setPosicionEnIv2(null);
        arco.setRotulo(null);
        return elemento;
    }
}