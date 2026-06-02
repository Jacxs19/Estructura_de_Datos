package ar.edu.uns.cs.ed.TpN8.ej2.i;

import ar.edu.uns.cs.ed.tdas.tdagrafo.GraphD;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;

import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;


import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;

@SuppressWarnings("unchecked")
public class GrafoDirigidoConListaDeAdyacencia<V,E> implements GraphD<V,E> {
    private PositionList<Vertice<V,E>> nodos;
    private PositionList<Arco<V,E>> arcos;

    public GrafoDirigidoConListaDeAdyacencia(){
        nodos= new ListaDoblementeEnlazada<Vertice<V,E>>();
        arcos= new ListaDoblementeEnlazada<Arco<V,E>>();
    }

    public Iterable<Vertex<V>> vertices(){
        PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada<Vertex<V>>();
        for(Vertex<V> v : nodos)
            lista.addLast(v);
        return lista;
    }
    public Iterable<Edge<E>> edges(){
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        for(Edge<E> a : arcos)
            lista.addLast(a);
        return lista;
    }

    public Iterable<Edge<E>> incidentEdges(Vertex<V> v){
        Vertice<V,E> vertice = checkVertex(v);
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        for(Edge<E> incidente : vertice.getIncidentes())
            lista.addLast(incidente);
        return lista;
    }
    public Iterable<Edge<E>> succesorEdges(Vertex<V> v){
        Vertice<V,E> vertice = checkVertex(v);
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        for(Edge<E> salientes : vertice.getEmergentes())
            lista.addLast(salientes);
        return lista;
    }
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e){
        Vertice<V,E> vertice = checkVertex(v);
        Arco<V,E> arco = checkEdge(e);
        if(arco.getV1()==vertice) return arco.getV2();
        else if(arco.getV2()==vertice) return arco.getV1();
        else throw new InvalidEdgeException("Vertice y arco no relacionados");
    }
    public Vertex<V> [] endvertices(Edge<E> e){         
        Arco<V,E> arco = checkEdge(e);
        Vertice<V,E> [] a = new Vertice[2];         //Preguntar
        a[0]= arco.getV1();
        a[1]= arco.getV2();
        return a;
    }
    public boolean areAdjacent(Vertex<V> v, Vertex<V> w){           //Preguntar si tenog que ver si hay un arco de w a v
        Vertice<V,E> v1 = checkVertex(v);
        Vertice<V,E> v2 = checkVertex(w);
        for(Arco<V,E> a : v1.getEmergentes())
            if(a.getV2()== v2)
                return true;
     //   for(Arco<V,E> a : v2.getEmergentes())           //No estoy seguro
     //       if(a.getV2()== v1)
     //           return true;
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
        vertice.setPosicionEnListaVertices(nodos.last());
        return vertice;
    }
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e){
        Vertice<V,E> v1 = checkVertex(v);
        Vertice<V,E> v2 = checkVertex(w);
        Arco<V,E> arco = new Arco<V,E>(e,v1,v2);
        v1.getEmergentes().addLast(arco);
        v2.getIncidentes().addLast(arco);
        arco.setPosicionEnEmergentes(v1.getEmergentes().last());
        arco.setPosicionEnIncidentes(v2.getIncidentes().last());
        arcos.addLast(arco);
        arco.setPosicionEnArcos(arcos.last());
        return arco;
    }
    public V removeVertex(Vertex<V> v){
        Vertice<V,E> vertice = checkVertex(v);
        PositionList<Arco<V,E>> listaSalientes = new ListaDoblementeEnlazada<Arco<V,E>>();
        for(Arco<V,E> salientes : vertice.getEmergentes())
            listaSalientes.addLast(salientes);
        PositionList<Arco<V,E>> listaEntrantes = new ListaDoblementeEnlazada<Arco<V,E>>();
        for(Arco<V,E> entrantes : vertice.getIncidentes())
            listaEntrantes.addLast(entrantes);
        for(Arco<V,E> e : listaSalientes){                  //Elimino arco en salientes
            removeEdge(e);
        }
        for(Arco<V,E> e : listaEntrantes){
            removeEdge(e);
        }
        V rotulo = vertice.element();
        nodos.remove(vertice.getPosicionEnListaVertices());
        vertice.setRotulo(null);
        vertice.setPosicionEnListaVertices(null);
        return rotulo;
    }
    public E removeEdge(Edge<E> e){         //Asumo que V1 del arco e es el saliente
        Arco<V,E> arco = checkEdge(e);
        E rotulo = arco.element();
        arco.getV1().getEmergentes().remove(arco.getPosicionEnEmergentes());
        arco.getV2().getIncidentes().remove(arco.getPosicionEnIncidentes());
        arcos.remove(arco.getPosicionEnArcos());
        arco.setPosicionEnArcos(null);
        arco.setPosicionEnEmergentes(null);
        arco.setPosicionEnIncidentes(null);
        arco.setRotulo(null);
        arco.setV1(null);
        arco.setV2(null);
        return rotulo;
    }




    private Vertice<V,E> checkVertex(Vertex<V> v){
        try{
            if(v==null)
                throw new InvalidVertexException("v invalido");
            if(v.element()==null)
                throw new InvalidVertexException("v eliminado anteriormente");
            return (Vertice<V,E>) v;
        }catch(ClassCastException e){
            throw new InvalidVertexException("v no es un vertice de grafo");
        }
    }

    private Arco<V,E> checkEdge(Edge<E> e){
        try{
            if(e==null)
                throw new InvalidEdgeException("e invalido");
            if(e.element()==null)
                throw new InvalidEdgeException("e eliminado anteriormente");
            return (Arco<V,E>) e;
        }catch(ClassCastException c){
            throw new InvalidEdgeException("e no es un arco de grafo");
        }
    }


    private class Vertice<V,E> implements Vertex<V>{
        private V rotulo;
        private Position<Vertice<V,E>> posicionEnListaVertices;
        private PositionList<Arco<V,E>> emergentes, incidentes;

        public Vertice(V rotulo){
            this.rotulo=rotulo;
            emergentes= new ListaDoblementeEnlazada<Arco<V,E>>();
            incidentes= new ListaDoblementeEnlazada<Arco<V,E>>();
        }
        //Setters
        public void setRotulo(V rot){
            rotulo=rot;
        }
        public void setPosicionEnListaVertices(Position<Vertice<V,E>> p){
            posicionEnListaVertices=p;
        }
        //Getters
        public V element(){
            return rotulo;
        }
        public Position<Vertice<V,E>> getPosicionEnListaVertices(){
            return posicionEnListaVertices;
        }
        public PositionList<Arco<V,E>> getEmergentes(){
            return emergentes;
        }
        public PositionList<Arco<V,E>> getIncidentes(){
            return incidentes;
        }
    }

    private class Arco<V,E> implements Edge<E>{
        private E rotulo;
        private Vertice<V,E> v1,v2;
        private Position<Arco<V,E>> posicionEnEmergentes, posicionEnIncidentes;
        private Position<Arco<V,E>> posicionEnArcos;

        public Arco(E rotulo, Vertice<V,E> v1, Vertice<V,E> v2){
            this.rotulo=rotulo;
            this.v1=v1;
            this.v2=v2;
        }

        //Setters
        public void setRotulo(E rot){
            rotulo=rot;
        }
        public void setV1(Vertice<V,E> v){
            v1=v;
        }
        public void setV2(Vertice<V,E> v){
            v2=v;
        }
        public void setPosicionEnEmergentes(Position<Arco<V,E>> p){
            posicionEnEmergentes=p;
        }
        public void setPosicionEnIncidentes(Position<Arco<V,E>> p){
            posicionEnIncidentes=p;
        }
        public void setPosicionEnArcos(Position<Arco<V,E>> p){
            posicionEnArcos=p;
        }
        //Getters
        public E element(){
            return rotulo;
        }
        public Vertice<V,E> getV1(){
            return v1;
        }
        public Vertice<V,E> getV2(){
            return v2;
        }
        public Position<Arco<V,E>> getPosicionEnEmergentes(){
            return posicionEnEmergentes;
        }
        public Position<Arco<V,E>> getPosicionEnIncidentes(){
            return posicionEnIncidentes;
        }
        public Position<Arco<V,E>> getPosicionEnArcos(){
            return posicionEnArcos;
        }
    }
}