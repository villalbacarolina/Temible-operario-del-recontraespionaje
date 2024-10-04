package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import logica.BFS;
import logica.Grafo;

import java.util.*;

public class BFSTest {

    @Test
    public void testEsConexoConGrafoNulo() {
        // Verificar que se lanza una excepción cuando el grafo es null
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BFS.esConexo(null);
        });
        assertEquals("El grafo no puede ser null.", exception.getMessage());
    }

    @Test
    public void testEsConexoConGrafoVacio() {
        // Crear un grafo vacío
        Grafo grafoVacio = new Grafo();
        
        // Un grafo vacío se considera conexo
        assertTrue(BFS.esConexo(grafoVacio));
    }

    @Test
    public void testEsConexoGrafoConexo() {
        // Crear un grafo conexo
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("B", "C", 1);
        grafo.agregarArista("C", "D", 1);

        // Verificar que el grafo es conexo
        assertTrue(BFS.esConexo(grafo));
    }

    @Test
    public void testEsConexoGrafoDesconexo() {
        // Crear un grafo no conexo
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("C", "D", 1);

        // Verificar que el grafo no es conexo
        assertFalse(BFS.esConexo(grafo));
    }

    @Test
    public void testAlcanzablesConUnSoloVertice() {
        // Crear un grafo con un solo vértice
        List<String> vertices = Arrays.asList("A");
        Grafo grafo = new Grafo(vertices);

        // Verificar que el único vértice es alcanzable desde sí mismo
        Set<String> alcanzables = BFS.alcanzables(grafo, "A");
        assertEquals(1, alcanzables.size());
        assertTrue(alcanzables.contains("A"));
    }

    @Test
    public void testAlcanzablesConMultiplesVertices() {
        // Crear un grafo con varios vértices conectados
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("B", "C", 1);
        grafo.agregarArista("C", "D", 1);

        // Verificar que todos los vértices son alcanzables desde A
        Set<String> alcanzables = BFS.alcanzables(grafo, "A");
        assertEquals(4, alcanzables.size());
        assertTrue(alcanzables.containsAll(Arrays.asList("A", "B", "C", "D")));
    }

    @Test
    public void testAlcanzablesConGrafoDesconexo() {
        // Crear un grafo no conexo
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("C", "D", 1);

        // Verificar que solo se pueden alcanzar los vértices conectados a A
        Set<String> alcanzables = BFS.alcanzables(grafo, "A");
        assertEquals(2, alcanzables.size());
        assertTrue(alcanzables.containsAll(Arrays.asList("A", "B")));
        assertFalse(alcanzables.contains("C"));
        assertFalse(alcanzables.contains("D"));
    }
}


