package ar.edu.uns.cs.ed.TpN8.ej1.i;

import ar.edu.uns.cs.ed.tdas.tdagrafo.Edge;
import ar.edu.uns.cs.ed.tdas.Position;

public class Arco<V,E> implements Edge<E>{
    private E rotulo;
    private Vertice<V,E> v1, v2;
    private Position<Arco<V,E>> posicionEnArcos;
    private Position<Arco<V,E>> posicionEnIv1, posicionEnIv2;

    public Arco(E rotulo, Vertice<V,E> v1, Vertice<V,E> v2 ){
        this.rotulo=rotulo;
        this.v1=v1;
        this.v2=v2;
    }
    //Setters
    public void setV1(Vertice<V,E> v){
        v1=v;
    }
    public void setV2(Vertice<V,E> v){
        v2=v;
    }
    public void setRotulo(E element){
        rotulo=element;
    }
    public void setPosicionEnArcos(Position<Arco<V,E>> p){
        posicionEnArcos=p;
    }
    public void setPosicionEnIv1(Position<Arco<V,E>> p){
        posicionEnIv1=p;
    }
    public void setPosicionEnIv2(Position<Arco<V,E>> p){
        posicionEnIv2=p;
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
    public Position<Arco<V,E>> getPosicionEnArcos(){
        return posicionEnArcos;
    }
    public Position<Arco<V,E>> getPosicionEnIv1(){
        return posicionEnIv1;
    }
    public Position<Arco<V,E>> getPosicionEnIv2(){
        return posicionEnIv2;
    }
}