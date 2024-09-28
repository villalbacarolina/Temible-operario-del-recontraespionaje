package logica;
import java.util.*;

public class Grafo {
    // Variables
    private Map<String, Map<String, Integer>> vertices; // Mapa de vértices y sus vecinos con pesos
    private List<Arista> aristas; // Lista de aristas del grafo

    // Constructor
    public Grafo(int cantVertices, List<String> nombresVertices) {
        vertices = new HashMap<>();
        aristas = new ArrayList<>(); // Inicializar la lista de aristas
        for (String vertice : nombresVertices) {
            vertices.put(vertice, new HashMap<>());
        }
    }

    // Obtener los vértices
    public Map<String, Map<String, Integer>> obtenerVertices() {
        return this.vertices;
    }

    // Obtener las aristas
    public List<Arista> obtenerAristas() {
        return this.aristas;
    }

    // Obtener los vecinos de un vértice
    public Map<String, Integer> obtenerVecinosVertice(String v) {
        if (existeVertice(v))
            return this.vertices.get(v);

        throw new IllegalArgumentException("Vértice no válido: " + v);
    }

    // Tamaño del grafo (cantidad de vértices)
    public int tamano() {
        return vertices.size();
    }

    // Agregar arista con peso
    public void agregarArista(String i, String j, int peso) {
        verificarVertices(i, j);

        vertices.get(i).put(j, peso);
        vertices.get(j).put(i, peso); // Arista bidireccional

        // Agregar la arista a la lista de aristas
        aristas.add(new Arista(i, j, peso));
    }

    // Verificar si existe una arista
    public boolean existeArista(String i, String j) {
        verificarVertices(i, j);
        return vertices.get(i).containsKey(j);
    }

    // Verificar si existe un vértice
    public boolean existeVertice(String v) {
        if (!vertices.containsKey(v))
            throw new IllegalArgumentException("El vértice " + v + " no existe.");
        return true;
    }

    // Obtener el peso de una arista
    public int obtenerPesoArista(String i, String j) {
        if (existeArista(i, j)) {
            return vertices.get(i).get(j);
        }
        throw new IllegalArgumentException("La arista no existe entre " + i + " y " + j);
    }

    // Métodos privados
    private void verificarDistintos(String vertice1, String vertice2) {
        if (vertice1.equals(vertice2))
            throw new IllegalArgumentException("vertice1 es igual a vertice2. No se permiten loops.");
    }

    private void verificarVertices(String i, String j) {
        existeVertice(i);
        existeVertice(j);
        verificarDistintos(i, j);
    }

    @SuppressWarnings("unused")
    private void eliminarArista(String i, String j) {
        existeVertice(i);
        existeVertice(j);

        vertices.get(i).remove(j);
        vertices.get(j).remove(i);
        
        // Eliminar la arista de la lista de aristas
        aristas.removeIf(arista -> (arista.origen.equals(i) && arista.destino.equals(j)) || 
                                    (arista.origen.equals(j) && arista.destino.equals(i)));
    }
}

