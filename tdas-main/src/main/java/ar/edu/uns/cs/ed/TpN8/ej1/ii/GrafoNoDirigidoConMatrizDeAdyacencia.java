package ar.edu.uns.cs.ed.TpN8.ej1.ii;

import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Graph;
import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;

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
