package logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
    private Map<String, String> padre; // Para cada vértice, guarda su padre
    private Map<String, Integer> tamaño; // Tamaño de los árboles de cada vértice

    public UnionFind(List<String> vertices) {
        padre = new HashMap<>();
        tamaño = new HashMap<>();
        for (String vertice : vertices) {
            padre.put(vertice, vertice);
            tamaño.put(vertice, 1);
        }
    }

    public String encontrar(String vertice) {
        if (!padre.get(vertice).equals(vertice)) {
            padre.put(vertice, encontrar(padre.get(vertice))); // Compresión de caminos
        }
        return padre.get(vertice);
    }

    public void unir(String vertice1, String vertice2) {
        String raiz1 = encontrar(vertice1);
        String raiz2 = encontrar(vertice2);

        if (!raiz1.equals(raiz2)) {
            // Unión por tamaño
            if (tamaño.get(raiz1) < tamaño.get(raiz2)) {
                padre.put(raiz1, raiz2);
                tamaño.put(raiz2, tamaño.get(raiz2) + tamaño.get(raiz1));
            } else {
                padre.put(raiz2, raiz1);
                tamaño.put(raiz1, tamaño.get(raiz1) + tamaño.get(raiz2));
            }
        }
    }
}
