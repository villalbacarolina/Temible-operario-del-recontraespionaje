package logica;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public static boolean esConexo(Grafo grafo) {
        if (grafo == null)
			throw new IllegalArgumentException("El grafo no puede ser null.");
        if (grafo.tamano() == 0) 
            return true;
        
        String verticeInicial = grafo.obtenerVerticeAleatorio();
        Set<String> verticesAlcanzables = alcanzables(grafo, verticeInicial);
        
        return verticesAlcanzables.size() == grafo.tamano();
    }

    private static Set<String> alcanzables(Grafo grafo, String verticeInicial) {
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        cola.add(verticeInicial);
        visitados.add(verticeInicial);

        while (!cola.isEmpty()) {
            String verticeActual = cola.poll();

            for (String vecino : grafo.obtenerVecinosVertice(verticeActual).keySet())
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
        }
        return visitados;
    }
}

