package ar.edu.uns.cs.ed.TDAS_Implementados;

public class Comparador<E extends Comparable<E>> implements java.util.Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		
		return ((Comparable<E>) o1).compareTo(o2);
	}
	
}
