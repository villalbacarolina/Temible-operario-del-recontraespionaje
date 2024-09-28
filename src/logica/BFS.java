package logica;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public static boolean esConexo(Grafo grafo) {
        // Si el grafo no tiene vértices, se considera conexo
        if (grafo.tamano() == 0) 
            return true;

        // Obtener un vértice inicial (puede ser cualquiera)
        String verticeInicial = grafo.obtenerVertices().keySet().iterator().next();

        // Obtener los vértices alcanzables desde el vértice inicial usando BFS
        Set<String> visitados = bfs(grafo, verticeInicial);

        // El grafo es conexo si todos los vértices han sido visitados
        return visitados.size() == grafo.tamano();
    }

    private static Set<String> bfs(Grafo grafo, String verticeInicial) {
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        cola.add(verticeInicial);
        visitados.add(verticeInicial);

        while (!cola.isEmpty()) {
            String verticeActual = cola.poll();

            // Visitar los vecinos del vértice actual
            for (String vecino : grafo.obtenerVecinosVertice(verticeActual).keySet()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        return visitados;
    }
}

