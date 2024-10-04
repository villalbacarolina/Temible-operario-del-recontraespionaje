package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.UnionFind;

import java.util.Arrays;
import java.util.List;

public class UnionFindTest {

    private UnionFind unionFind;

    @BeforeEach
    public void setUp() {
        // Inicializamos el UnionFind con algunos vértices
        List<String> vertices = Arrays.asList("A", "B", "C", "D", "E");
        unionFind = new UnionFind(vertices);
    }

    @Test
    public void testInicializacion() {
        // Comprobamos que cada vértice es su propio padre al inicio
        assertEquals("A", unionFind.encontrar("A"));
        assertEquals("B", unionFind.encontrar("B"));
        assertEquals("C", unionFind.encontrar("C"));
        assertEquals("D", unionFind.encontrar("D"));
        assertEquals("E", unionFind.encontrar("E"));
    }

    @Test
    public void testUnionSimple() {
        // Unimos dos vértices y verificamos que tienen la misma raíz
        unionFind.unir("A", "B");
        assertEquals(unionFind.encontrar("A"), unionFind.encontrar("B"));
    }

    @Test
    public void testUnionMultiple() {
        // Unimos varios vértices y verificamos que pertenecen al mismo conjunto
        unionFind.unir("A", "B");
        unionFind.unir("C", "D");
        unionFind.unir("A", "C");
        
        // Todos deben tener la misma raíz
        String raiz = unionFind.encontrar("A");
        assertEquals(raiz, unionFind.encontrar("B"));
        assertEquals(raiz, unionFind.encontrar("C"));
        assertEquals(raiz, unionFind.encontrar("D"));
        
        // E no ha sido unido, por lo que debe tener una raíz diferente
        assertNotEquals(raiz, unionFind.encontrar("E"));
    }

    @Test
    public void testUnionPorTamaño() {
        // Unimos varios vértices asegurándonos de que se realiza la unión por tamaño correctamente
        unionFind.unir("A", "B"); // Unión entre A y B
        unionFind.unir("C", "D"); // Unión entre C y D
        unionFind.unir("A", "C"); // Unir los conjuntos A-B y C-D (se debería unir el más pequeño al más grande)

        // Verificamos que las uniones son correctas
        assertEquals(unionFind.encontrar("A"), unionFind.encontrar("D"));
        assertEquals(unionFind.encontrar("B"), unionFind.encontrar("C"));
    }

    @Test
    public void testCompresionCaminos() {
        // Unimos varios vértices
        unionFind.unir("A", "B");
        unionFind.unir("B", "C");
        unionFind.unir("C", "D");

        // Realizamos una búsqueda de E para asegurar que no cambia la raíz
        assertEquals("E", unionFind.encontrar("E"));
        
        // Verificamos que la compresión de caminos funciona correctamente
        assertEquals(unionFind.encontrar("A"), unionFind.encontrar("D"));

        // Verificamos que la raíz de B es ahora la misma que la de D, optimizando el árbol
        assertEquals(unionFind.encontrar("B"), unionFind.encontrar("A"));
    }

}

