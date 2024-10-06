package logica;
import java.io.Serializable;
import java.util.*;

public class Grafo implements Serializable {
    private static final long serialVersionUID = 1L;

    // Vertice1  // Vertice2 // Peso
    private Map<String, Map<String, Double>> grafo;
    transient private List<Arista> aristas;

    public Grafo() {
        grafo = new HashMap<>();
        aristas = new ArrayList<>();
    }

    public Grafo(List<String> vertices) {
        this();
        for (String vertice : vertices)
            this.grafo.put(vertice, new HashMap<String, Double>());
    }

    public void agregarVertice(String v) {
        if (existeVertice(v))
            throw new RuntimeException("El vértice '" + v + "' ya existe.");
        grafo.put(v, new HashMap<String, Double>());
    }

    public void agregarArista(String vertice1, String vertice2, double peso) {
        verificarVertices(vertice1, vertice2);

        grafo.get(vertice1).put(vertice2, peso);
        grafo.get(vertice2).put(vertice1, peso);

        aristas.add(new Arista(vertice1, vertice2, peso));
    }

    public void eliminarArista(String vertice1, String vertice2) {
        existeVertice(vertice1);
        existeVertice(vertice2);
        if (existeArista(vertice1, vertice2)) {
            grafo.get(vertice1).remove(vertice2);
            grafo.get(vertice2).remove(vertice1);

            aristas.removeIf(arista -> (arista.getOrigen().equals(vertice1) && arista.getDestino().equals(vertice2))
                    				|| (arista.getOrigen().equals(vertice2) && arista.getDestino().equals(vertice1)));
        } else
            throw new IllegalArgumentException("No existe una arista entre " + vertice1 + " y " + vertice2 + ".");
    }

    public void eliminarVertice(String vertice) {
        grafo.remove(vertice);
    }

    public int tamano() {
        return grafo.size();
    }

    public String obtenerVerticeAleatorio() {
        if (!grafo.isEmpty()) {
            String vertice = grafo.keySet().iterator().next();
            return vertice;
        } else
            throw new RuntimeException("Aún no existen vértices.");
    }

    // Getters //

    // Pendiente: Mejorar encapsulamiento
    public Map<String, Map<String, Double>> obtenerVertices() {
        return this.grafo;
    }

    // Pendiente: Mejorar encapsulamiento
    public List<Arista> obtenerAristas() {
        return this.aristas;
    }

    // Pendiente: Mejorar encapsulamiento
    public Map<String, Double> obtenerVecinosVertice(String v) {
        if (existeVertice(v))
            return this.grafo.get(v);
        throw new IllegalArgumentException("Vértice no válido: " + v);
    }

    public double obtenerPesoArista(String i, String j) {
        if (existeArista(i, j))
            return grafo.get(i).get(j);
        throw new IllegalArgumentException("La arista no existe entre " + i + " y " + j);
    }

    // Auxiliares //

    public boolean existeVertice(String v) {
        return grafo.containsKey(v);
    }

    public boolean existeArista(String i, String j) {
        verificarVertices(i, j);
        return grafo.get(i).containsKey(j);
    }

    private void verificarVerticesDistintos(String v1, String v2) {
        if (v1.equals(v2))
            throw new IllegalArgumentException("Los vértices son iguales. No se permiten loops.");
    }

    private void verificarVertices(String v1, String v2) {
        if (!existeVertice(v1))
            throw new IllegalArgumentException("No existe el vértice '" + v1 + "'.");
        if (!existeVertice(v2))
            throw new IllegalArgumentException("No existe el vértice '" + v2 + "'.");
        verificarVerticesDistintos(v1, v2);
    }
}
