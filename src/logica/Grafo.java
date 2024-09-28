package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo {
	
		private Map<Integer, Set<Integer>> vecinos;
	    
	    public Grafo(int cantVertices) {
			vecinos = new HashMap<>();
	        for (int i = 0; i < cantVertices; i++)
				vecinos.put(i, new HashSet<>());
	    }
	    
	    //Revisar encapsulamiento

	    public Map<Integer, Set<Integer>> obtenerVertices() {
    	return vecinos;
	    }
    
	    public Set<Integer> obtenerVecinosVertice(int v){
	    	if(existeVertice(v))
		    	return this.vecinos.get(v);
	
	      throw new IllegalArgumentException("Vértice no válido: " + v);
	    }
	    
	    //
	    
		public int tamano(){
			return vecinos.size();
		}
	    
	    public void agregarArista(int i, int j) {
			verificarVertices(i,j);

			vecinos.get(i).add(j);
			vecinos.get(j).add(i);
	    }

	    public void eliminarArista(int i, int j) {
			existeVertice(i);
			existeVertice(j);

	        vecinos.get(i).remove(j);
	        vecinos.get(j).remove(i);
	    }

	    public boolean existeArista(int i, int j) {
			verificarVertices(i,j);
	        return vecinos.get(i).contains(j);
	    }
	    
	    public boolean existeVertice(int v) {
	        if (!vecinos.containsKey(v))
	            throw new IllegalArgumentException("El vértice "+v+" no existe.");
	        return true;
	    }
	    
	    //____Auxiliares____//

	    private void verificarDistintos(int vertice1, int vertice2) {
	        if (vertice1 == vertice2)
	            throw new IllegalArgumentException("vertice1 es igual a vertice2. No se permiten loops.");
	    }
	    
		private void verificarVertices(int i, int j){
			existeVertice(i);
			existeVertice(j);
			verificarDistintos(i, j);
		}
   
}
