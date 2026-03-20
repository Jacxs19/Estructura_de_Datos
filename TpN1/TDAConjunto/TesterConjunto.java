public class TesterConjunto{
    public static void main (String []a){
        Conjunto <Integer> c1 = new ConjuntoArreglo<Integer> (5);
        Conjunto <Integer> c2 = new ConjuntoArreglo<Integer> (5);
        Conjunto <Integer> c3= null;
        c1.put(15);
        c1.put(12);
        c1.put(3);
        c2.put(15);
        c2.put(3);
        c3=c1.interseccion(c2);
        System.out.println("Interseccion de conjunto c1 y c2: "+c3.get(0)+" , "+c3.get(1));

    }
}