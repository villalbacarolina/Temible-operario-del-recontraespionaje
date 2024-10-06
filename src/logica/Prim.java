package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Prim {

    public static List<Arista> prim(Grafo grafo) {
        if (!BFS.esConexo(grafo)) {
            throw new RuntimeException("El grafo no es conexo");
        }
        if (grafo.tamano() <= 0) { 
            throw new RuntimeException("El grafo está vacío");
        }

        Set<String> verticesVisitados = new HashSet<>();
        List<Arista> aristasResultantesAGM = new ArrayList<>();
        verticesVisitados.add(grafo.obtenerVerticeAleatorio());

        while (verticesVisitados.size() < grafo.tamano()) {
            String verticeV = null;
            String verticeU = null;
            double menorPeso = Double.MAX_VALUE;  
            Arista aristaMenorPeso = null;

            // Iterar sobre los vértices ya visitados
            for (String u : verticesVisitados) {
                // Iterar sobre los vértices vecinos no visitados
                for (Map.Entry<String, Double> vecinos : grafo.obtenerVertices().get(u).entrySet()) {
                    String v = vecinos.getKey();
                    double peso = vecinos.getValue();

                    if (!verticesVisitados.contains(v) && peso < menorPeso) {
                        menorPeso = peso;
                        verticeU = u;
                        verticeV = v;
                        aristaMenorPeso = new Arista(verticeU, verticeV, menorPeso);
                    }
                }
            }

            if (aristaMenorPeso != null) {
                verticesVisitados.add(verticeV);
                aristasResultantesAGM.add(aristaMenorPeso);
            }
        }

        return aristasResultantesAGM;
    }
}
