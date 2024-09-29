package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AGM {

    public List<Arista> obtenerAGMKruskal(Grafo grafo) {

		if (!BFS.esConexo(grafo)) {
			throw new RuntimeException("El grafo no es conexo");
		}
    	
        List<Arista> aristas = new ArrayList<>();
        List<Arista> resultado = new ArrayList<>();
        
        // Obtener todas las aristas del grafo
        for (Map.Entry<String, Map<String, Integer>> vertice : grafo.obtenerVertices().entrySet()) {
            String origen = vertice.getKey();
            for (Map.Entry<String, Integer> vecino : vertice.getValue().entrySet()) {
                String destino = vecino.getKey();
                int peso = vecino.getValue();

                // Evitar duplicados (agregar solo una vez cada arista)
                if (origen.compareTo(destino) < 0) {
                    aristas.add(new Arista(origen, destino, peso));
                }
            }
        }

        // Ordenar las aristas por peso
        Collections.sort(aristas);

        // Crear el Union-Find
        UnionFind unionFind = new UnionFind(new ArrayList<>(grafo.obtenerVertices().keySet()));

        // Agregar las aristas al AGM mientras no formen ciclos
        for (Arista arista : aristas) {
            String raizOrigen = unionFind.encontrar(arista.origen);
            String raizDestino = unionFind.encontrar(arista.destino);

            // Si no forman un ciclo, agregamos la arista al AGM
            if (!raizOrigen.equals(raizDestino)) {
                resultado.add(arista);
                unionFind.unir(arista.origen, arista.destino);
            }

            // Si el número de aristas en el resultado es igual al número de vértices - 1, ya tenemos el AGM
            if (resultado.size() == grafo.tamano() - 1) {
                break;
            }
        }

        return resultado; // Retornar las aristas que forman el AGM
    }
	
    // Método para obtener el AGM y retornar un nuevo Grafo
    public Grafo obtenerAGMComoGrafo(Grafo grafoOriginal) {
        // Paso 1: Obtener las aristas del AGM usando Kruskal
        List<Arista> aristasAGM = obtenerAGMKruskal(grafoOriginal);

        // Paso 2: Crear un nuevo grafo vacío con los mismos vértices
        List<String> vertices = new ArrayList<>(grafoOriginal.obtenerVertices().keySet());
        Grafo nuevoGrafo = new Grafo(vertices);

        // Paso 3: Agregar las aristas encontradas al nuevo grafo
        for (Arista arista : aristasAGM) {
            nuevoGrafo.agregarArista(arista.origen, arista.destino, arista.peso);
        }

        return nuevoGrafo; // Retorna el nuevo grafo generado a partir del AGM
    }

}
