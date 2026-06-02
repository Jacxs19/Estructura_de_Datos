package ar.edu.uns.cs.ed.TpN8.ej1.ii;

import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.excepciones.GraphException;

@SuppressWarnings("unchecked")
public class GrafoNoDirigidoConMatrizDeAdyacencia<V,E> implements Graph<V,E>{
    protected PositionList<Vertex<V>> vertices;
    protected PositionList<Edge<E>> arcos;
    protected Edge<E> [][] matriz;
    protected int cantidadVertices;

    public GrafoNoDirigidoConMatrizDeAdyacencia(int n){         //Recibe el tamaño de la matriz
        vertices= new ListaDoblementeEnlazada<Vertex<V>>();
        arcos= new ListaDoblementeEnlazada<Edge<E>>();
        matriz= (Edge<E>[][]) new Arco[n][n];
        cantidadVertices=0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                matriz[i][j]=null;
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
    public Iterable<Edge<E>> incidentEdges(Vertex<V> v){
        Vertice<V> vertice = checkVertex(v);
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        int fila = vertice.getIndice();
        for(int col=0; col<matriz[1].length;col++){
            if(matriz[fila][col]!=null)
                lista.addLast(matriz[fila][col]);
        }
        return lista;
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e){
        Vertice<V> vertice = checkVertex(v);
        Arco<V,E> arco = checkEdge(e);
        if(arco.getV1()== vertice)return arco.getV2();
        else if(arco.getV2()==v)return arco.getV1();
        else throw new InvalidEdgeException("Vertice y arco no relacionados");
        
    }

    public Vertex<V> [] endvertices(Edge<E> e){
        Arco<V,E> arco = checkEdge(e);
        Vertex<V>[] a = (Vertex<V>[]) new Vertex [2];
        a[0]=arco.getV1();
        a[1]=arco.getV2();
        return a;
    }
    public boolean areAdjacent(Vertex<V> v, Vertex<V>w){
        Vertice<V> vertice1 = checkVertex(v);
        Vertice<V> vertice2 = checkVertex(w);
        int i1 = vertice1.getIndice();
        int i2 = vertice2.getIndice();
        return matriz[i1][i2]!=null;
    }
    public V replace(Vertex<V> v, V x){
        Vertice<V> vertice = checkVertex(v);
        V rotulo = vertice.element();
        vertice.setRotulo(x);
        return rotulo;
    }
    public E replace(Edge<E> e, E x){
        Arco<V,E> arco = checkEdge(e);
        E rotulo = arco.element();
        arco.setRotulo(x);
        return rotulo;
    }
    public Vertex<V> insertVertex(V x){
        if(cantidadVertices >= matriz.length){
            aumentarMatriz();
        }
        Vertice<V> vertice = new Vertice<V>(x, cantidadVertices++);
        vertices.addLast(vertice);
        vertice.setPosicionEnVertices(vertices.last());
        return vertice;
    }
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e){
        Vertice<V> vertice1= checkVertex(v);
        Vertice<V> vertice2= checkVertex(w);
        Arco<V,E> arco = new Arco<V,E>(e,vertice1,vertice2);
        arcos.addLast(arco);
        arco.setPosicionEnArcos(arcos.last());
        int i1=vertice1.getIndice();
        int i2=vertice2.getIndice();
        matriz[i1][i2]=matriz[i2][i1]=arco;
        return arco;
    }
    public V removeVertex(Vertex<V> v){                                         //Preguntar corrimiento de vertices.
        Vertice<V> vertice = checkVertex(v);
        V rotulo = vertice.element();
        int fila = vertice.getIndice();
        PositionList<Edge<E>> lista = new ListaDoblementeEnlazada<Edge<E>>();
        for(int i=0; i<matriz[0].length;i++){
            if(matriz[fila][i]!=null){
                lista.addLast(matriz[fila][i]);
            }
        }
        for(Edge<E> e : lista){ 
            removeEdge(e);
        }
        vertices.remove(vertice.getPosicionEnVertices());
        vertice.setPosicionEnVertices(null);
        vertice.setRotulo(null);
        cantidadVertices--;
        return rotulo;
    }

    public E removeEdge(Edge<E> e){
        Arco<V,E> arco = checkEdge(e);
        int fila = arco.getV1().getIndice();
        int col = arco.getV2().getIndice();
        matriz[fila][col] = null;
        matriz[col][fila] = null;
        arcos.remove(arco.getPosicionEnArcos());
        E elemento = arco.element();
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
        private Vertice<V> v1, v2;
        private E rotulo;

        public Arco(E rot , Vertice<V> v1 , Vertice<V> v2){
            rotulo=rot;
            this.v1=v1;
            this.v2=v2;
        }
        //Setters
        public void setPosicionEnArcos(Position<Edge<E>> p){
            posicionEnArcos=p;
        }
        public void setRotulo(E rot){
            rotulo=rot;
        }
        //Getters
        public Position<Edge<E>> getPosicionEnArcos(){
            return posicionEnArcos;
        }
        public Vertice<V> getV1(){
            return v1;
        }
        public Vertice<V> getV2(){
            return v2;
        }
        public E element(){
            return rotulo;
        }
    }
}