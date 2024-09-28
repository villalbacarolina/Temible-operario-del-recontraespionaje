package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo {
	    private Map<Integer, Set<Integer>> adjList;
	    
	    // Constructor para inicializar el grafo con un número dado de vértices
	    public Grafo(int vertices) {
	        adjList = new HashMap<>();
	        for (int i = 0; i < vertices; i++) {
	            adjList.put(i, new HashSet<>());
	        }
	    }

	    //obtenerVertices del Grafo
	    public Map<Integer, Set<Integer>> obtenerVertices() {
	    	return adjList;
	    }
	    
	    public Set<Integer> obtenerVecinosVertice(int v){
	    	if(verificarVertice(v)) {
		    	return this.adjList.get(v);
	    	}
            throw new IllegalArgumentException("Vértice no válido: " + v);
	    }
	    
	    //devuelve el tamaño del grafo (cantidad de vertices)
		public int tamaño()
		{
			return adjList.size();
		}
	    
	    // Agrega una arista entre los vértices i y j
	    public void agregarArista(int i, int j) {
	        verificarVertice(i);
	        verificarVertice(j);
	        verificarDistintos(i, j);

	        adjList.get(i).add(j);
	        adjList.get(j).add(i);
	    }

	    // Elimina una arista entre los vértices i y j
	    public void eliminarArista(int i, int j) {
	        verificarVertice(i);
	        verificarVertice(j);
	        verificarDistintos(i, j);

	        adjList.get(i).remove(j);
	        adjList.get(j).remove(i);
	    }

	    // Verifica si existe una arista entre los vértices i y j
	    public boolean existeArista(int i, int j) {
	        verificarVertice(i);
	        verificarVertice(j);
	        verificarDistintos(i, j);

	        return adjList.get(i).contains(j);
	    }
	    
	    // Verifica si el vértice es válido
	    private boolean verificarVertice(int i) {
	        if (!adjList.containsKey(i)) {
	            throw new IllegalArgumentException("Vértice no válido: " + i);
	        }else {
	        	return true;
	        }
	    }

	    // Verifica que i y j sean distintos
	    private void verificarDistintos(int i, int j) {
	        if (i == j) {
	            throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	        }
	    }
   
}
