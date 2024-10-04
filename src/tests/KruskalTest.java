package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import logica.Arista;
import logica.Grafo;
import logica.Kruskal;

import java.util.Arrays;
import java.util.List;

public class KruskalTest {

    @Test
    public void testObtenerAGMKruskalConGrafoNoConexo() {
        // Crear un grafo no conexo
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista("A", "B", 3);
        grafo.agregarArista("C", "D", 2);

        // Verificar que se lanza una excepción cuando el grafo no es conexo
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Kruskal.obtenerAGMKruskal(grafo);
        });
        assertEquals("El grafo no es conexo", exception.getMessage());
    }

    @Test
    public void testObtenerAGMKruskalConGrafoConexo() {
        // Crear un grafo conexo
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("B", "C", 2);
        grafo.agregarArista("C", "D", 3);
        grafo.agregarArista("A", "D", 4);

        // Ejecutar Kruskal para obtener el AGM
        List<Arista> resultado = Kruskal.obtenerAGMKruskal(grafo);

        // Verificar que el número de aristas en el AGM es igual a V-1
        assertEquals(grafo.tamano() - 1, resultado.size());

        // Verificar las aristas del AGM (deberían ser las de menor peso)
        assertTrue(resultado.contains(new Arista("A", "B", 1)));
        assertTrue(resultado.contains(new Arista("B", "C", 2)));
        assertTrue(resultado.contains(new Arista("C", "D", 3)));
    }

    @Test
    public void testObtenerAGMComoGrafo() {
        // Crear un grafo conexo
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("B", "C", 2);
        grafo.agregarArista("C", "D", 3);
        grafo.agregarArista("A", "D", 4);

        // Ejecutar Kruskal para obtener el AGM como un nuevo grafo
        Grafo nuevoGrafoAGM = Kruskal.obtenerAGMComoGrafo(grafo);

        // Verificar que el nuevo grafo tiene el mismo número de vértices
        assertEquals(grafo.tamano(), nuevoGrafoAGM.tamano());

        // Verificar que el número de aristas en el nuevo grafo es igual a V-1
        assertEquals(grafo.tamano() - 1, nuevoGrafoAGM.obtenerAristas().size());

        // Verificar que las aristas son las mismas que en el AGM original
        List<Arista> aristasAGM = nuevoGrafoAGM.obtenerAristas();
        assertTrue(aristasAGM.get(0).equals(new Arista("A", "B", 1)));
        assertTrue(aristasAGM.get(1).equals(new Arista("B", "C", 2)));
        assertTrue(aristasAGM.get(2).equals(new Arista("C", "D", 3)));
    }

    @Test
    public void testObtenerAGMKruskalConGrafoConUnSoloVertice() {
        // Crear un grafo con un solo vértice
        List<String> vertices = Arrays.asList("A");
        Grafo grafo = new Grafo(vertices);

        // Ejecutar Kruskal en un grafo con un solo vértice
        List<Arista> resultado = Kruskal.obtenerAGMKruskal(grafo);

        // Un grafo con un solo vértice no tiene aristas, el AGM debe estar vacío
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testObtenerAGMKruskalConGrafoConDosVerticesSinArista() {
        // Crear un grafo con dos vértices sin arista
        List<String> vertices = Arrays.asList("A", "B");
        Grafo grafo = new Grafo(vertices);

        // Verificar que Kruskal lanza una excepción ya que el grafo no es conexo
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Kruskal.obtenerAGMKruskal(grafo);
        });
        assertEquals("El grafo no es conexo", exception.getMessage());
    }
}


