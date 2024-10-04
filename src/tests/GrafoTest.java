package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Arista;
import logica.Grafo;


import java.util.List;


public class GrafoTest {
    private Grafo grafo;

    @BeforeEach
    public void setUp() {
        grafo = new Grafo();
    }

    @Test
    public void testAgregarVertice() {
        grafo.agregarVertice("A");
        assertTrue(grafo.obtenerVertices().containsKey("A"));
    }

    @Test
    public void testAgregarVerticeExistente() {
        grafo.agregarVertice("A");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            grafo.agregarVertice("A");
        });
        assertEquals("El vértice 'A' ya existe.", exception.getMessage());
    }

    @Test
    public void testAgregarArista() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarArista("A", "B", 5);
        
        assertEquals(5, grafo.obtenerPesoArista("A", "B"));
        assertTrue(grafo.obtenerVecinosVertice("A").containsKey("B"));
        assertTrue(grafo.obtenerVecinosVertice("B").containsKey("A"));
    }

    @Test
    public void testAgregarAristaEntreVerticesNoExistentes() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            grafo.agregarArista("A", "B", 5);
        });
        assertEquals("No existe el vértice 'A'.", exception.getMessage());
    }

    @Test
    public void testEliminarArista() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarArista("A", "B", 5);
        
        grafo.eliminarArista("A", "B");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            grafo.obtenerPesoArista("A", "B");
        });
        assertEquals("La arista no existe entre A y B", exception.getMessage());
    }

    @Test
    public void testEliminarAristaQueNoExiste() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            grafo.eliminarArista("A", "B");
        });
        assertEquals("No existe una arista entre A y B.", exception.getMessage());
    }

    @Test
    public void testEliminarVertice() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarArista("A", "B", 5);

        grafo.eliminarVertice("A");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            grafo.obtenerVecinosVertice("A");
        });
        assertEquals("Vértice no válido: A", exception.getMessage());
        assertFalse(grafo.obtenerVertices().containsKey("A"));
    }

    @Test
    public void testTamano() {
        assertEquals(0, grafo.tamano());
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        assertEquals(2, grafo.tamano());
    }

    @Test
    public void testObtenerVerticeAleatorio() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        String vertice = grafo.obtenerVerticeAleatorio();
        assertTrue(vertice.equals("A") || vertice.equals("B"));
    }

    @Test
    public void testObtenerVerticeAleatorioSinVertices() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            grafo.obtenerVerticeAleatorio();
        });
        assertEquals("Aún no existen vértices.", exception.getMessage());
    }

    @Test
    public void testObtenerAristas() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarArista("A", "B", 5);

        List<Arista> aristas = grafo.obtenerAristas();
        assertEquals(1, aristas.size());
        assertEquals("A", aristas.get(0).getOrigen());
        assertEquals("B", aristas.get(0).getDestino());
        assertEquals(5, aristas.get(0).getPeso());
    }

    @Test
    public void testObtenerVecinosVerticeNoExistente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            grafo.obtenerVecinosVertice("A");
        });
        assertEquals("Vértice no válido: A", exception.getMessage());
    }

    @Test
    public void testObtenerPesoArista() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarArista("A", "B", 5);

        int peso = grafo.obtenerPesoArista("A", "B");
        assertEquals(5, peso);
    }

    @Test
    public void testObtenerPesoAristaQueNoExiste() {
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            grafo.obtenerPesoArista("A", "B");
        });
        assertEquals("La arista no existe entre A y B", exception.getMessage());
    }
}

