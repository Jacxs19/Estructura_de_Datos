package ar.edu.uns.cs.ed.TpN8.ej1.i;

import ar.edu.uns.cs.ed.tdas.tdagrafo.Vertex;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;

public class Vertice<V,E> implements Vertex<V>{
    private V rotulo;
    private PositionList<Arco<V,E>> adyacentes;
    private Position<Vertice<V,E>> posicionEnNodos;
    
    public Vertice (V rotulo){
        this.rotulo= rotulo;
        adyacentes = new ListaDoblementeEnlazada<Arco<V,E>>();
    }

    //Setters
    public void setRotulo(V nuevoRotulo){
        rotulo=nuevoRotulo;
    }
    public void setPosicionEnNodos(Position<Vertice<V,E>> p){
        posicionEnNodos=p;
    }
    //Getters
    public PositionList<Arco<V,E>> getAdyacentes(){
        return adyacentes;
    }
    public V element(){
        return rotulo;
    }
    public Position<Vertice<V,E>> getPosicion(){
        return posicionEnNodos;
    }
}
