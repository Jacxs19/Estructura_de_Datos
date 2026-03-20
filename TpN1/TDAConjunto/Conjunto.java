//<<interface>> generica
public interface Conjunto<E> {                              //<E> sirve para que el conjunto pueda guardar elementos de cualquier tipo.
    public int size();
    public int capacity();
    public boolean isEmpty();
    public E get(int i);
    public void put(E e);
    public boolean pertenece(E e);
    public Conjunto<E> interseccion (Conjunto<E> c);
}
