public class ConjuntoArreglo<E> implements Conjunto<E>{
    private int cant;
    private E[] coleccion;
    //Constructor
    public ConjuntoArreglo(int tamanio){
        coleccion = (E[]) new Object[tamanio];      //Produce warning, pero hago un casteo.
        cant=0;
    }

    public int size(){
        return cant;
    }
    public int capacity(){
        return coleccion.length;
    }
    public boolean isEmpty(){
        return cant==0;
    }
    public E get(int i){                        //Req. posicion valida
        return coleccion[i];
    }
    public void put (E e){                      //Requiere que el conjunto no esté lleno y que el elemento no se encuentre en el conjunto
        coleccion[cant]=e;
        cant++;
    }
    public boolean pertenece (E elem){          //Retorna verdadero si y sólo si el elemento elem se encuentra en el conjunto. La comparación se realiza por equivalencia.
        boolean esta =false;
        int i=0;
        while (!esta && i<cant){
            esta=coleccion[i].equals(elem);
            i++;    
        }
        return esta;
    }
    public Conjunto<E> interseccion (Conjunto<E> c){    //Retorna un nuevo conjunto producto de intersectar los elementos del conjunto que recibe el mensaje y el pasado por parámetro
        Conjunto<E> inter= new ConjuntoArreglo<>(cant);
        for(int i=0; i<cant; i++){
            if (c.pertenece(coleccion[i]))
                inter.put(coleccion[i]);
        }
        return inter;
    }
}