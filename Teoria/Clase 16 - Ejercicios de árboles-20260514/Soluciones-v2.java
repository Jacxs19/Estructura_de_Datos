public class Arbol<E> implements Tree<E> {
	protected TNodo<E> raiz;
	protected int size;
	...
}

Esto respeta el principio abierto-cerrado:
1. Todas las clases son abiertas a extensión (por herencia)
2. Cerradas a modificaciones (esto garantiza que nadie rompe un código que ya funciona).	
	
public class ArbolMasMetodosExplicacion<E> extends Arbol<E> {
	...
	
	// PROBLEMA 1: 
	public int sizeSubarbol(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);  //c1 // Convierto p en nodo para acceder a sus campos.
		return contarNodos(nodo); // Llamo a metodo privado que recorre la estructura // return tiene tiempo c2
	} 
	// En el peor escenario p es la raiz del árbol y 
	// el tamaño del problema es n = cantidad de nodos del arbol.
	// En general el tamaño del problema será la cantidad de nodos
	// del subarbol con raiz p.
	// T(n) = c1 + c2 + T_contarNodos(n)
	
	
	// El metodo es recursivo:
	// CB: Una hoja tiene exactamente un nodo.
	// CR: Un nodo interno tiene tantos nodos como la suma de nodos de sus subarboles + 1.
	private int contarNodos(TNodo<E> nodo) {
		if (nodo.getHijos().isEmpty()) // c1
			return 1; c2
		else {  // nodo es interno
			int suma = 1;  // Cuento al nodo donde estoy parado c3
			for ( TNodo<E> hijo : nodo.getHijos() ) { // hi iteraciones
					suma += contarNodos(hijo);  // c4
			}
			return suma; // c5
		}
	} 
	// T_contarNodos(n) = O(n) 
	// La justificación es la misma que la de preorden.
	// Paso una vez por cada nodo:
// T(n) = sum_{i=1}^{n}{c1 + max(c2, c3 + hi*c4 + c5)}
//        = sum_{i=1}^{n}{c + hic'}
// 		= sum_{i=1}^{n}{c} + = sum_{i=1}^{n}{hi*c'}
//        = n*c + c'(n-1) porque la suma de los hi daba (n-1)=cantidad arco del arbol
//		= O(max(n, n-1)) = O(n)
		
		
	// Hecho con TDAArbol
	// this esta recibiendo el mensaje
	private int contarNodos(Position<E> nodo) {
		if (isExternal(nodo)) // c1
			return 1; c2
		else {  // nodo es interno
			int suma = 1;  // Cuento al nodo donde estoy parado c3
			for ( Position<E> hijo : children(nodo) ) { // hi iteraciones
					suma += contarNodos(hijo);  // c4
			}
			return suma; // c5
		}
	} 
	
	
	
	// PROBLEMA 2: Primera aproximacion usando el iterador
	public Map<Position<E>, Integer> mapSizeSubarboles() throws InvalidPositionException {
		// Creo un mapeo usando hash:
		Map<Position<E>, Integer> resultado = new HashMap<Position<E>, Integer>(); // c1
		for ( Position<E> p : positions() ) { 
		    // O(n) por positions + n iteraciones del foreach
			resultado.put( p, sizeSubarbol(p) );
			// c3 del put + O(n) de sizeSubarbol
			// El put tiene O(1) porque hash con distribucion
			// uniforme de claves y factor de carga controlado.
		}
		return resultado;  // c2
	}
	// T_mapSizeSubarboles(n) = c1 + O(n) + n*(c3 + O(n)) + c2 = O(n^2)
	
	// PROBLEMA 2: Segunda aproximacion recolectando resultados en postorden
	public Map<Position<E>, Integer> mapSizeSubarboles() throws InvalidPositionException {
		// Creo un mapeo usando hash:
		Map<Position<E>, Integer> resultado = new HashMap<Position<E>, Integer>();
		recorrerYContar( raiz, resultado );
		return resultado;
	}
	
	private void recorrerYContar( TNodo<E> nodo, 
		Map<Position<E>, Integer> mapeo ) {
		if (isExternal(nodo)) 
			// El subarbol tiene un nodo porque es una hoja.
			mapeo.put(nodo, 1); 
		else { // nodo es interno:
			// Computo los valores del mapeo para sus hijos:
			for( TNodo<E> h : nodo.getHijos() ) {
				recorrerYContar( h, mapeo );
			}
			//-------------------------------
			// VISITA DE nodo en POSTORDER:
			// Al salir de la recursión el mapeo conoce
			// el tamaño de todos los subarboles que estan "abajo"
			// Seteo el valor del mapeo para nodo:
			int suma = 1; // Inicializo con 1 para tener en cuenta a nodo.
			for( TNodo<E> h : nodo.getHijos() ) 
				suma += mapeo.get(h);
			mapeo.put(nodo, suma);
			// Fin VISITA
			//-------------------------------
	} 
	// T_recorrerYContar(n) = n*T_put(n) + (n-1)*T_get(n) 
	// entre O(n) y O(n^2) dependiendo del mapeo.
	// POR QUÉ?
	// Si el mapeo es bueno (hash bien hecho), cada get y cada put tiene O(1)
	// y paso una vez por cada nodo y hay n nodos => T(n) = O(n*grado)
	// Pensar que hay tantos gets como arcos tiene el arbol,
	// o sea que T(n) = O(n)
	// Si el mapeo es malo (mapeo con lista o hash mal hecho) =>
	// paso una vez por cada nodo y en cada nodo tengo tantos gets como hijos 
	// tiene el nodo y un put. 
	
	// Mejora para recorrer y contar que no accede al mapeo:
	// Devuelve el valor asignado en el mapeo para nodo.
	private int recorrerYContar( TNodo<E> nodo, Map<Position<E>, Integer> mapeo ) {
		if (isExternal(nodo)) { // El subarbol tiene 1 nodo
			resultado.put(nodo, 1); 
			return 1; 
		} else { // nodo es interno:
			// Computo los valores del mapeo para sus hijos:
			int suma = 1; // Inicializo con 1 para tener en cuenta a nodo.
			for( TNodo<E> h : nodo.getHijos() ) {
					suma += recorrerYContar( h, mapeo );
			}
			// Seteo el valor del mapeo para nodo:
			mapeo.put( nodo, suma );
			return suma; // Le devuelvo el valor que computé al que me llamó para que lo use.
	} // T_recorrerYContar(n) = n*T_put(n) = entre O(n) y O(n^2) dependiendo del hash.
	
	
	// PROBLEMA 3:
	public int podarSubArbol(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);  // c1
		// Propago excepción InvalidPositionException si checkPosition falla.
		// Si p es la raíz del árbol => dejo el  árbol vacío:
		if ( nodo.getPadre() == null ) {  // c2
			int resultado = this.size; // c3
			this.size = 0;  // c4
			this.raiz = null;// c5
			nodo.setRotulo(null);  // Invalido la posicion // c6
			// En realidad hay que invalidar todas las posiciones en O(n)
			// MEJORA PARA LA Y EL ESTUDIANTE.
			return resultado; // c7 => then => O(n)
		} else { // p no es la raiz del arbol:
			int resultado = contarNodos(nodo);  // Computo el resutado. // O(n)
			// Elimino el nodo: voy al padre y lo borro de la lista de sus hijos, y modifico size del arbol.
			TNodo<E> padre = nodo.getPadre(); // O(1)
			Iterator<Position<TNodo<E>>> it = 
				padre.getHijos().positions().iterator(); // O(grado del padre)
				// grado de un arbol = maxima cantidad de hijos
			boolean encontre = false; // O(1)
			Position<TNodo<E>> pos = null; // O(1)
			while (it.hasNext() && !encontre) { // iteraciones = grado del padre
				pos = it.next(); // O(1) // Cond=O(1)
				if ( pos.element() == nodo ) encontre = true; O(1)
			}
			if ( encontre == false ) // Exageracion! // O(1)
				throw new InvalidPositionException("Arbol roto: el padre de p no conoce a p");
			// pos es la posicicion de nodo en la lista de hijos de su padre.
			padre.getHijos().remove( pos ); // Borro a la posicion pos de la lista.
			// O(1) si la lista de hijos es doblemente enlazada con centinelas
			this.size -= resultado; // O(1)
			nodo.setRotulo(null);  // Invalido la posicion
			// En realidad hay que invalidar todas las posiciones en O(n)
			// MEJORA PARA LA Y EL ESTUDIANTE.
			return resultado;  // O(1)
		}	
	} // n = size del arbol => T(n) = max( O(1), O(n), O(grado)) = O(n)
...} // Fin class


// Uso:
OtraClase trabajador = new OtraClase();
trabajador.cambiarArbol( unArbolDeString, "mama", "papa");
trabajador.cambiarArbol( unArbolDeEnteros, 8, 9 );


public class OtraClase {
	// PROBLEMA 4:
	public <E> void cambiarArbol( Tree<E> t, E e, E f ) {
		for ( Position<E> p : t.positions() ) {
			if (p.element().equals(e)) t.replace( p, f );
		}
	}   
	// La entrada del algoritmo es this, p, e, f.
	// n = la cantidad de nodos de this, p mide 1, el tamaño de e y f no son
	// parte del enunciado.
	// T_cambiarArbol(n) = T_positions() + T_for_each(n) + T_if(n) + T_replace(n)
	// O(n) + O(n) + O(1) + O(1) = O(max(n,n,1,1)) = O(n)
	
	// Quiero hacer lo mismo pero sin el iterador.
	// Puedo el recorrer el arbol: por niveles, preorden, postorden, inorden.
	public <E> void cambiarArbol( Tree<E> t, E e, E f ) 
		throws InvalidOperationException {
		try {
			cambiarArbolRec( t, t.root(), e, f );
		} catch( EmptyTreeException ex ) {
			throw new InvalidOperationException("cambiarArbol: Me paso " + 
				"un árbol vacio");
		}
	}
	
	private <E> void cambiarArbolRec( Tree<E> t, Position<E> p, E e, E f ) {
		if ( t.isExternal(p) ) { // CB: p es una hoja
			if ( p.element().equals(e) ) t.replace( p, f );
		} else { // CR: p no es una hoja
			if ( p.element().equals(e) ) t.replace( p, f );
			...
		}	
	}
	
	// Refactorizo el codigo.
	private <E> void cambiarArbolRec( Tree<E> t, Position<E> p, E e, E f ) {
		if ( p.element().equals(e) ) t.replace( p, f);
		if ( t.isInternal(p) ) { 
			for (Position<E> h : t.children(p))
				cambiarArbolRec(t, h, e, f);
		}	
	}
	
	// Refactorizo el codigo:
	private <E> void cambiarArbolRec( Tree<E> t, Position<E> p, E e, E f ) {
		// Visita
		if ( p.element().equals(e) ) t.replace( p, f ); 
		// visita = c1 + c2 = c4
		for (Position<E> h : t.children(p))   // tantas iteraciones como hijos tiene p
			cambiarArbolRec(t, h, e, f); // c2 = tiempo de crear y destruir el registro de activacion
	}
	
	// Tiempo de ejecucion:
	// n = cantidad de nodos del arbol t
	// el tamaño de e y f no me importan porque no dependen del arbol ni 
	// son inherentes al algoritmo
	// El algoritmo procesa 1 vez a cada nodo y en cada nodo hace:
	// T(n) = n * T_visita(n) +  tiempo de recorre los hijos del nodo actual 
	// 		= n * c4 + (n-1)*c2 = O(n)
	// La suma de las iteraciones de todos los foreach da n-1 = la cantidad de 
	// arcos del arbol.
		
	
	// PROBLEMA 5:
	public static <E> void insertarMasivo(E e, int x, Tree<E> a) {
		for ( Position<E> p : t.positions() )
			if ( profundidad(t, p) == x ) 
				t.addFirstChild( p,  e.clone() );
	}
	
	// T(n) = O( n + n(h + 1))
	
	// Esto se invoca:
	// OtraClase.insertarMasivo( e, x, a );
	
	// Integer.parseInt( "123" ) ---> 123
	
	// Si el metodo no es estatico:
	public <E> void insertarMasivo(E e, int x, Tree<E> a) {
		for ( Position<E> p : t.positions() )
			if ( profundidad(t, p) == x ) 
				t.addFirstChild( p,  e.clone() );
	}
		
	OtraClase otro = new OtraClase();
	otro.insertarMasivo( ..... );
	
	// Vamos a hacer otra solucion con preorden que va a tener O(n)
    public static <E> void insertarMasivo(E e, int x, Tree<E> a) {
		try {
			recorrerEInsertar( e, x, a, a.root(), 0);
		} catch(EmptyTreeException ex) {
			ex.printStackTrace();
		}
	}
	
	private static <E> void recorrerEInsertar( E e, int x, 
		Tree<E> a, Position<E> p, int nivelActual ) {
		if ( nivelActual == x ) {  // c1
			t.addFirstChild( p, e.clone() );  // c2
		} else if ( nivelActual < x ) {  
			for (Position<E> h : t.children(p)) {  // hi iteraciones
				recorrerEInsertar( e, x, a, h, nivel + 1); // c3
			}
		}
	} //	T(n) = O(n) = n(c1 + c2) + c3(n-1) por los motivos que
	// la cantidad de iteraciones de todos los foreach considerados
	// todos juntos equivalen a la cantidad de arcos del arbol.
	// La justificación del orden es que se recorre el árbol a de n nodos exhaustivamente
	// en O(n)
	// y en el peor escenario en cada nodo se agrega un hijo en O(1).

	// PROBLEMA 6:
	
// Utilizando el TDA Árbol, resuelva el siguiente problema; dados dos árboles A y B, 
// determinar si B está incluido perfectamente dentro de A. Que esté incluido 
// perfectamente quiere decir que B aparece exactamente igual a como es dentro 
// de A.
	
	
	public <E> boolean estaIncluidoEn( Tree<E> b, Tree<E> a ) {
		for ( Position<E> p : a.positions() )  // n iteraciones
			if ( esIgual( b, b.root(), a, p ) ) return true; // O(n+m)
		return false; // c1
	}
	/*
	El orden de ejecución de este metodo requiere usar complejidad combinada porque tiene
	2 entradas: n=tamaño de a y m=tamaño de b.
	T(n,m) = n * T_esigual(n,m) = n * (n+m) = O(n^2 + n*m)
	*/
	
	// RECURSIVO:
	// CB Positivo: p1 y p2 son hojas y tienen el mismo rotulo.
	// CB Negativo: p1 y p2 son hojas y no tienen el mismo rotulo.
	// CB Negativo: p1 es hoja y p2 no es hoja.
	// CB Negativo: p1 no es hoja y p2 es hoja.
	// CB Negativo: p1 y p2 son internos y tienen distinta cantidad de hijos.
	// CR: p1 y p2 son internos, tienen la misma cantidad de hijos => 
	// debo testear si sus hijos son iguales dos a dos.
	private <E> boolean esIgual( Tree<E> a1, Position<E> p1, 
	                             Tree<E> a2, Position<E> p2 ) {
		if ( a1.isExternal(p1) && a2.isExternal(p2) ) 
			return p1.element().equals(p2.element());
		else if ( a1.isExternal(p1) && a2.isInternal(p2)) return false;
		else if ( a1.isInternal(p1) && a2.ExInternal(p2)) return false;
		else { // p1 y p2 son internos:
			// Comparo rotulos de p1 y p2:
			if (p1.element().equals(p2.element()) == false) return false;
			// Si estoy aca los rotulos son iguales y sigo comparando:
			int gradoP1 = longitud(a1.children(p1));
			int gradoP2 = longitud(a2.children(p2));
			if ( gradoP1 != gradoP2 ) return false;
			else { // gradoP1 == gradoP2 => las dos listas de children miden igual
				Iterator<Position<E>> it1 = a1.children(p1).iterator();
				Iterator<Position<E>> it2 = a2.children(p2).iterator();
				boolean seguir = true;
				while ( it1.hasNext() && seguir ) { // no miro it2 porque miden lo mismo
					Position<E> h1 = it1.next();
					Position<E> h2 = it2.next();
					seguir = esIgual( a1, h1, a2, h2 );
				}
				// Si sali porque agoté la iteracion => seguir = true
				// y los árboles eran iguales
				// Si sali porque seguir = false => algun llamado recursivo
				// falló y algún subarbol no es igual.
				return seguir; 
			}
		}
	}
	

private int longitud(Iterable<TNode<E>> hijos) {
	int cont = 0;
	for ( TNode<E> h : hijos ) cont++;
	return cont;
}


/*

Para calcular el tiempo de ejecución, como hay dos entradas a1 y a2, 
hay que usar complejidad combinada, es decir, usar 2 tamaños de entrada:
n = cantidad de nodos de a1, y,
m = cantidad de nodos de a2

En el peor escenario los árboles son iguales, entonces a1 se recorre completamente y
lo mismo a2.
El costo de recorrer a1 es O(n) y el costo de recorrer a2 es O(m).
Entonces el costo de recorrer los 2 árboles es O(n+m).



*/