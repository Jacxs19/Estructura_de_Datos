package ar.edu.uns.cs.ed.TpN8.ej2.ii;

import ar.edu.uns.cs.ed.tdas.tdagrafo.GraphD;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;

import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;

@SuppressWarnings("unchecked")
public class GrafoDirigidoConMatrizDeAdyacencia<V,E> implements GraphD<V,E> {
    protected PositionList<Edge<E>> arcos;
    protected PositionList<Vertex<V>> vertices;
    protected Edge<E> [][] matriz;
    protected int cantidadVertices;



    public GrafoDirigidoConMatrizDeAdyacencia(int n){
        arcos=new ListaDoblementeEnlazada<Edge<E>>();
        vertices=new ListaDoblementeEnlazada<Vertex<V>>();
        cantidadVertices=0;
        matriz= (Edge<E>[][]) new Arco[n][n];
        for(int i=0; i<n; i++ )
            for(int j=0; j<n; j++ )
                matriz[i][j] = null;
    }


    public Iterable<Vertex<V>> vertices(){
        PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada<Vertex<V>>();
        for(Vertex<V> v : vertices)
            lista.addLast(v);
        return lista;
    }
    public Iterable<Edge<E>> edges(){
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        for(Edge<E> e : arcos)
            lista.addLast(e);
        return lista;
    }
    public Iterable<Edge<E>> incidentEdges(Vertex<V> v){            //Recorro por columna
        Vertice<V> vertice = checkVertex(v);
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        int col=vertice.getIndice();
        for(int fila=0; fila<matriz[0].length;fila++)
            if(matriz[fila][col]!=null)
                lista.addLast(matriz[fila][col]);
        return lista;
    }
    public Iterable<Edge<E>> succesorEdges(Vertex<V> v){            //Recorro por fila
        Vertice<V> vertice= checkVertex(v);
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        int fila = vertice.getIndice();
        for(int col=0; col<matriz[0].length;col++)
            if(matriz[fila][col]!=null)
                lista.addLast(matriz[fila][col]);
        return lista;
    }
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e){
        Vertice<V> vertice = checkVertex(v);
        Arco<V,E> arco = checkEdge(e);
        if(arco.getCola()==vertice) return arco.getPunta();
        else if(arco.getPunta()==vertice) return arco.getCola();
        else throw new InvalidEdgeException("Vertice y Arco no relacionados");
    }
    public Vertex<V> [] endvertices(Edge<E> e){
        Arco<V,E> arco = checkEdge(e);
        Vertice<V> [] a = new Vertice[2];
        a[0]=arco.getCola();
        a[1]=arco.getPunta();
        return a;
    }
    public boolean areAdjacent(Vertex<V> v, Vertex<V> w){
        Vertice<V> v1 = checkVertex(v);
        Vertice<V> v2 = checkVertex(w);
        int i1 = v1.getIndice();
        int i2 = v2.getIndice();
        return matriz[i1][i2]!=null;
    }
    public V replace(Vertex<V> v, V x){
        Vertice<V> vertice = checkVertex(v);
        V elemento = vertice.element();
        vertice.setRotulo(x);
        return elemento;
    }
    public E replace(Edge<E> e, E x){
        Arco<V,E> arco = checkEdge(e);
        E element = arco.element();
        arco.setRotulo(x);
        return element;
    }
    public Vertex<V> insertVertex(V x){
        if(cantidadVertices >= matriz.length)
            aumentarMatriz();
        Vertice<V> vertice = new Vertice<V>(x,cantidadVertices++);
        vertices.addLast(vertice);
        vertice.setPosicionEnVertices(vertices.last());
        return vertice;
    }
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e){
        Vertice<V> v1 = checkVertex(v);
        Vertice<V> v2 = checkVertex(w);
        Arco<V,E> arco = new Arco<V,E>(e,v1,v2);
        arcos.addLast(arco);
        arco.setPosicionEnArcos(arcos.last());
        int fila = v1.getIndice();
        int col = v2.getIndice();
        matriz[fila][col] = arco;
        return arco;
    }
    public V removeVertex(Vertex<V> v){                 //Preguntar si debo mover la matriz
        Vertice<V> vertice = checkVertex(v);
        V rotulo = vertice.element();
        for(Edge<E> arc : incidentEdges(v))
            removeEdge(arc);
        for(Edge<E> arc : succesorEdges(v))
            removeEdge(arc);
        vertices.remove(vertice.getPosicionEnVertices());
        vertice.setPosicionEnVertices(null);
        vertice.setRotulo(null);
        return rotulo;
    }
    public E removeEdge(Edge<E> e){
        Arco<V,E> arco = checkEdge(e);
        E elemento = arco.element();
        int fila= arco.getCola().getIndice();
        int col = arco.getPunta().getIndice();
        matriz[fila][col]=null;
        arcos.remove(arco.getPosicionEnArcos());
        arco.setCola(null);
        arco.setPunta(null);
        arco.setPosicionEnArcos(null);
        arco.setRotulo(null);
        return elemento;
    }









    private void aumentarMatriz(){
        Edge<E>[][] nuevaMatriz = (Edge<E>[][]) new Arco[matriz.length*2][matriz.length*2];
        for(int f=0; f<nuevaMatriz.length; f++){
            for(int c=0; c<nuevaMatriz.length; c++)
                nuevaMatriz[f][c]=null;
        }
        for(int f=0; f<matriz.length;f++){                                                  //Uso matriz.length para filas y columnas ya que la matriz es de NxN
            for(int c=0; c<matriz.length;c++){
                nuevaMatriz[f][c]=matriz[f][c];
            }
        }
        matriz=nuevaMatriz;
    }

    private Vertice<V> checkVertex(Vertex<V> v){
        try{
            if(v==null)
                throw new InvalidVertexException("v invalido");
            if(v.element()==null)
                throw new InvalidVertexException("v eliminado anteriormente");
            return (Vertice<V>) v;
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
        }catch(ClassCastException d){
            throw new InvalidEdgeException("e no es un arco de grafo");
        }
    }



    private class Vertice<V> implements Vertex<V> {
        private Position<Vertex<V>> posicionEnVertices;
        private V rotulo;
        private int indice;

        public Vertice(V rotulo , int indice){
            this.rotulo=rotulo;
            this.indice=indice;
        }
        //Setters
        public void setPosicionEnVertices(Position<Vertex<V>> p){
            posicionEnVertices=p;
        }
        public void setRotulo(V r){
            rotulo=r;
        }
        //Getters
        public V element(){
            return rotulo;
        }
        public int getIndice(){
            return indice;
        }
        public Position<Vertex<V>> getPosicionEnVertices(){
            return posicionEnVertices;
        }
    }
    private class Arco<V,E> implements Edge<E>{
        private Position<Edge<E>> posicionEnArcos;
        private Vertice<V> cola, punta;
        private E rotulo;

        public Arco(E e, Vertice<V> cola , Vertice<V> punta){
            rotulo=e;
            this.cola=cola;
            this.punta=punta;
        }
        //Setters
        public void setRotulo(E e){
            rotulo=e;
        }
        public void setPosicionEnArcos(Position<Edge<E>> p){
            posicionEnArcos=p;
        }
        public void setCola(Vertice<V> c){
            cola=c;
        }
        public void setPunta(Vertice<V> p){
            punta=p;
        }
        //Getters
        public E element(){
            return rotulo;
        }
        public Position<Edge<E>> getPosicionEnArcos(){
            return posicionEnArcos;
        }
        public Vertice<V> getCola(){
            return cola;
        }
        public Vertice<V> getPunta(){
            return punta;
        }
    }
    
}
