package logica;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public boolean esConexo(Grafo grafo) {
    	
        // Si el grafo no tiene vértices, se considera conexo
        if (grafo.tamano()==0) 
            return true;

        // Obtener un vértice inicial (puede ser cualquiera)
        Integer verticeInicial = grafo.obtenerVertices().keySet().iterator().next();

        // Obtener los vértices alcanzables desde el vértice inicial usando BFS
        Set<Integer> visitados = bfs(grafo,verticeInicial);

        // El grafo es conexo si todos los vértices han sido visitados
        return visitados.size() == grafo.tamano();
    }
	
    private static Set<Integer> bfs(Grafo grafo, Integer verticeInicial) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        
        cola.add(verticeInicial);
        visitados.add(verticeInicial);

        while (!cola.isEmpty()) {
            Integer verticeActual = cola.poll();

            // Visitar los vecinos del vértice actual
            for (Integer vecino : grafo.obtenerVecinosVertice(verticeActual)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        return visitados;
    }
	
}
