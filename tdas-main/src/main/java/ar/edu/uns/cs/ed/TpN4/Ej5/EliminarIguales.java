package ar.edu.uns.cs.ed.TpN4.Ej5;
import ar.edu.uns.cs.ed.TDAS_Implementados.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.Position;


public class EliminarIguales{

    public Iterable<Character> eliminarIguales(PositionList<Character> l1, PositionList<Character> l2){
        PositionList<Character> eliminados = new ListaDoblementeEnlazada<Character>();
        PositionList<Position<Character>> aBorrar = new ListaDoblementeEnlazada<Position<Character>>();
        for(Position<Character> c :l2.positions()){
            for(Character caracter : l1){
                if(caracter.equals(c.element())){
                    aBorrar.addLast(c);
                }
            }
        }
        for(Position<Character> pos : aBorrar){
            eliminados.addLast(pos.element());
            l2.remove(pos);
        }
        return eliminados;
    }
    
}
