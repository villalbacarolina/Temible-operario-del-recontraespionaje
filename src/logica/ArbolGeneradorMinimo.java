package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ArbolGeneradorMinimo {

    public static List<Arista> obtenerAGMKruskal(Grafo grafo) {

        if (!BFS.esConexo(grafo)) {
            throw new RuntimeException("El grafo no es conexo");
        }
        
        List<Arista> aristas = new ArrayList<>();
        List<Arista> resultado = new ArrayList<>();
        
        // Obtener todas las aristas del grafo
        for (Entry<String, Map<String, Double>> vertice : grafo.obtenerVertices().entrySet()) {
            String origen = vertice.getKey();
            for (Entry<String, Double> vecino : vertice.getValue().entrySet()) {
                String destino = vecino.getKey();
                Double pesoInt = vecino.getValue(); // Obtener el peso como int
                double peso = (double) pesoInt; // Convertir a double

                // Evitar duplicados (agregar solo una vez cada arista)
                if (origen.compareTo(destino) < 0) {
                    aristas.add(new Arista(origen, destino, peso)); // Usar el peso como double
                }
            }
        }

        // Ordenar las aristas por peso
        Collections.sort(aristas);

        // Crear el Union-Find
        UnionFind unionFind = new UnionFind(new ArrayList<>(grafo.obtenerVertices().keySet()));

        // Agregar las aristas al AGM mientras no formen ciclos
        for (Arista arista : aristas) {
            String raizOrigen = unionFind.encontrar(arista.getOrigen());
            String raizDestino = unionFind.encontrar(arista.getDestino());

            // Si no forman un ciclo, agregamos la arista al AGM
            if (!raizOrigen.equals(raizDestino)) {
                resultado.add(arista);
                unionFind.unir(arista.getOrigen(), arista.getDestino());
            }

            // Si el número de aristas en el resultado es igual al número de vértices - 1, ya tenemos el AGM
            if (resultado.size() == grafo.tamano() - 1) {
                break;
            }
        }

        return resultado; // Retornar las aristas que forman el AGM
    }
    
    // Método para obtener el AGM y retornar un nuevo Grafo
    public static Grafo obtenerAGMComoGrafo(Grafo grafoOriginal) {
        // Paso 1: Obtener las aristas del AGM usando Kruskal
        List<Arista> aristasAGM = obtenerAGMKruskal(grafoOriginal);

        // Paso 2: Crear un nuevo grafo vacío con los mismos vértices
        List<String> vertices = new ArrayList<>(grafoOriginal.obtenerVertices().keySet());
        Grafo nuevoGrafo = new Grafo(vertices);

        // Paso 3: Agregar las aristas encontradas al nuevo grafo
        for (Arista arista : aristasAGM) {
            nuevoGrafo.agregarArista(arista.getOrigen(), arista.getDestino(), arista.getPeso());
        }

        return nuevoGrafo; // Retorna el nuevo grafo generado a partir del AGM
    }
}
