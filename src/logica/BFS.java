package logica;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public boolean esConexo(Grafo g) {
        // Si el grafo no tiene vértices, se considera conexo
        if (g.obtenerVertices().isEmpty()) {
            return true;
        }

        // Obtener un vértice inicial (puede ser cualquiera)
        Integer verticeInicial = g.obtenerVertices().keySet().iterator().next();

        // Obtener los vértices alcanzables desde el vértice inicial usando BFS
        Set<Integer> visitados = bfs(g,verticeInicial);

        // El grafo es conexo si todos los vértices han sido visitados
        return visitados.size() == g.tamaño();
    }
	
    private static Set<Integer> bfs(Grafo g, Integer verticeInicial) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        
        cola.add(verticeInicial);
        visitados.add(verticeInicial);

        while (!cola.isEmpty()) {
            Integer verticeActual = cola.poll();

            // Visitar los vecinos del vértice actual
            for (Integer vecino : g.obtenerVecinosVertice(verticeActual)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        return visitados;
    }
	
}
